package org.hni.order.service;

import org.hni.order.om.PartialOrder;
import org.hni.order.om.TransactionPhase;
import org.hni.provider.om.ProviderLocation;
import org.hni.provider.service.GeoCodingService;
import org.hni.provider.service.MenuService;
import org.hni.user.dao.UserDAO;
import org.hni.user.om.Address;
import org.hni.user.om.User;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
                }
                break;
            case CHOOSING_LOCATION:
                //below may need to get moved in order to fit the format
                //Subway (Ham Sandwich), Taco Bell (Chicken Soft Tacos),  Panera (Steak Sandwich)
                ProviderLocation location = order.getProviderLocationsForSelection().get(Integer.parseInt(message));
                order.setChosenProvider(location);
                order.setMenuItemsForSelection(menuService.with(location.getProvider()).stream()
                        .flatMap(menu -> menu.getMenuItems().stream())
                        .collect(Collectors.toList()));
                break;
            case CHOOSING_MENU_ITEM:
                //this is chosen w/ provider now I believe
                break;
            case CONFIRM_OR_CONTINUE:
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
