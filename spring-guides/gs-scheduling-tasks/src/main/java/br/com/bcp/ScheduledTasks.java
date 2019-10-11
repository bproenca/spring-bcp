package br.com.bcp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    /*
    The Scheduled annotation defines when a particular method runs. NOTE: This example uses fixedRate,
    which specifies the interval between method invocations measured from the start time of each invocation.
    There are other options, like fixedDelay, which specifies the interval between invocations measured from
    the completion of the task. You can also use @Scheduled(cron=". . .") expressions for more sophisticated task scheduling.
     */
    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
	log.info("The time is now {}", dateFormat.format(new Date()));
    }
}