package org.hni.events.service;

import lombok.Data;

import java.util.Map;
import java.util.Optional;

/**
 * Created by walmart on 11/14/16.
 */
@Data
public class Event {
    private EventName name;
    private Optional<Integer> userId;
    private Map<String, String> attributes;
}
