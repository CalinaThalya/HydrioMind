package br.com.fiap.hydriomind.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "home"; // Isso deve corresponder ao nome do arquivo home.html
    }
}
