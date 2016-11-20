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
import static org.mockito.Mockito.*;

public class EventServiceFactoryIntTest {

    //TODO FIX SESSION_ID and phoneNumber REFACTOR
    private static final String SESSION_ID = "8188461238";
    private static final String PHONE_NUMBER = "8188461238";
    private static final String AUTH_CODE = "123456";

    @InjectMocks
    private EventServiceFactory factory;

    @Spy
    @InjectMocks
    private RegisterService registerService = new RegisterService();

    @Spy
    private SessionStateDAO sessionStateDao = new DefaultSessionStateDAO();

    @Mock
    private CustomerService customerService;

    @Mock
    private ActivationCodeService activationCodeService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        factory.init();
        when(customerService.validate(any(User.class))).thenReturn(true);
        when(activationCodeService.validate(eq(AUTH_CODE))).thenReturn(true);
    }

    @Test
    public void testStartRegister() throws Exception {
        String returnString = factory.handleEvent(new Event(SESSION_ID, PHONE_NUMBER, "REGISTER"));
        Assert.assertEquals("Welcome to Hunger Not Impossible! Msg & data rates may apply. "
                + "Any information you provide here will be kept private. "
                + "Reply with PRIVACY to learn more. Let's get you registered. What's your name?", returnString);
        verify(sessionStateDao, times(1)).insert(any(SessionState.class));
        verify(sessionStateDao, times(1)).update(any(SessionState.class));
        SessionState nextState = sessionStateDao.get(SESSION_ID);
        Assert.assertEquals(SESSION_ID, nextState.getSessionId());
        Assert.assertEquals(PHONE_NUMBER, nextState.getPhoneNumber());
        Assert.assertEquals(EventName.REGISTER, nextState.getEventName());
        Assert.assertEquals(EventState.STATE_REGISTER_GET_NAME, nextState.getEventState());
    }

    @Test
    public void testInterruptExistingWorkFlow() {
        sessionStateDao.insert(new SessionState(EventName.MEAL, SESSION_ID, PHONE_NUMBER, null, null));
        String returnString = factory.handleEvent(new Event(SESSION_ID, PHONE_NUMBER, "REGISTER"));
        Assert.assertEquals("Welcome to Hunger Not Impossible! Msg & data rates may apply. "
                + "Any information you provide here will be kept private. "
                + "Reply with PRIVACY to learn more. Let's get you registered. What's your name?", returnString);
        verify(sessionStateDao, times(1)).delete(eq(SESSION_ID));
        verify(sessionStateDao, times(2)).insert(any(SessionState.class));
    }

    @Test
    public void testRegisterWorkFlow() throws Exception {
        // enroll keyword to start register workflow
        String returnString = factory.handleEvent(new Event(SESSION_ID, PHONE_NUMBER, "ENROLL"));
        Assert.assertEquals("Welcome to Hunger Not Impossible! Msg & data rates may apply. "
                + "Any information you provide here will be kept private. "
                + "Reply with PRIVACY to learn more. Let's get you registered. What's your name?", returnString);
        // name
        returnString = factory.handleEvent(new Event(SESSION_ID, PHONE_NUMBER, "john doe"));
        Assert.assertEquals("Perfect! Lastly, I'd like to get your email address "
                + "to verify your account in case you text me from a new "
                + "number. So what's your email address? Thanks", returnString);
        // email
        returnString = factory.handleEvent(new Event(SESSION_ID, PHONE_NUMBER, "johndoe@gmail.com"));
        Assert.assertEquals("Okay! I have " + "johndoe@gmail.com" + " as your email address. "
                + "Is that correct? Reply 1 for yes and 2 for no", returnString);
        // confirm email
        returnString = factory.handleEvent(new Event(SESSION_ID, PHONE_NUMBER, "1"));
        Assert.assertEquals("Please enter the 6 digit authorization code provided to you for this program.", returnString);
        // auth code
        returnString = factory.handleEvent(new Event(SESSION_ID, PHONE_NUMBER, "123456"));
        Assert.assertEquals("Ok. You're all setup for yourself. If you have additional family"
                + " members to register please enter the additional authorization"
                + " codes now. When you need a meal just text MEAL back to this number.", returnString);
        // addition auth code
        returnString = factory.handleEvent(new Event(SESSION_ID, PHONE_NUMBER, "111111"));
        Assert.assertEquals("The authorization code you entered (" + "111111" + ") is not valid."
                + " Please resend a valid unused authorization code", returnString);
    }
}
