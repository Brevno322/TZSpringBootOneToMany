package com.mukhaArturTZ.TZSpringBootOneToMany.util.PhoneBookValid;

public class PhoneBookErrorResponse {
    private String message;
    private Long timestamp;

    public PhoneBookErrorResponse(String message, Long timestamp) {
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
