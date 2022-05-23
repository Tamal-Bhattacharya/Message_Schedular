package com.bhattacharya.processors;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.regex.Pattern;

import com.bhattacharya.databases.AccountCredentialDAO;
import com.bhattacharya.entities.Account_Credential;
import com.bhattacharya.requests.PostFormURLEncoded;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationProcessor {

    private Exception exception;

    @Autowired
    AccountCredentialDAO credentialDAO;

    // String userName2 = "admin";
    // String password2;

    // {
    //     try {
    //         String test = "admin";
    //         String password = new BigInteger(1, MessageDigest.getInstance("SHA-256").digest(Base64.encodeBase64(test.getBytes()))).toString();
    //         String password2 = Base64.encodeBase64String(password.getBytes());
    //         this.password2 = password2;
    //     } catch (Exception e) {
    //         //TODO: handle exception
    //     }
        
    // }
    public int verifyClient(PostFormURLEncoded message){
        try {

            if (message.getUserName().isEmpty()) {
                return 411;
            }
    
            if (!message.getPassword().isEmpty()) {
                if (!Pattern.matches("[a-zA-Z@._0-9]{5,32}", message.getPassword())) {
                    return 412;
                }
            } else {
                return 412;
            }


            String encodedString = Base64.encodeBase64String(message.getPassword().getBytes(StandardCharsets.UTF_8));
            String encriptedString = new BigInteger(1, MessageDigest.getInstance("SHA-256").digest(encodedString.getBytes())).toString();
            String lock = Base64.encodeBase64String(encriptedString.getBytes());
            System.out.println(lock);

            Account_Credential credential = credentialDAO.retrieve(message.getUserName());

            if (!lock.equals(credential.getPassword())) {
                throw exception;
            }

            message.setAccount_ID(credential.getAccount_ID());

        } catch (Exception e) {
            return 511;
        }
        return 0;
    }
}
