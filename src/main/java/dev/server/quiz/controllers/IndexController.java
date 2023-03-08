package dev.server.quiz.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    private final String TITULO = "SISTEMA DE FICHAS DE MONITOREO VIRTUAL A LA PRÁCTICA PEDAGÓGICA EN EL MARCO DE LA " +
            "EDUCACIÓN A DISTANCIA EBR" +
            "APRENDO EN CASA 2021";

    @RequestMapping("/index")
    public String listar(Model model, HttpServletRequest request) throws Exception {

        model.addAttribute("titulo", TITULO);
        model.addAttribute("request", request);

        return "index";
    }
}
