package com.bhattacharya.responses;

import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
public class MSGErrorResponse extends Response {
    public String error;

    public MSGErrorResponse(String msgID, String sendTo, String msg, String scheduled_Time, String status,
            String error) {
        super(msgID, sendTo, msg, scheduled_Time, status);
        this.error = error;
    }

    @Override
    public String toString() {
        return "MSGErrorResponse [error=" + error + ", msg=" + msg + ", msgID=" + msgID + ", scheduled_Time="
                + scheduled_Time + ", sendTo=" + sendTo + ", status=" + status + "]";
    }

}
