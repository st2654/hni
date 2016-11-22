package org.hni.events.service.dao;

import org.hni.events.service.om.SessionState;

public interface SessionStateDAO {

    SessionState getByPhoneNumber(String phoneNumber);

    SessionState insert(SessionState state);

    SessionState update(SessionState state);

    SessionState delete(String sessionId);

}
