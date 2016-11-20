package org.hni.order.service;

import org.hni.order.dao.DefaultPartialOrderDAO;
import org.hni.order.dao.OrderDAO;
import org.hni.order.om.Order;
import org.hni.order.om.OrderItem;
import org.hni.order.om.PartialOrder;
import org.hni.order.om.TransactionPhase;
import org.hni.provider.om.GeoCodingException;
import org.hni.provider.om.Menu;
import org.hni.provider.om.MenuItem;
import org.hni.provider.om.Provider;
import org.hni.provider.om.ProviderLocation;
import org.hni.provider.service.MenuService;
import org.hni.provider.service.ProviderLocationService;
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
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Test the OrderProcessorService.  This class assumes that anything consumed from within the app (from DAOs ect.) are 0 fault.
 *      These test wil ONLY be testing faults from consumer input.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:test-applicationContext.xml"})
public class TestDefaultOrderProcessorService {

    @Mock
    private UserDAO userDAO;

    @Mock
    private DefaultPartialOrderDAO partialOrderDAO;

    @Mock
    private ProviderLocationService providerLocationService;

    @Mock
    private MenuService menuService;

    @Mock
    private OrderDAO orderDAO;

    @Inject
    @InjectMocks
    private DefaultOrderProcessor orderProcessor;

    private User user;
    private List<ProviderLocation> providerLocationList;
    private List<MenuItem> menuItems;

    private ArgumentCaptor<PartialOrder> argumentCaptor;
    private PartialOrder partialOrder;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        argumentCaptor = ArgumentCaptor.forClass(PartialOrder.class);
        partialOrder = new PartialOrder();
        providerLocationList = new ArrayList<>();
        menuItems = new ArrayList<>();

