package bz.nimitz.ybr.demo;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import bz.nimitz.ybr.demo.service.MyService;

@Component
@Configuration
@EnableScheduling
@EnableAsync
//@ConditionalOnProperty(name = "scheduler.enabled", matchIfMissing = true)
public class ScheduledTasks {

	
	private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    MyService myService;

	@Scheduled(fixedRate = 300000)
    @Async
	public void reportCurrentTime() {
        myService.loadDataFromWeb();
		logger.info("Load data at: {}", dateFormat.format(new Date()));
	}

}
