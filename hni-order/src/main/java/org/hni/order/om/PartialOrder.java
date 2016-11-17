package org.hni.order.om;

import org.hni.provider.om.MenuItem;
import org.hni.provider.om.ProviderLocation;
import org.hni.user.om.User;

import java.util.List;

/**
 * Provides a state holder during ordering session.
 */
public class PartialOrder {

    private User user;
    private List<ProviderLocation> providerLocationsForSelection;
    private ProviderLocation chosenProvider;
    private List<MenuItem> menuItemsForSelection;
    private List<OrderItem> orderItems;
    private TransactionPhase transactionPhase;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<ProviderLocation> getProviderLocationsForSelection() {
        return providerLocationsForSelection;
    }

    public void setProviderLocationsForSelection(List<ProviderLocation> providerLocationsForSelection) {
        this.providerLocationsForSelection = providerLocationsForSelection;
    }

    public ProviderLocation getChosenProvider() {
        return chosenProvider;
    }

    public void setChosenProvider(ProviderLocation chosenProvider) {
        this.chosenProvider = chosenProvider;
    }

    public List<MenuItem> getMenuItemsForSelection() {
        return menuItemsForSelection;
    }

    public void setMenuItemsForSelection(List<MenuItem> menuItemsForSelection) {
        this.menuItemsForSelection = menuItemsForSelection;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public TransactionPhase getTransactionPhase() {
        return transactionPhase;
    }

    public void setTransactionPhase(TransactionPhase transactionPhase) {
        this.transactionPhase = transactionPhase;
    }
}
