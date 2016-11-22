package org.hni.events.service;

import org.hni.events.service.dao.EventStateDao;
import org.hni.events.service.om.Event;
import org.hni.events.service.om.EventName;
import org.hni.events.service.om.EventState;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import java.util.HashMap;
import java.util.Map;

@Component
public class EventRouter {

    @Inject
    private EventStateDao eventStateDao;

    @Inject
    private RegisterService registerService;

    private Map<EventName, EventService> eventServiceMap;

    @PostConstruct
    void init() {
        eventServiceMap = new HashMap<>();
        eventServiceMap.put(EventName.REGISTER, registerService);
    }

    @Transactional(rollbackFor = Exception.class)
    public String handleEvent(final Event event) {

        final String phoneNumber = event.getPhoneNumber();

        // Lookup routing state by phone number
        final EventState state = eventStateDao.byPhoneNumber(phoneNumber);

        EventName eventName = parseKeyWordToEventName(event.getTextMessage());
        if (eventName == null) {
            if (state == null) {
                // not a keyword, nor having a in progress workflow
                return "Unknown keyword " + event.getTextMessage();
            } else {
                eventName = state.getEventName();
            }
        } else {
            if (state != null) {
                // clear previous workflow as a new keyword is received
                eventStateDao.delete(state);
            }

            // Insert the new event
            eventStateDao.insert(new EventState(eventName, event.getPhoneNumber()));
        }

        // Route the event to the appropriate service
        return eventServiceMap.get(eventName).handleEvent(event);
    }

    private EventName parseKeyWordToEventName(final String keyWord) {
        switch (keyWord.toUpperCase()) {
            // Suport multiple keywords for enrollment
            case "SIGNUP":
            case "ENROLL":
            case "REGISTER":
                return EventName.REGISTER;
            // Support multiple Keywords for ordering
            case "MEAL":
            case "ORDER":
                return EventName.MEAL;
            case "GIVE":
            case "DONATE":
                return EventName.DONATE;
            default: // No valid keyword entered
                return null;
        }
    }
}
