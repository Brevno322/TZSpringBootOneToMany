package com.mukhaArturTZ.TZSpringBootOneToMany.util.ContactValid;

public class ContactErrorResponse {

    private String message;
    private Long timestamp;

    public ContactErrorResponse(String message, Long timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
