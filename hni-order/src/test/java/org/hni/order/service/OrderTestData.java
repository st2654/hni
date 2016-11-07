package org.hni.order.service;

import org.hni.order.om.Order;
import org.hni.order.om.OrderItem;
import org.hni.provider.om.MenuItem;
import org.hni.provider.om.ProviderLocation;

import java.util.Date;

/**
 * Created by chugh13 on 11/6/16.
 */
public class OrderTestData {

    public static Order getTestOrder() {
        Order order = new Order();

        order.setCreatedById(1L);
        order.setOrderDate(new Date());
        order.setProviderLocation( new ProviderLocation(1L));
        order.getOrderItems().add(new OrderItem(1L, 8.99, new MenuItem(1L)));
        order.getOrderItems().add(new OrderItem(2L, 7.99, new MenuItem(2L)));

        return order;
    }


}
