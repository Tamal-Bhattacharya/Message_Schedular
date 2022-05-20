package com.bhattacharya.processors;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import org.apache.tomcat.util.codec.binary.Base64;

public class AuthenticationProcessor {

    private Exception exception;

    String userName2 = "admin";
    String password2;

    {
        try {
            String test = "admin";
            String password = new BigInteger(1, MessageDigest.getInstance("SHA-256").digest(Base64.encodeBase64(test.getBytes()))).toString();
            String password2 = Base64.encodeBase64String(password.getBytes());
            this.password2 = password2;
        } catch (Exception e) {
            //TODO: handle exception
        }
        
    }
    public int verifyClient(String userName, String password){
        try {
            String encodedString = Base64.encodeBase64String(password.getBytes(StandardCharsets.UTF_8));
            String encriptedString = new BigInteger(1, MessageDigest.getInstance("SHA-256").digest(encodedString.getBytes())).toString();
            String lock = Base64.encodeBase64String(encriptedString.getBytes());
            System.out.println(password2);

            if (!lock.equals(this.password2)) {
                throw exception;
            }
        } catch (Exception e) {
            return 511;
        }
        return 0;
    }
}
