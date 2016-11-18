package org.hni.events.service;

import org.hni.user.om.User;

import java.util.function.Consumer;

/**
 * Created by walmart on 11/14/16.
 */
public interface TextMessagingService {

    /**
     * Send a text message to a user
     * @param user
     * @param message
     */
    public void sendTextMessage(User user, String message);

    /**
     * Register a consumer to be notified of incoming texts
     * @param messageConsumer
     */
    public void registerConsumer(Consumer<String> messageConsumer);
}
