package org.hni.order.om;

import org.hni.common.om.Persistable;
import org.hni.provider.om.MenuItem;
import org.hni.provider.om.ProviderLocation;
import org.hni.user.om.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Provides a state holder during ordering session.
 */
@Entity
@Table(name = "partial_orders")
public class PartialOrder implements Persistable, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private User user;

    @OneToMany
    @JoinColumn(name="provider_location_id", referencedColumnName = "id")
    private List<ProviderLocation> providerLocationsForSelection;

    @OneToMany
    @JoinColumn(name="menu_item_id", referencedColumnName = "id")
    private List<MenuItem> menuItemsForSelection;

    @OneToMany
    @JoinColumn(name="order_item_id", referencedColumnName = "id")
    private Set<OrderItem> orderItems;

    @ManyToOne
    @JoinColumn(name="chosen_provider_id", referencedColumnName = "id")
    private ProviderLocation chosenProvider;

    @Column(name = "transaction_phase")
    private TransactionPhase transactionPhase;

    @Column(name = "address")
    private String address;

    public PartialOrder() {
        this.providerLocationsForSelection = new ArrayList<>();
        this.menuItemsForSelection = new ArrayList<>();
        this.orderItems = new HashSet<>();
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public TransactionPhase getTransactionPhase() {
        return transactionPhase;
    }

    public void setTransactionPhase(TransactionPhase transactionPhase) {
        this.transactionPhase = transactionPhase;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
