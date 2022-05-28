package com.bhattacharya;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.bhattacharya.threads.PollingThread;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * Hello world!
 *
 */

@ComponentScan
@SpringBootApplication
public class App 
{
    
    @Autowired
    PollingThread thread ;

    ConfigurableApplicationContext context;

    static ExecutorService threadeExecutor = Executors.newCachedThreadPool();
    public static void main( String[] args )
    {
        App app = new App();

        app.context = SpringApplication.run(App.class, args);
        System.out.println( "Hello World!" );
        
        // thread.run();
        app.thread = (PollingThread) app.context.getBean("thread");
        threadeExecutor.execute(app.thread);
    }
}
