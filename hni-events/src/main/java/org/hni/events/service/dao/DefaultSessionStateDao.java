package org.hni.events.service.dao;

import org.hni.events.service.om.SessionState;

import java.util.HashMap;
import java.util.Map;

public class DefaultSessionStateDao implements SessionStateDao {

    //TODO get rid of this with real DAO
    Map<String, SessionState> sessionStateMap;

    public DefaultSessionStateDao() {
        sessionStateMap = new HashMap<>();
    }

    public SessionState get(String sessionId) {
        return sessionStateMap.get(sessionId);
    }

    public SessionState insert(SessionState state) {
        return sessionStateMap.put(state.getSessionId(), state);
    }

    public SessionState update(SessionState state) {
        return sessionStateMap.put(state.getSessionId(), state);
    }

    public SessionState delete(String sessionId) {
        return sessionStateMap.remove(sessionId);
    }
}
