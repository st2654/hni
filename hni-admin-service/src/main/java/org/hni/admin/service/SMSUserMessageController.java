package org.hni.admin.service;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.map.HashedMap;
import org.hni.common.exception.HNIException;
import org.hni.events.service.EventServiceFactory;
import org.hni.events.service.om.Event;
import org.hni.provider.om.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Api(value = "/usermessage", description = "endpoint to accept SMS messages")
@Component
@Path("/usermessage")
public class SMSUserMessageController extends AbstractBaseController {
    private static final Logger logger = LoggerFactory.getLogger(SMSUserMessageController.class);

    @Inject
    private EventServiceFactory eventServiceFactory;

    @GET
    @Produces(MediaType.TEXT_HTML)
    @ApiOperation(value = "processes an SMS workflow and returns the response as HTML"
            , notes = ""
            , response = Provider.class
            , responseContainer = "")
    public String respondToMessageHTML(@QueryParam("auth_key") String authKey,
                                       @QueryParam("phonenumber") String phoneNumber,
                                       @QueryParam("sessionid") String sessionId,
                                       @QueryParam("usertext") String userMessage,
                                       @QueryParam("testmode") String testMode) {

        logger.info("HTML/Received a message, auth_key={}, phonenumber={}, sessionid={}, " +
                "usertext={}, textmode={}", authKey, phoneNumber, sessionId, userMessage, testMode);
        final Event event = new Event(sessionId, phoneNumber, userMessage);
        return String.format("<html><body>%s</body></html>", eventServiceFactory.handleEvent(event));
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @ApiOperation(value = "processes an SMS workflow and returns the response as text"
            , notes = ""
            , response = Provider.class
            , responseContainer = "")
    public String respondToMessage(@QueryParam("auth_key") String authKey,
                                   @QueryParam("phonenumber") String phoneNumber,
                                   @QueryParam("sessionid") String sessionId,
                                   @QueryParam("usertext") String userMessage,
                                   @QueryParam("testmode") String testMode) {
        logger.info("PLAIN/Received a message, auth_key={}, phonenumber={}, sessionid={}, " +
                "usertext={}, textmode={}", authKey, phoneNumber, sessionId, userMessage, testMode);
        final Event event = new Event(sessionId, phoneNumber, userMessage);
        try {
            return eventServiceFactory.handleEvent(event);
        } catch (Exception ex) {
            throw new HNIException("Something went wrong. Please try again later.", Response.Status.INTERNAL_SERVER_ERROR);
        }

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "processes an SMS workflow and returns the response as JSON"
            , notes = ""
            , response = Provider.class
            , responseContainer = "")
    public Map<String, Object> respondToMessageJson(@QueryParam("auth_key") String authKey,
                                   @QueryParam("phonenumber") String phoneNumber,
                                   @QueryParam("sessionid") String sessionId,
                                   @QueryParam("usertext") String userMessage,
                                   @QueryParam("testmode") String testMode) {
        logger.info("JSON/Received a message, auth_key={}, phonenumber={}, sessionid={}, " +
                "usertext={}, textmode={}", authKey, phoneNumber, sessionId, userMessage, testMode);
        final Event event = new Event(sessionId, phoneNumber, userMessage);
        Map<String, Object> res = new HashMap();
        try {
            String[] output = {eventServiceFactory.handleEvent(event)};
            res.put("message", output);
            res.put("status", Response.Status.OK.getStatusCode());
        } catch (HNIException ex) {
            logger.error("Error handling request: {}", ex.getMessage());
            res.put("error", ex.getMessage());
            res.put("status", ex.getResponse().getStatus());
            throw new HNIException(Response.status(ex.getResponse().getStatus()).entity(res).build());
        } catch (Exception ex) {
            logger.error("Internal error handling request: {}", ex.getMessage());
            res.put("error", "Something went wrong. Please try again later.");
            res.put("status", Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
            throw new HNIException(Response.serverError().entity(res).build());
        }
        return res;
    }

}
