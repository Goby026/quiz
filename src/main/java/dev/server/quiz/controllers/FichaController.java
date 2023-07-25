package dev.server.quiz.controllers;

import dev.server.quiz.entities.Actividad;
import dev.server.quiz.services.*;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FichaController {

    private final static Logger logger = LoggerFactory.getLogger(FichaController.class);

    private final FichaService service;

    public FichaController(FichaService service) {
        this.service = service;
    }

    @RequestMapping("/fichas")
    public String listar(Model model, HttpSession session) throws Exception {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        session.setAttribute("username", username);

        model.addAttribute("activeLink", "/fichas");
        model.addAttribute("titulo", "Evaluaciones realizadas");
        model.addAttribute("fichas", service.listar());

        return "pages/fichas/index";
    }

//    @RequestMapping("/fichas/registro")
//    public String regItems(Model model) throws Exception {
//
//        model.addAttribute("titulo", "Valoración de items");
//        model.addAttribute("categorias", categoriaItemService.listar());
//        model.addAttribute("items", itemService.listar());
//
//        return "pages/fichas/fichaRegistro";
//    }

}