        standardTestData();
    }

    private void standardTestData() {
        user = new User("John", "Handcock", "1234567890");

        MenuItem item = new MenuItem("Food", "Good Food", 12.12, new Date());

        Menu menu = new Menu(1L);
        Set itemSet = new HashSet<>();
        itemSet.add(item);
        menu.setMenuItems(itemSet);

        Provider provider = new Provider(1L);
        Set menuSet = new HashSet<>();
        menuSet.add(menu);
        provider.setMenus(menuSet);

        ProviderLocation providerLocation = new ProviderLocation(1L);
        providerLocation.setName("Subway");
        providerLocation.setProvider(provider);
        menuItems.add(item);
        providerLocationList.add(providerLocation);

        providerLocation = new ProviderLocation(2L);
        providerLocation.setName("McDonalds");
        providerLocation.setProvider(provider);
        menuItems.add(item);
        providerLocationList.add(providerLocation);

        providerLocation = new ProviderLocation(3L);
        providerLocation.setName("Waffle House");
        providerLocation.setProvider(provider);
        menuItems.add(item);
        providerLocationList.add(providerLocation);
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
        Mockito.when(providerLocationService.providersNearCustomer(Mockito.anyString(), Mockito.anyInt()))
                .thenReturn(providerLocationList);

        // Execute
        String output = orderProcessor.processMessage(user, message);
        String expectedOutput = "Please provide a number between 1-3\n"
                + "1) Subway, Food ($12.12)\n"
                + "2) McDonalds, Food ($12.12)\n"
                + "3) Waffle House, Food ($12.12)\n";

        // Verify
        Assert.assertEquals(expectedOutput, output);
        ArgumentCaptor<PartialOrder> argumentCaptor = ArgumentCaptor.forClass(PartialOrder.class);
        Mockito.verify(partialOrderDAO, Mockito.times(1)).save(argumentCaptor.capture());
        Assert.assertEquals(TransactionPhase.CHOOSING_LOCATION, argumentCaptor.getValue().getTransactionPhase());
        Assert.assertEquals(providerLocationList, argumentCaptor.getValue().getProviderLocationsForSelection());
    }

    @Test
    public void processMessage_providingAddress_badLocation() {
        // Setup
        String message = "5540 S Hyde Park Blvd, Chicago IL, 60637";
        partialOrder.setTransactionPhase(TransactionPhase.PROVIDING_ADDRESS);

        Mockito.when(partialOrderDAO.get(user)).thenReturn(partialOrder);
        Mockito.when(providerLocationService.providersNearCustomer(Mockito.anyString(), Mockito.anyInt()))
                .thenThrow(new GeoCodingException("Unable to resolve address"));

        // Execute
        String output = orderProcessor.processMessage(user, message);
        String expectedOutput = "Unable to resolve address";

        // Verify
        Assert.assertEquals(expectedOutput, output);
        ArgumentCaptor<PartialOrder> argumentCaptor = ArgumentCaptor.forClass(PartialOrder.class);
        Mockito.verify(partialOrderDAO, Mockito.times(1)).save(argumentCaptor.capture());
        Assert.assertEquals(TransactionPhase.PROVIDING_ADDRESS, argumentCaptor.getValue().getTransactionPhase());
    }

    @Test
    public void processMessage_choosingLocation_success() {
        // Setup
        String message = "2";
        partialOrder.setTransactionPhase(TransactionPhase.CHOOSING_LOCATION);
        partialOrder.setProviderLocationsForSelection(providerLocationList);
        partialOrder.setMenuItemsForSelection(menuItems);

        Mockito.when(partialOrderDAO.get(user)).thenReturn(partialOrder);

        // Execute
        String output = orderProcessor.processMessage(user, message);
        String expectedOutput = "You've chosen McDonalds, Food ($12.12).\n"
                + "Respond with CONFIRM to place this order or REDO to change selected provider.";

        // Verify
        Assert.assertEquals(expectedOutput, output);
        ArgumentCaptor<PartialOrder> argumentCaptor = ArgumentCaptor.forClass(PartialOrder.class);
        Mockito.verify(partialOrderDAO, Mockito.times(1)).save(argumentCaptor.capture());
        Assert.assertEquals(TransactionPhase.CONFIRM_OR_CONTINUE, argumentCaptor.getValue().getTransactionPhase());
    }

    @Test
    public void processMessage_choosingLocation_badResponse() {
        // Setup
        String message = "4";
        partialOrder.setTransactionPhase(TransactionPhase.CHOOSING_LOCATION);
        partialOrder.setProviderLocationsForSelection(providerLocationList);
        partialOrder.setMenuItemsForSelection(menuItems);

        Mockito.when(partialOrderDAO.get(user)).thenReturn(partialOrder);

        // Execute
        String output = orderProcessor.processMessage(user, message);
        String expectedOutput = "Invalid input!\n"
                + "Please provide a number between 1-3\n"
                + "1) Subway, Food ($12.12)\n"
                + "2) McDonalds, Food ($12.12)\n"
                + "3) Waffle House, Food ($12.12)\n";

        // Verify
        Assert.assertEquals(expectedOutput, output);
        ArgumentCaptor<PartialOrder> argumentCaptor = ArgumentCaptor.forClass(PartialOrder.class);
        Mockito.verify(partialOrderDAO, Mockito.times(1)).save(argumentCaptor.capture());
        Assert.assertEquals(TransactionPhase.CHOOSING_LOCATION, argumentCaptor.getValue().getTransactionPhase());
    }

    // TODO: 11/20/16 Add testing for choosing menu items when this functionality it added to the service.


    @Test
    public void processMessage_confirmOrRedo() {
        // Setup
        String message = "CONFIRM";

        user.setId(1L);
        partialOrder.setUser(user);
        partialOrder.setTransactionPhase(TransactionPhase.CONFIRM_OR_CONTINUE);
        partialOrder.setProviderLocationsForSelection(providerLocationList);
        partialOrder.setMenuItemsForSelection(menuItems);
        partialOrder.setChosenProvider(providerLocationList.get(1));
        partialOrder.getOrderItems().add(new OrderItem((long)1, menuItems.get(0).getPrice(), menuItems.get(0)));

        Mockito.when(partialOrderDAO.get(user)).thenReturn(partialOrder);
        Mockito.when(orderDAO.save(Mockito.any())).thenReturn(null);

        // Execute
        String output = orderProcessor.processMessage(user, message);
        String expectedOutput = "Your order has been confirmed.";

        // Verify
        Assert.assertEquals(expectedOutput, output);
        Mockito.verify(partialOrderDAO.delete(partialOrder), Mockito.times(1));
        ArgumentCaptor<Order> argumentCaptor = ArgumentCaptor.forClass(Order.class);
        Mockito.verify(orderDAO, Mockito.times(1)).save(argumentCaptor.capture());
//        Assert.assertEquals(TransactionPhase.CHOOSING_LOCATION, argumentCaptor.getValue().getTransactionPhase());
    }

}
