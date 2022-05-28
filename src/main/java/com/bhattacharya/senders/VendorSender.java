package com.bhattacharya.senders;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.bhattacharya.databases.AccountDetailsDAO;
import com.bhattacharya.databases.MessageDAO;
import com.bhattacharya.entities.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class VendorSender {

    @Autowired
    AccountDetailsDAO accountDetails;

    @Autowired
    MessageDAO messageDAO;

    public void sendBatch(List<Message> messages){
        for (Message message : messages) {
            send(message);
        }
        return;
    }

    public void send(Message message){
        String url = "https://api.gupshup.io/sm/api/v1/msg";
        String apikey = accountDetails.retrieve(message.getAccount_ID());
        System.out.println(apikey);
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
            } else {
                message.setStatus(400);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        int r = messageDAO.update(message);
        System.out.println("r = " + r);
    }
}
