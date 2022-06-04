package com.bhattacharya.processors;

import static org.junit.Assert.assertEquals;

import com.bhattacharya.requests.PostFormURLEncoded;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AuthenticationProcessorTest implements ApplicationContextAware{
    
    @Autowired
    AuthenticationProcessor processor;

    ApplicationContext context;

    @Test
    public void testVerifyClient() {
        context.getAutowireCapableBeanFactory().autowireBean(processor);
        assertEquals(0, processor.verifyClient(new PostFormURLEncoded("admin", "admin", null, null, null)));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        // TODO Auto-generated method stub
        this.context = applicationContext;
    }

    
}
