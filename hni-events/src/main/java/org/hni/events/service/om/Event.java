package org.hni.events.service.om;

/**
 * Created by walmart on 11/14/16.
 */
public class Event {

    // incoming media type
    // this is text/plain for SMS
    // but for the future it could be json or xml if the endpoint was called from web UI
    private String mediaType;

    private String phoneNumber;

    private String textMessage;

    private Event(String mediaType, String phoneNumber, String textMessage) {
        this.mediaType = mediaType;
        this.phoneNumber = phoneNumber;
        this.textMessage = textMessage;
    }

    public static Event createEvent(String mediaType, String phoneNumber, String textMessage) {
        return new Event(mediaType, phoneNumber, textMessage);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getTextMessage() {
        return textMessage;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    @Override
    public String toString() {
        return "Event{" +
                "mediaType='" + mediaType + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", textMessage='" + textMessage + '\'' +
                '}';
    }
}
