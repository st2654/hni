package org.hni.order.service;

import org.hni.user.om.User;

public interface OrderProcessor {

    String processMessage(User user, String message);

    String processMessage(Long userId, String message);
}
