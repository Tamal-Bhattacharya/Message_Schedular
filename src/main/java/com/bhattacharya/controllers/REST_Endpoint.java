package com.bhattacharya.controllers;

// import java.util.logging.Logger;

import com.bhattacharya.processors.MessageProcessor;
import com.bhattacharya.requests.PostFormURLEncoded;
import com.bhattacharya.responses.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class REST_Endpoint {

    private static final Logger LOGGER = LogManager.getLogger(REST_Endpoint.class); 
    // private static final Logger LOGGER = Logger.getLogger("REST_Endpoint Logger");

    @Autowired
    private MessageProcessor messageProcessor;
    
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/send", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response sendMessage(PostFormURLEncoded postFormURLEncoded){
        LOGGER.info("Started New Send Message Request");

        return messageProcessor.msgProcessor(postFormURLEncoded);
    }
}
