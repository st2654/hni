package org.hni.admin.service;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hni.events.service.EventRouter;
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
import javax.ws.rs.core.MediaType;

@Api(value = "/usermessage", description = "endpoint to accept SMS messages")
@Component
@Path("/usermessage")
public class SMSUserMessageController extends AbstractBaseController {
    private static final Logger logger = LoggerFactory.getLogger(SMSUserMessageController.class);

    @Inject
    private EventRouter eventRouter;

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
        final Event event = Event.createEvent("text/html", phoneNumber, userMessage);
        return String.format("<html><body>%s</body></html>", eventRouter.handleEvent(event));
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
        logger.info("Received a message, auth_key={}, phonenumber={}, sessionid={}, " +
                "usertext={}, textmode={}", authKey, phoneNumber, sessionId, userMessage, testMode);
        final Event event = Event.createEvent("text/plain", phoneNumber, userMessage);
        return eventRouter.handleEvent(event);

    }
}
