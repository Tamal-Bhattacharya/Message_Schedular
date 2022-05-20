package com.bhattacharya.responses;

public class ScheduledResponse extends Response {
    private String Scheduled_Time;

    public ScheduledResponse(String msgID, long sendTo, String status, String msg, String scheduled_Time) {
        super(msgID, sendTo, status, msg);
        Scheduled_Time = scheduled_Time;
    }

    @Override
    public String toString() {
        return "ScheduledResponse [Scheduled_Time=" + Scheduled_Time + "]";
    }

    
}
