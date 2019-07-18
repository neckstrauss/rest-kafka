package com.assertsolutions.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author Assert Solutions S.A.S
 *
 */

public class Response {

  
    private Data data;

    
    public Response(String response, String message, Date fecha) {
		super();
		String pattern = "yyyy-MM-dd HH:mm:ssZ";
		this.data = new Data(response, message, new SimpleDateFormat(pattern).format(fecha));
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	private class Data{
    	
    	private String response;
    	private String message;
    	private String fecha;
    	
		public Data(String response, String message, String fecha) {
			super();
			this.response = response;
			this.message = message;
			this.fecha = fecha;
		}

		public String getResponse() {
			return response;
		}

		public void setResponse(String response) {
			this.response = response;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public String getFecha() {
			return fecha;
		}

		public void setFecha(String fecha) {
			this.fecha = fecha;
		}
    	
    	
    	
    }

}
