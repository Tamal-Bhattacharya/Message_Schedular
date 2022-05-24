package com.bhattacharya.responses;

public class Response {
    public String msgID;
    public String sendTo;
    public String msg;
    public String scheduled_Time;
    public String status;
    // protected String error;

    public Response(String msgID, String sendTo, String msg, String scheduled_Time, String status) {
        this.msgID = msgID;
        this.sendTo = sendTo;
        this.msg = msg;
        this.scheduled_Time = scheduled_Time;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Response [msg=" + msg + ", msgID=" + msgID + ", scheduled_Time=" + scheduled_Time + ", sendTo=" + sendTo
                + ", status=" + status + "]";
    }
    
}
