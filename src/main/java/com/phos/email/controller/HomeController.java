package com.phos.email.controller;

import com.phos.email.models.EmailModel;
import com.phos.email.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;

/**
 * Created by Folarin on 03/10/2020
 */
@Controller
public class HomeController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("emails", emailService.getAllEmails());
        return "index";
    }

    @GetMapping("/search")
    public String search(@RequestParam(value = "q", required = false) String name, Model model){
        model.addAttribute("emails",emailService.search(name));
        return "index";
    }

    @GetMapping("/test")
    public String test(Model model){
        model.addAttribute("emailModel",new EmailModel());
        return "test";
    }

    @PostMapping("/send")
    public String sendMail(@ModelAttribute EmailModel email, BindingResult bindingResult, Model model) throws ValidationException {

        if(bindingResult.hasErrors()){
            throw new ValidationException("Email is not valid");
        }
        emailService.send(email);

        return "redirect:/index";
    }

}
