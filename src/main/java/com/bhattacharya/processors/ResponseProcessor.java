package com.bhattacharya.processors;

import com.bhattacharya.constants.ErrorCodes;
import com.bhattacharya.responses.MSGErrorResponse;
import com.bhattacharya.responses.Response;
import com.bhattacharya.entities.Message;
import com.bhattacharya.requests.PostFormURLEncoded;

import org.springframework.stereotype.Service;

@Service
public class ResponseProcessor {

    public Response responseGenerator(int errorCode, PostFormURLEncoded request){
        String error = ErrorCodes.codeToString(errorCode);
        MSGErrorResponse response = new MSGErrorResponse("N/A", request.getSendTo(), request.getMsg(), request.getTime(), "ERROR", error);
        System.out.println(response);
        return response;
    }

    public Response responseGenerator(int errorCode, Message message){
        String error = ErrorCodes.codeToString(errorCode);
        Response response = null;
        if (errorCode <= 10) {
            response = new Response(Integer.toString(message.getMessage_ID()), message.getSend_To(), message.getMsg(), message.getTimestamp().toString(), error);
        }
        return response;
    }
}
