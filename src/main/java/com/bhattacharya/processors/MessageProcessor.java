package com.bhattacharya.processors;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import com.bhattacharya.databases.MessageDAO;
import com.bhattacharya.entities.Message;
import com.bhattacharya.requests.PostFormURLEncoded;
import com.bhattacharya.responses.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageProcessor {

    Exception exception;

    @Autowired
    ValidatorProcess validator;

    @Autowired
    AuthenticationProcessor authenticator;

    @Autowired
    ResponseProcessor responseProcessor;

    @Autowired
    MessageDAO messageDAO;

    int error_code = -1;

    public Response msgProcessor(PostFormURLEncoded requestMessage){
        // test();
        Message message = null;
        try {
            
            error_code = authenticator.verifyClient(requestMessage);
            System.out.println("Authentication Error Code = " + error_code);
            if (error_code != 0) {
                throw exception;
            }

            System.out.println(requestMessage);
            error_code = validator.isMsgValidate(requestMessage);
            if (error_code != 0) {
                throw exception;
            }
            message = prepareMessage(requestMessage);
            error_code = storeMessage(message);

        } catch (Exception e) {
            if (error_code < 200) {
                return responseProcessor.responseGenerator(error_code, message);
            } else {
                return responseProcessor.responseGenerator(error_code, requestMessage);
            }
        }
        
        // manager.store();
        return responseProcessor.responseGenerator(error_code, requestMessage);
    }

    // private void test() {
    //     messageDAO.retrieveScheduledMessages().forEach(System.out::println);
    // }

    private int storeMessage(Message message) {
        try {
            int r = messageDAO.insert(message);
            System.out.println("r = " + r);
            if(r == 0){
                throw exception;
            }
        } catch (Exception e) {
            //TODO: handle exception
            return 400;
        }
        return 0;
    }

    private Message prepareMessage(PostFormURLEncoded requestMessage){
        Message message = new Message();
        message.setAccount_ID(requestMessage.getAccount_ID());
        message.setMsg(requestMessage.getMsg());
        message.setSend_To(requestMessage.getSendTo());
        message.setTimestamp(Timestamp.valueOf(LocalDateTime.parse(requestMessage.getTime().replace(" ", "T"))));
        message.setStatus(0);
        return message;
    }
}
