package org.hni.common.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

//https://jersey.java.net/documentation/latest/representations.html 
public class HNIException extends WebApplicationException {

	public HNIException() {
		this("could not find the resource");
	}
	
	public HNIException(String message) {
		super(Response.status(Response.Status.NOT_FOUND).entity(message).type(MediaType.APPLICATION_JSON).build());
	}
	

	public HNIException(String message, Response.Status status) {
		super(Response.status(status).entity(message).type(MediaType.APPLICATION_JSON).build());
	}
}
