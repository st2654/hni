package org.hni.events.service.om;

/**
 * Created by walmart on 11/14/16.
 */
public class Event {

    private String eventStateId;

    private String phoneNumber;

    private String textMessage;

    private Event(String eventStateId, String phoneNumber, String textMessage) {
        this.eventStateId = eventStateId;
        this.phoneNumber = phoneNumber;
        this.textMessage = textMessage;
    }

    public static Event createEvent(String sessionId, String phoneNumber, String textMessage) {
        return new Event(sessionId, phoneNumber, textMessage);
    }

    public String getEventStateId() {
        return eventStateId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getTextMessage() {
        return textMessage;
    }

    public void setEventStateId(String eventStateId) {
        this.eventStateId = eventStateId;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventStateId='" + eventStateId + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", textMessage='" + textMessage + '\'' +
                '}';
    }
}
