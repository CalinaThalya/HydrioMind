package br.com.fiap.hydriomind.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError() {
        // Aqui você pode retornar uma página de erro customizada
        return "error"; // O nome da página error.html
    }
}
