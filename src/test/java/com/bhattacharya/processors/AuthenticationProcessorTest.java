package com.bhattacharya.processors;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AuthenticationProcessorTest {
    @Test
    public void testVerifyClient() {
        assertEquals(0, new AuthenticationProcessor().verifyClient("admin", "admin"));
    }
}
