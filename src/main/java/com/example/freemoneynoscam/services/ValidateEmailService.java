package com.example.freemoneynoscam.services;

import com.example.freemoneynoscam.services.Email;
import com.example.freemoneynoscam.repositories.DbHandler;

import java.util.ArrayList;

public class ValidateEmailService
{
    private final DbHandler db = new DbHandler();

    public boolean isEmailValid(String email)
    {

        int trunk = email.length() - email.replace("@", "").length();

        return trunk == 1 && email.contains(".");
    }

    public void addValidEmail(String email)
    {
        db.updateDb(email);
    }

    public ArrayList<Email> getAddedEmails()
    {
        return db.loadAddresses();
    }
}
