package com.bhattacharya.processors;

import static org.junit.Assert.assertEquals;

import com.bhattacharya.requests.PostFormURLEncoded;

import org.junit.Test;

public class AuthenticationProcessorTest {
    @Test
    public void testVerifyClient() {
        assertEquals(0, new AuthenticationProcessor().verifyClient(new PostFormURLEncoded("admin", "admin", null, null, null)));
    }
}
