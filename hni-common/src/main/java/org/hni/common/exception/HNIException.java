package org.hni.common.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

//https://jersey.java.net/documentation/latest/representations.html 
public class HNIException extends WebApplicationException {

	public HNIException() {
		
	}
}
