package org.hni.order.dao;

import junit.framework.Assert;
import org.hni.order.om.OrderItem;
import org.hni.order.om.PartialOrder;
import org.hni.order.om.TransactionPhase;
import org.hni.provider.om.MenuItem;
import org.hni.provider.om.ProviderLocation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Arrays;

/**
 * Verifies that the PartialOrder DAO is working
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:test-applicationContext.xml"} )
@Transactional
public class TestDefaultPartialOrderDAO {

    @Inject
    private DefaultPartialOrderDAO partialOrderDAO;

    @Test
    public void testPartialOrderDao_verify() {
        PartialOrder partialOrder = new PartialOrder();

        partialOrder.setChosenProvider(new ProviderLocation(1L));
        partialOrder.setMenuItemsForSelection(Arrays.asList(new MenuItem(1L)));
        partialOrder.setOrderItems(Arrays.asList(new OrderItem(1L)));
        partialOrder.setProviderLocationsForSelection(Arrays.asList(new ProviderLocation(2L)));
        partialOrder.setTransactionPhase(TransactionPhase.CONFIRM_OR_CONTINUE);

        partialOrderDAO.save(partialOrder);
        PartialOrder orderReceived = (PartialOrder) partialOrderDAO.get(1L);

        Assert.assertEquals(orderReceived, partialOrder);
        Assert.assertEquals(orderReceived.getChosenProvider(), partialOrder.getChosenProvider());
        Assert.assertEquals(orderReceived.getMenuItemsForSelection(), partialOrder.getMenuItemsForSelection());
        Assert.assertEquals(orderReceived.getOrderItems(), partialOrder.getOrderItems());
        Assert.assertEquals(orderReceived.getProviderLocationsForSelection(), partialOrder.getProviderLocationsForSelection());
        Assert.assertEquals(orderReceived.getTransactionPhase(), partialOrder.getTransactionPhase());
    }
}
