package com.phos.email.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Folarin on 03/10/2020
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String home(){
        return "index";
    }

}
