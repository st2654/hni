package org.hni.order.service;

import org.hni.order.om.PartialOrder;
import org.hni.order.om.TransactionPhase;
import org.hni.provider.om.MenuItem;
import org.hni.provider.om.ProviderLocation;
import org.hni.provider.service.GeoCodingService;
import org.hni.provider.service.MenuService;
import org.hni.user.dao.UserDAO;
import org.hni.user.om.Address;
import org.hni.user.om.User;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DefaultOrderProcessor implements OrderProcessor {
    private static final double PROVIDER_SEARCH_RADIUS = 5;

    @Inject
    private UserDAO userDao;

    @Inject
    GeoCodingService geoService;

    @Inject
    MenuService menuService;

    public String processMessage(User user, String message) {
        //this partial order is the one I get for this user
        PartialOrder order = new PartialOrder();
        if (order == null) {
            order = new PartialOrder();
            order.setTransactionPhase(TransactionPhase.MEAL);
        }

        TransactionPhase phase = order.getTransactionPhase();
        String output = "";

        switch (phase) {
            case MEAL:
                break;
            case PROVIDING_ADDRESS:
                Optional<Address> address = geoService.resolveAddress(message);
                if (address.isPresent()) {
                    List<ProviderLocation> nearbyProviders = geoService.searchNearbyLocations(address.get(), PROVIDER_SEARCH_RADIUS);
                    order.setProviderLocationsForSelection(nearbyProviders);
                    List<MenuItem> items = new ArrayList<>();
                    for (ProviderLocation location : nearbyProviders) {
                        //TODO get the currently available menu items, not just first
                        items.add(menuService.with(location.getProvider()).iterator().next().getMenuItems().iterator().next());
                    }
                    order.setMenuItemsForSelection(items);
                    for (int i = 0 ; i < 3; i++) {
                        output += i + "." + nearbyProviders.get(i).getName() + "(" + items.get(i).getName() + ")";
                    }
                    order.setTransactionPhase(TransactionPhase.CHOOSING_LOCATION);
                } else {
                    output = "Invalid address, please try again";
                }
                break;
            case CHOOSING_LOCATION:
                //below may need to get moved in order to fit the format
                //Subway (Ham Sandwich), Taco Bell (Chicken Soft Tacos),  Panera (Steak Sandwich)
                try {
                    int index = Integer.parseInt(message);
                    if (index < 1 || index > 3) {
                        throw new IndexOutOfBoundsException();
                    }
                    ProviderLocation location = order.getProviderLocationsForSelection().get(index-1);
                    order.setChosenProvider(location);
                    order.setTransactionPhase(TransactionPhase.CONFIRM_OR_CONTINUE);
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    output = "Please provide a number between 1-3";
                }
                break;
            case CHOOSING_MENU_ITEM:
                //this is chosen w/ provider now I believe
                break;
            case CONFIRM_OR_CONTINUE:
                switch (message.toUpperCase()) {
                    case "CONFIRM":
                        //TODO create new completed order
                        break;
                    case "CONTINUE":
                        //TODO mark the partial order as having ometihng ordered
                        order.setTransactionPhase(TransactionPhase.CHOOSING_LOCATION);
                        break;
                    default:
                        output = "Please respond with CONFIRM or CONTINUE";
                }
                break;
            default:
                //shouldn't get here
        }

        return output;
    }

    public String processMessage(Long userId, String message) {
        return processMessage(userDao.get(userId), message);
    }

}
