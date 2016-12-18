package com.sam.fun.model;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class SendingMailJob implements Job {

    Mail mail;

    public SendingMailJob(Mail mail) {
        this.mail = mail;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //TODO send mail
    }
}
