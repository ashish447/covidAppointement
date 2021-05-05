package com.demo.engine;


import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    @SuppressWarnings("unused")
	@Autowired
    private HeadRequestService service;

    
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    @Scheduled(fixedRate = 7500)
    public void getHeadValue() throws InterruptedException, ExecutionException {
    	log.info("tcalling start ");
        
		
    	service.doHeadRequest();
		
    }
}