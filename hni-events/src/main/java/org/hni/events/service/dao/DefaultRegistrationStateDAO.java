package org.hni.events.service.dao;

import org.hni.events.service.om.RegistrationState;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DefaultRegistrationStateDAO implements RegistrationStateDAO {

    //TODO get rid of this with real DAO
    Map<String, RegistrationState> sessionStateMap;

    public DefaultRegistrationStateDAO() {
        sessionStateMap = new HashMap<>();
    }

    public RegistrationState get(String sessionId) {
        return sessionStateMap.get(sessionId);
    }

    public RegistrationState insert(RegistrationState state) {
        return sessionStateMap.put(state.getSessionId(), state);
    }

    public RegistrationState update(RegistrationState state) {
        return sessionStateMap.put(state.getSessionId(), state);
    }

    public RegistrationState delete(String sessionId) {
        return sessionStateMap.remove(sessionId);
    }
}
