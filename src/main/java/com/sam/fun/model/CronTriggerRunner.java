package com.sam.fun.model;

import org.quartz.*;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.CronTriggerImpl;

public class CronTriggerRunner {

    public void startSendingMails() throws SchedulerException {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();

        long currentTime = System.currentTimeMillis();

        JobDetail jobDetail = new JobDetailImpl("jobDetail", "jobDetailGroup", SendingMailJob.class);
        CronTrigger cronTrigger = new CronTriggerImpl("cronTrigger", "triggerGroup");

        CronExpression cronExpression = new CronExpression()
    }

}
