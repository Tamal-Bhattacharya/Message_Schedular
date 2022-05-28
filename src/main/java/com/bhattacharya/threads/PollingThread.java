package com.bhattacharya.threads;

import com.bhattacharya.databases.MessageDAO;
import com.bhattacharya.senders.VendorSender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("thread")
public class PollingThread implements Runnable{

    @Autowired
    MessageDAO messageDAO;

    @Autowired
    VendorSender sender;

    public PollingThread() {
        // run();
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(3000);
                sender.sendBatch(messageDAO.retrieveScheduledMessages());
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
    }

    
    
}
