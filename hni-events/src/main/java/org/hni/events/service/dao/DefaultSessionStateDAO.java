package org.hni.events.service.dao;

import org.hni.events.service.om.SessionState;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DefaultSessionStateDAO implements SessionStateDAO {

    //TODO get rid of this with real DAO
    Map<String, SessionState> sessionStateMap;

    public DefaultSessionStateDAO() {
        sessionStateMap = new HashMap<>();
    }

    public SessionState getByPhoneNumber(String phoneNumber) {
        return sessionStateMap.get(phoneNumber);
    }

    public SessionState insert(SessionState state) {
        return sessionStateMap.put(state.getPhoneNumber(), state);
    }

    public SessionState update(SessionState state) {
        return sessionStateMap.put(state.getPhoneNumber(), state);
    }

    public SessionState delete(String phoneNumber) {
        return sessionStateMap.remove(phoneNumber);
    }
}
