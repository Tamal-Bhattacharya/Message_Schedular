package com.bhattacharya.responses;

import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
public class MSGErrorResponse extends Response {
    private String error;

    public MSGErrorResponse(String msgID, long sendTo, String status, String msg, String error) {
        super(msgID, sendTo, status, msg);
        this.error = error;
    }

    @Override
    public String toString() {
        return "ErrorResponse [error=" + error + "]";
    }

}
