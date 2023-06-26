package dev.server.quiz.controllers;

import dev.server.quiz.entities.Actividad;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ConfiguracionController {

    @RequestMapping("/config")
    public String listar(Model model) throws Exception {

        model.addAttribute("titulo", "Evaluaciones realizadas");
//        model.addAttribute("fichas", service.listar());

        return "pages/configuracion/index";
    }

}
