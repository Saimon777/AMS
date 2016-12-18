package com.sam.fun.utils;

import com.google.gson.Gson;
import com.sam.fun.model.Mail;

import java.io.*;
import java.util.ArrayList;

public class GSONConverter {

    private Gson gson = new Gson();

    private void saveMail(Mail mail, String path) throws IOException {
        gson.toJson(mail, new FileWriter(path));
    }

    private Mail uploadMail(String path) throws FileNotFoundException {
        Mail result = gson.fromJson(new FileReader(path), Mail.class);
        return result;
    }

    public void saveMails(ArrayList<Mail> mails, String path) throws IOException {
        for (Mail mail : mails) {
            saveMail(mail, path);
        }
    }

    public ArrayList<Mail> uploadMails(String path) throws FileNotFoundException {
        File parentDir = new File(path);
        ArrayList<Mail> result = new ArrayList<Mail>();

        for (File file : parentDir.listFiles()) {
            result.add(gson.fromJson(new FileReader(file.getPath()), Mail.class));
        }
        return result;
    }

}
