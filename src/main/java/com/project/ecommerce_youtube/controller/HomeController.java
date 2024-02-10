package com.project.ecommerce_youtube.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        // Return the name of the home page template, home tu banayega na react se wohi aayega kya idhar??
        return "home";
    }

    @GetMapping("/about")
    public String about() {
        // Return the name of the about page template
        return "about";
    }
    @GetMapping("/contactus")
    public String contactUs(){

        return "contactus";
    }

    @GetMapping("/letushelpyou")
    public String letUsHelpYou(){

        return "letushelpyou";
    }

}
