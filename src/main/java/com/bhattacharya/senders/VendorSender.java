package com.bhattacharya.senders;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bhattacharya.databases.AccountDetailsDAO;
import com.bhattacharya.databases.MessageDAO;
import com.bhattacharya.entities.Message;
import com.bhattacharya.responses.GupshupResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class VendorSender {

    @Autowired
    AccountDetailsDAO accountDetails;

    @Autowired
    MessageDAO messageDAO;

    private final RestTemplate template = new RestTemplateBuilder().build();

    public void sendBatch(List<Message> messages){
        for (Message message : messages) {
            send(message);
        }
        return;
    }

    public void send(Message message){
        String url = "https://api.gupshup.io/sm/api/v1/msg";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        String apikey = accountDetails.retrieve(message.getAccount_ID());
        headers.add("apikey", apikey);

        Map<String, Object> body = new HashMap<>();
        body.put("channel", "whatsapp");
        body.put("source", 917834811114l);
        body.put("destination", Long.parseLong(message.getSend_To()));
        body.put("message", "{\"type\":\"text\",\"text\":\""+message.getMsg()+"\"}");
        body.put("src.name", "DRSTRA");

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);
        
        GupshupResponse responseEntity = template.postForObject(url, entity, GupshupResponse.class);

        if (responseEntity.getStatus() == "submitted") {
            message.setStatus(1);
        } else {
            message.setStatus(400);
        }
        messageDAO.update(message);
    }
}
