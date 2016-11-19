package org.hni.events.service.dao;

import org.hni.events.service.om.SessionState;

public interface SessionStateDAO {

    SessionState get(String sessionId);

    SessionState insert(SessionState state);

    SessionState update(SessionState state);

    SessionState delete(String sessionId);

}
