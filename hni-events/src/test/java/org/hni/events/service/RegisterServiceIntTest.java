package org.hni.events.service;

import org.hni.events.service.dao.DefaultSessionStateDAO;
import org.hni.events.service.dao.SessionStateDAO;
import org.hni.events.service.om.Event;
import org.hni.events.service.om.EventName;
import org.hni.events.service.om.EventState;
import org.hni.events.service.om.SessionState;
import org.hni.security.service.ActivationCodeService;
import org.hni.user.om.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

public class RegisterServiceIntTest {

    private static final String SESSION_ID = "123";
    private static final String PHONE_NUMBER = "8188461238";
    private static final String AUTH_CODE = "123456";

    @InjectMocks
    private RegisterService registerService;

    @Spy
    SessionStateDAO sessionStateDAO = new DefaultSessionStateDAO();

    @Mock
    private CustomerService customerService;

    @Mock
    private ActivationCodeService activationCodeService;

    private SessionState state;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        state = new SessionState(EventName.REGISTER, SESSION_ID, PHONE_NUMBER, null, EventState.STATE_REGISTER_START);
        sessionStateDAO.insert(state);
        when(customerService.validate(any(User.class))).thenReturn(true);
        when(activationCodeService.validate(eq(AUTH_CODE))).thenReturn(true);
    }

    @Test
    public void testStartRegister() throws Exception {
        String returnString = registerService.handleEvent(new Event(SESSION_ID, PHONE_NUMBER, "message"));
        Assert.assertEquals("Welcome to Hunger Not Impossible! Msg & data rates may apply. "
                + "Any information you provide here will be kept private. "
                + "Reply with PRIVACY to learn more. Let's get you registered. What's your first name?", returnString);
        SessionState nextState = sessionStateDAO.getByPhoneNumber(PHONE_NUMBER);
        Assert.assertEquals(SESSION_ID, nextState.getSessionId());
        Assert.assertEquals(PHONE_NUMBER, nextState.getPhoneNumber());
        Assert.assertEquals(EventName.REGISTER, nextState.getEventName());
        Assert.assertEquals(EventState.STATE_REGISTER_GET_FIRST_NAME, nextState.getEventState());
    }

    @Test
    public void testRegisterWorkFlow() throws Exception {
        // enroll keyword to start register workflow
        String returnString = registerService.handleEvent(new Event(SESSION_ID, PHONE_NUMBER, "ENROLL"));
        Assert.assertEquals("Welcome to Hunger Not Impossible! Msg & data rates may apply. "
                + "Any information you provide here will be kept private. "
                + "Reply with PRIVACY to learn more. Let's get you registered. What's your first name?", returnString);
        // first name
        returnString = registerService.handleEvent(new Event(SESSION_ID, PHONE_NUMBER, "john"));
        Assert.assertEquals("Thanks " + "john" + ". What's your last name?", returnString);
        // last name
        returnString = registerService.handleEvent(new Event(SESSION_ID, PHONE_NUMBER, "doe"));
        Assert.assertEquals("Perfect! Lastly, I'd like to get your email address "
                + "to verify your account in case you text me from a new "
                + "number. So what's your email address? Thanks", returnString);
        // email
        returnString = registerService.handleEvent(new Event(SESSION_ID, PHONE_NUMBER, "johndoe@gmail.com"));
        Assert.assertEquals("Okay! I have " + "johndoe@gmail.com" + " as your email address. "
                + "Is that correct? Reply 1 for yes and 2 for no", returnString);
        // confirm email
        returnString = registerService.handleEvent(new Event(SESSION_ID, PHONE_NUMBER, "1"));
        Assert.assertEquals("Please enter the 6 digit authorization code provided to you for this program.", returnString);
        // auth code
        returnString = registerService.handleEvent(new Event(SESSION_ID, PHONE_NUMBER, "123456"));
        Assert.assertEquals("Ok. You're all setup for yourself. If you have additional family"
                + " members to register please enter the additional authorization"
                + " codes now. When you need a meal just text MEAL back to this number.", returnString);
        // addition auth code
        when(activationCodeService.validate(eq("111111"))).thenReturn(true);
        returnString = registerService.handleEvent(new Event(SESSION_ID, PHONE_NUMBER, "111111"));
        Assert.assertEquals("We have added that authorization code to your family account. Please"
                + " send any additional codes you need for your family.", returnString);
    }

    @Test
    public void testCorrectEmailOnce() throws Exception {
        // enroll keyword to start register workflow
        String returnString = registerService.handleEvent(new Event(SESSION_ID, PHONE_NUMBER, "ENROLL"));
        Assert.assertEquals("Welcome to Hunger Not Impossible! Msg & data rates may apply. "
                + "Any information you provide here will be kept private. "
                + "Reply with PRIVACY to learn more. Let's get you registered. What's your first name?", returnString);
        // first name
        returnString = registerService.handleEvent(new Event(SESSION_ID, PHONE_NUMBER, "john"));
        Assert.assertEquals("Thanks " + "john" + ". What's your last name?", returnString);
        // last name
        returnString = registerService.handleEvent(new Event(SESSION_ID, PHONE_NUMBER, "doe"));
        Assert.assertEquals("Perfect! Lastly, I'd like to get your email address "
                + "to verify your account in case you text me from a new "
                + "number. So what's your email address? Thanks", returnString);
        // wrong email
        returnString = registerService.handleEvent(new Event(SESSION_ID, PHONE_NUMBER, "johndoe@gmail.com"));
        Assert.assertEquals("Okay! I have " + "johndoe@gmail.com" + " as your email address. "
                + "Is that correct? Reply 1 for yes and 2 for no", returnString);
        // reject email
        returnString = registerService.handleEvent(new Event(SESSION_ID, PHONE_NUMBER, "2"));
        Assert.assertEquals("So what's your email address?", returnString);
        // email
        returnString = registerService.handleEvent(new Event(SESSION_ID, PHONE_NUMBER, "johndoe2@gmail.com"));
        Assert.assertEquals("Okay! I have " + "johndoe2@gmail.com" + " as your email address. "
                + "Is that correct? Reply 1 for yes and 2 for no", returnString);
        // confirm email
        returnString = registerService.handleEvent(new Event(SESSION_ID, PHONE_NUMBER, "1"));
        Assert.assertEquals("Please enter the 6 digit authorization code provided to you for this program.", returnString);
        // auth code
        returnString = registerService.handleEvent(new Event(SESSION_ID, PHONE_NUMBER, "123456"));
        Assert.assertEquals("Ok. You're all setup for yourself. If you have additional family"
                + " members to register please enter the additional authorization"
                + " codes now. When you need a meal just text MEAL back to this number.", returnString);
    }

    @Test
    public void testInvalidAdditionalAuthCode() throws Exception {
        // enroll keyword to start register workflow
        String returnString = registerService.handleEvent(new Event(SESSION_ID, PHONE_NUMBER, "ENROLL"));
        Assert.assertEquals("Welcome to Hunger Not Impossible! Msg & data rates may apply. "
                + "Any information you provide here will be kept private. "
                + "Reply with PRIVACY to learn more. Let's get you registered. What's your first name?", returnString);
        // first name
        returnString = registerService.handleEvent(new Event(SESSION_ID, PHONE_NUMBER, "john"));
        Assert.assertEquals("Thanks " + "john" + ". What's your last name?", returnString);
        // last name
        returnString = registerService.handleEvent(new Event(SESSION_ID, PHONE_NUMBER, "doe"));
        Assert.assertEquals("Perfect! Lastly, I'd like to get your email address "
                + "to verify your account in case you text me from a new "
                + "number. So what's your email address? Thanks", returnString);
        // email
        returnString = registerService.handleEvent(new Event(SESSION_ID, PHONE_NUMBER, "johndoe@gmail.com"));
        Assert.assertEquals("Okay! I have " + "johndoe@gmail.com" + " as your email address. "
                + "Is that correct? Reply 1 for yes and 2 for no", returnString);
        // confirm email
        returnString = registerService.handleEvent(new Event(SESSION_ID, PHONE_NUMBER, "1"));
        Assert.assertEquals("Please enter the 6 digit authorization code provided to you for this program.", returnString);
        // auth code
        returnString = registerService.handleEvent(new Event(SESSION_ID, PHONE_NUMBER, "123456"));
        Assert.assertEquals("Ok. You're all setup for yourself. If you have additional family"
                + " members to register please enter the additional authorization"
                + " codes now. When you need a meal just text MEAL back to this number.", returnString);
        // addition auth code
        returnString = registerService.handleEvent(new Event(SESSION_ID, PHONE_NUMBER, "111111"));
        Assert.assertEquals("The authorization code you entered (" + "111111" + ") is not valid."
                + " Please resend a valid unused authorization code", returnString);
    }

}
