package com.example.projet_2_spring_mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccueilController {

    @GetMapping("/")
    public String accueil() {
        return "accueil"; // -> /WEB-INF/views/accueil.jsp
    }
}
