package com.project.ecommerce_youtube.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HomeController {

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return ResponseEntity.ok("Hello, " + username + "! This is a protected resource.");
    }

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
