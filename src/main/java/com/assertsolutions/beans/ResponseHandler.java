package com.assertsolutions.beans;

import java.util.Calendar;

import org.apache.camel.Handler;
import org.springframework.stereotype.Component;

import com.assertsolutions.dto.Response;

import io.swagger.annotations.ApiModelProperty;

@Component("responsehandler")
public class ResponseHandler {

    @Handler
    @ApiModelProperty(notes = "Parametro De Salida")
    public Response handler() {
	
	return new Response("OK", "Mensaje recibido exitosamente", Calendar.getInstance().getTime());
    }
}
