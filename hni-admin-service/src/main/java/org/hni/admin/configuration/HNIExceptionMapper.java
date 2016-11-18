package org.hni.admin.configuration;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.hni.common.exception.HNIException;

@Provider
public class HNIExceptionMapper implements ExceptionMapper<HNIException> {

	@Override
	public Response toResponse(HNIException e) {
		return Response.status(e.getResponse().getStatus()).
                entity(e.getResponse().getEntity()).
                type(e.getResponse().getMediaType()).
                build();
	}

}
