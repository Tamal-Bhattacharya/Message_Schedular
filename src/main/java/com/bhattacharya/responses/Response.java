package com.bhattacharya.responses;

public class Response {
    private String msgID;
    private long sendTo;
    private String status;
    private String msg;
    
    public Response(String msgID, long sendTo, String status, String msg) {
        this.msgID = msgID;
        this.sendTo = sendTo;
        this.status = status;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Response [msg=" + msg + ", msgID=" + msgID + ", sendTo=" + sendTo + ", status=" + status + "]";
    }
    
    
}
