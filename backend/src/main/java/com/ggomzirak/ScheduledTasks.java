package com.ggomzirak;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ggomzirak.db.entity.User;
import com.ggomzirak.dto.TopUsers;
import com.ggomzirak.service.MainService;

import lombok.extern.java.Log;

@Component
@Log
public class ScheduledTasks {
	
	@Autowired
	private MainService service;

//    @Scheduled(fixedDelay = 60000)
//    public void runEvery60Sec(){
//    	log.info("runEvery30Sec");
//    	List<User> list = service.getTopUsers();
//    	service.setRanking(list);
//    	
//    }

    @Scheduled(cron = "0 59 23 ? * SUN")
    public void runAt9EveryDay(){
        log.info("sunday test");
        List<User> list = service.getTopUsers();
        service.setRanking(list);
    }
}
