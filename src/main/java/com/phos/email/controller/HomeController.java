package com.phos.email.controller;

import com.phos.email.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Folarin on 03/10/2020
 */
@Controller
public class HomeController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/")
    public String home(){
        return "index";
    }

    @GetMapping("/search")
    public String search(@RequestParam(value = "q", required = false) String name, Model model){
        model.addAttribute("emails",emailService.search(name));
        return "index";
    }

}
