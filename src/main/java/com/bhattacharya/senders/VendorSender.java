package com.bhattacharya.senders;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
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
        // HttpHeaders headers = new HttpHeaders();
        // headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        String apikey = accountDetails.retrieve(message.getAccount_ID());
        System.out.println(apikey);
        // headers.add("apikey", apikey);

        // Map<String, Object> body = new HashMap<>();
        // body.put("channel", "whatsapp");
        // body.put("source", 917834811114l);
        // body.put("destination", Long.parseLong(message.getSend_To()));
        // body.put("message", "{\"type\":\"text\",\"text\":\""+message.getMsg()+"\"}");
        // body.put("src.name", "DRSTRA");

        // HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);
        
        // GupshupResponse responseEntity = template.postForObject(url, entity, GupshupResponse.class);

        // if (responseEntity.getStatus() == "submitted") {
        //     message.setStatus(1);
        // } else {
        //     message.setStatus(400);
        // }
        
        String mString = URLEncoder.encode("{\"type\":\"text\",\"text\":\""+message.getMsg()+"\"}", StandardCharsets.UTF_8) ;
        System.out.println("URL ENCODED MSG = " + mString);
        

        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(url)).header("Accept", "application/json").header("apikey", apikey).header("Content-Type", "application/x-www-form-urlencoded").method(
            "POST", HttpRequest.BodyPublishers.ofString("message="+mString+"&src.name=DRSTRA&channel=whatsapp&source=917834811114&destination="+message.getSend_To())).build();

            System.out.println("Test = " + "message="+mString+"&src.name=DRSTRA&channel=whatsapp&source=917834811114&destination="+message.getSend_To());
        try {
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            if (response.statusCode() == HttpStatus.ACCEPTED.value()) {
                message.setStatus(1);
                messageDAO.update(message);
            } else {
                message.setStatus(400);
            }
        } catch (IOException | InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        messageDAO.update(message);
    }
}
