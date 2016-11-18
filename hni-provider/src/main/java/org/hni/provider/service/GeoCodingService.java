package org.hni.provider.service;

import org.hni.provider.om.ProviderLocation;
import org.hni.user.om.Address;

import java.util.List;
import java.util.Optional;

/**
 * Created by walmart on 11/14/16.
 */
public interface GeoCodingService {

    Optional<Address> resolveAddress(String address);

    List<ProviderLocation> searchNearbyLocations(Address address, double distanceMiles);
}
