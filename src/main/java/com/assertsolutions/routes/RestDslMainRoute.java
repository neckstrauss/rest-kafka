package com.assertsolutions.routes;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.bean.validator.BeanValidationException;
import org.apache.camel.component.kafka.KafkaComponent;
import org.apache.camel.component.kafka.KafkaConstants;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestBindingMode;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.assertsolutions.dto.AuditoriaRequest;

/**
 * 
 * @author Assert Solutions S.A.S
 *
 */
@Component
public class RestDslMainRoute extends RouteBuilder {

	@Value("${camel.component.servlet.mapping.context-path}")
	private String contextPath;

	@Autowired
	private Logger logger;

	@Autowired
	private CamelContext camelContext;


	@Override
	public void configure() throws Exception {
		camelContext.setUseMDCLogging(Boolean.TRUE);
		KafkaComponent kafka = new KafkaComponent();
		kafka.setBrokers("{{kafka.host}}:{{kafka.port}}");
		camelContext.addComponent("kafka", kafka);

		onException(BeanValidationException.class)
		.handled(true)
		.setHeader(Exchange.HTTP_RESPONSE_CODE, constant(400))
		.bean("errorResponse")
		.marshal().json(JsonLibrary.Jackson)
		;
		
		onException(Exception.class)
		.handled(true)
		.setHeader(Exchange.HTTP_RESPONSE_CODE, constant(500))
		.bean("errorResponse")
		;
		
		 restConfiguration().component("servlet").bindingMode(RestBindingMode.json)
	        .dataFormatProperty("prettyPrint", "true").enableCORS(true).port("{{server.port}}")
	        .contextPath(contextPath.substring(0, contextPath.length() - 2))
	        .apiContextPath("/api-doc").apiProperty("api.title", "{{api.title}}")
	        .apiProperty("api.version", "{{api.version}}");
	        
		
		rest("/karaf")
		.post()//.consumes("application/json")
		.type(AuditoriaRequest.class)
		//.outType(Response.class)		
		
		//.description(env.getProperty("api.description.service"))
		.to("direct:procesarpeticion")
		//
		;
		
		from("direct:procesarpeticion")
		.to("bean-validator:validacion")
		.inOnly("seda:kafkaStart")
		.bean("responsehandler")
		
		;

		from("seda:kafkaStart").routeId("DirectToKafka")
	//	.delay(60000)
		.marshal().json(JsonLibrary.Jackson)
		.log(LoggingLevel.INFO, "Inicia ruta  kafka con mensaje => ${body}")
		.removeHeaders("*")
		.setHeader(KafkaConstants.PARTITION_KEY, constant(0))
		.setHeader(KafkaConstants.KEY, constant("1"))
		.to("kafka:{{kafka.topic}}" )
		.log("despues de entregar mensaje: ${headers}");

		from("direct:kafkaStartNoTopic").routeId("kafkaStartNoTopic").to("kafka:dummy").log("${headers}");
		// @formatter:on
	}

}
