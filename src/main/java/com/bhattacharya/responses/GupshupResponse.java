package com.bhattacharya.responses;

import java.io.Serializable;

public class GupshupResponse implements Serializable {
    private String status;
    private String messageID;
    
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getMessageID() {
        return messageID;
    }
    public void setMessageID(String messageID) {
        this.messageID = messageID;
    }

    
}
