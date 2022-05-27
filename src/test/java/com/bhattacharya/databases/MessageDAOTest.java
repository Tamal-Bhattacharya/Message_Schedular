package com.bhattacharya.databases;

import static org.junit.Assert.assertEquals;

import com.bhattacharya.entities.Message;

import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration
public class MessageDAOTest implements ApplicationContextAware{

    @Autowired
    MessageDAO messageDAO;

    ApplicationContext context;

    @Test
    public void testUpdate() {
        // context.getBean(MessageDAO.class);
        context.getAutowireCapableBeanFactory().autowireBean(messageDAO);
        Message message = messageDAO.retriveMessageById(1);
        message.setStatus(7);
        assertEquals(1, messageDAO.update(message));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        // TODO Auto-generated method stub
        this.context = applicationContext;
    }
}
