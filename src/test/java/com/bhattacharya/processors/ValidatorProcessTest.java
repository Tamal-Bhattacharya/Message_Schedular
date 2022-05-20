package com.bhattacharya.processors;

import static org.junit.Assert.assertTrue;

import com.bhattacharya.requests.PostFormURLEncoded;

import org.junit.Test;

public class ValidatorProcessTest {

    @Test
    public void testIsMsgValidate() {
        assertTrue(new ValidatorProcess().isMsgValidate(new PostFormURLEncoded("admin","admin","Hi! User","2022-06-15 15:30:00", "919239252479")));
    }
}
