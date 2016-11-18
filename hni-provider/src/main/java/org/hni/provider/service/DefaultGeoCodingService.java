package org.hni.provider.service;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.apache.http.client.fluent.Request;
import org.hni.provider.om.ProviderLocation;
import org.hni.user.om.Address;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Optional;

/**
 * Created by walmart on 11/14/16.
 */
public class DefaultGeoCodingService implements GeoCodingService {

    private static final String GOOGLE_MAP_API_KEY="AIzaSyBCmt3RMn46CIpxUx20hmlpPbx6ws-lbkI";

    @Override
    public Optional<Address> resolveAddress(String address) {

        String targetURI= null;
        try {
            targetURI = "https://maps.googleapis.com/maps/api/geocode/json?address=" +
                    URLEncoder.encode(address,"UTF-8") + "&key=" + GOOGLE_MAP_API_KEY;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return Optional.empty();
        }

        try {
            String json = Request.Get(targetURI)
                    .execute()
                    .returnContent()
                    .asString();
            if(json != null) {
                DocumentContext ctx = JsonPath.parse(json);
                Double latitude = ctx.read("$.results[0].geometry.location.lat");
                Double longitude = ctx.read("$.results[0].geometry.location.lng");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
        return Optional.of(new Address());

    }

    @Override
    public List<ProviderLocation> searchNearbyLocations(Address address, double distanceMiles) {
        return null;
    }
}
