package com.pdt.ssm.SaticScheduleTask;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
@Component
@EnableScheduling
public class SaticScheduleTask {

    @Scheduled(cron="0/60 * *  * * ? ")   //每60秒执行一次
    public void testTask() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(df.format(new Date())+"*********testTask每60秒执行一次");
    }

}
