package com.springboot.start;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yuanwenjie on 2017/5/27.
 * 执行定时任务
 */
@Component
public class ScheduledTasks {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    //@Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        System.out.println("The time is now {}" + dateFormat.format(new Date()));
    }

}
