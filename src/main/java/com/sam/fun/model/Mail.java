package com.sam.fun.model;

import javafx.beans.property.*;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;

public class Mail {

    private StringProperty sender;
    private ArrayList<StringProperty> recipients;
    private StringProperty subject;
    private String text;
    private ArrayList<File> attachments;

    private BooleanProperty inProcess;
    private String cronSec;
    private String cronMin;
    private String cronHours;
    private String cronDay;
    private String cronMonth;
    private String cronDayOfWeek;
    private String cronYear;

    public Mail() {
        this.sender = new SimpleStringProperty();
        this.recipients = new ArrayList<>();
        this.subject = new SimpleStringProperty();
        this.text = "";
        this.inProcess = new SimpleBooleanProperty(false);
        /*this.cronSec = "*";
        this.cronMin = "*";
        this.cronHours = "*";
        this.cronDay = "*";
        this.cronMonth = "*";
        this.cronDayOfWeek = "*";
        this.cronYear = "*";*/
    }

    public Mail(String sender, ArrayList<StringProperty> recipients, String subject,
                String text, ArrayList<File> attachments, boolean inProcess,
                String cronSec, String cronMin, String cronHours, String cronDay,
                String cronMonth, String cronDayOfWeek,String cronYear) {
        this.sender = new SimpleStringProperty(sender);
        this.recipients = recipients;
        this.subject = new SimpleStringProperty(subject);
        this.text = text;
        this.attachments = attachments;
        this.inProcess = new SimpleBooleanProperty(inProcess);
        this.cronSec = cronSec;
        this.cronMin = cronMin;
        this.cronHours = cronHours;
        this.cronDay = cronDay;
        this.cronMonth = cronMonth;
        this.cronDayOfWeek = cronDayOfWeek;
        this.cronYear = cronYear;
    }

    public StringProperty getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender.set(sender);
    }

    public ArrayList<StringProperty> getRecipients() {
        return recipients;
    }

    public String getRecipientsInString() {
        StringBuilder result = new StringBuilder();
        for (StringProperty recipient: getRecipients()) {
            result.append(recipient.getValue() + "; ");
        }
        return result.toString();
    }

    public void setRecipients(ArrayList<StringProperty> recipients) {
        this.recipients = recipients;
    }

    public StringProperty getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject.set(subject);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ArrayList<File> getAttachments() {
        return attachments;
    }

    public void setAttachments(ArrayList<File> attachments) {
        this.attachments = attachments;
    }

    public BooleanProperty isInProcess() {
        BooleanProperty result = new SimpleBooleanProperty(inProcess.get());
        return result;
    }

    public void setInProcess(boolean inProcess) {
        this.inProcess.set(inProcess);
    }

    public String getCronSec() {
        return cronSec;
    }

    public void setCronSec(String cronSec) {
        this.cronSec = cronSec;
    }

    public String getCronMin() {
        return cronMin;
    }

    public void setCronMin(String cronMin) {
        this.cronMin = cronMin;
    }

    public String getCronHours() {
        return cronHours;
    }

    public void setCronHours(String cronHours) {
        this.cronHours = cronHours;
    }

    public String getCronDay() {
        return cronDay;
    }

    public void setCronDay(String cronDay) {
        this.cronDay = cronDay;
    }

    public String getCronMonth() {
        return cronMonth;
    }

    public void setCronMonth(String cronMonth) {
        this.cronMonth = cronMonth;
    }

    public String getCronDayOfWeek() {
        return cronDayOfWeek;
    }

    public void setCronDayOfWeek(String cronDayOfWeek) {
        this.cronDayOfWeek = cronDayOfWeek;
    }

    public String getCronYear() {
        return cronYear;
    }

    public void setCronYear(String cronYear) {
        this.cronYear = cronYear;
    }
}
