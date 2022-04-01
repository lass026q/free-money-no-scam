package com.example.freemoneynoscam.services;

import com.example.freemoneynoscam.services.Email;
import com.example.freemoneynoscam.repositories.DbHandler;

import java.util.ArrayList;
import java.util.Objects;

public class ValidateEmailService
{
    private final DbHandler db = new DbHandler();

    public boolean isEmailValid(String email)
    {

        int trunk = email.length() - email.replace("@", "").length();

        return trunk == 1 && email.contains(".");
    }

    public boolean isEmailExisting(String email)
    {
        ArrayList<Email> emails = getAddedEmails();
        int count = 0;
        for (Email e : emails)
        {
            if (Objects.equals(e.getEmailAddress(), email))
            {
                count++;
            }
        }
        return (count != 0);
    }

    public String addValidEmail(String email, boolean valid, boolean exists)
    {
        if (valid && !exists)
        {
            db.updateDb(email);
        }
        return (exists) ? "already in the database." : "invalid.";
    }

    public ArrayList<Email> getAddedEmails()
    {
        return db.loadAddresses();
    }
}