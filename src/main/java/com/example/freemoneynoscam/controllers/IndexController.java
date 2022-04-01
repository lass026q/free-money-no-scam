package com.example.freemoneynoscam.controllers;

import com.example.freemoneynoscam.services.Email;
import com.example.freemoneynoscam.services.ValidateEmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;

@Controller
public class IndexController
{
    private final ValidateEmailService ves = new ValidateEmailService();
    private String email, invalid;

    @GetMapping("/index")
    public String start()
    {
        return "index";
    }

    @PostMapping("/index")
    public String addEmail(WebRequest emailFromForm)
    {
        email = emailFromForm.getParameter("email");
        assert email != null;
        boolean validEmail = ves.isEmailValid(email);
        boolean emailExists = ves.isEmailExisting(email);
        invalid = ves.addValidEmail(email, validEmail, emailExists);
        return (validEmail && !emailExists) ? "redirect:/confirmation" : "redirect:/rejection";
    }

    @GetMapping("/confirmation")
    public String emailAdded(Model model)
    {
        model.addAttribute("email", email);
        return "confirmation";
    }

    @GetMapping("/rejection")
    public String emailInvalid(Model model)
    {
        model.addAttribute("email", email);
        model.addAttribute("reason", invalid);
        return "rejection";
    }


    @GetMapping("/list")
    public String listEmails(Model model)
    {
        ArrayList<Email> emails = ves.getAddedEmails();
        model.addAttribute("emailList", emails);
        return "list";
    }
}