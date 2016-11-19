package org.hni.order.service;

import org.hni.order.dao.DefaultPartialOrderDAO;
import org.hni.order.om.PartialOrder;
import org.hni.order.om.TransactionPhase;
import org.hni.provider.om.MenuItem;
import org.hni.provider.om.ProviderLocation;
import org.hni.provider.service.GeoCodingService;
import org.hni.provider.service.MenuService;
import org.hni.user.dao.UserDAO;
import org.hni.user.om.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Test the OrderProcessorService
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:test-applicationContext.xml"})
public class TestDefaultOrderProcessorService {

    @Mock
    private UserDAO userDAO;

    @Mock
    private DefaultPartialOrderDAO partialOrderDAO;

    @Mock
    private GeoCodingService geoCodingService;

    @Mock
    private MenuService menuService;

    @Inject
    @InjectMocks
    private DefaultOrderProcessor orderProcessor;

    private User user;
    private List<ProviderLocation> providerLocationList;
    private Map <ProviderLocation, MenuItem> menuItemMap;

    private ArgumentCaptor<PartialOrder> argumentCaptor;
    private PartialOrder partialOrder;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        argumentCaptor = ArgumentCaptor.forClass(PartialOrder.class);
        partialOrder = new PartialOrder();
        user = new User("John", "Handcock", "1234567890");
    }

    @Test
    public void processMessage_meal_success() {
        // Setup
        String message = "MEAL";
        Mockito.when(partialOrderDAO.get(user)).thenReturn(null);

        // Execute
        String output = orderProcessor.processMessage(user, message);
        String expectedOutput = "Please provide your address";

        // Verify
        Assert.assertEquals(expectedOutput, output);
        ArgumentCaptor<PartialOrder> argumentCaptor = ArgumentCaptor.forClass(PartialOrder.class);
        Mockito.verify(partialOrderDAO, Mockito.times(1)).save(argumentCaptor.capture());
        Assert.assertEquals(TransactionPhase.PROVIDING_ADDRESS, argumentCaptor.getValue().getTransactionPhase());
    }

    @Test
    public void processMessage_providingAddress_success() {
        // Setup
        String message = "5540 S Hyde Park Blvd, Chicago IL, 60637";
        partialOrder.setTransactionPhase(TransactionPhase.PROVIDING_ADDRESS);

        Mockito.when(partialOrderDAO.get(user)).thenReturn(partialOrder);
        Mockito.when(geoCodingService.resolveAddress(Mockito.anyString())).thenReturn();

        // Execute
        String output = orderProcessor.processMessage(user, message);
        String expectedOutput = "";

        // Verify
//        Assert.assertEquals(expectedOutput, output);
    }

    @Test
    public void processMessage_choosingLocation() {

        String message = "MEAL";

        Mockito.when(partialOrderDAO.get(user)).thenReturn(null);

        String output = orderProcessor.processMessage(user, message);
        String expectedOutput = "";

//        Assert.assertEquals(expectedOutput, output);
    }

    @Test
    public void processMessage_choosingMenuItem() {

        String message = "MEAL";

        Mockito.when(partialOrderDAO.get(user)).thenReturn(null);

        String output = orderProcessor.processMessage(user, message);
        String expectedOutput = "";

//        Assert.assertEquals(expectedOutput, output);
    }


    @Test
    public void processMessage_confirmOrContinue() {

        String message = "MEAL";

        Mockito.when(partialOrderDAO.get(user)).thenReturn(null);

        String output = orderProcessor.processMessage(user, message);
        String expectedOutput = "";

//        Assert.assertEquals(expectedOutput, output);
    }



}
