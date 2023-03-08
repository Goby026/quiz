package dev.server.quiz.controllers;

import dev.server.quiz.services.CategoriaItemService;
import dev.server.quiz.services.FichaService;
import dev.server.quiz.services.InstitucionService;
import dev.server.quiz.services.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class FichaController {

    private final static Logger logger = LoggerFactory.getLogger(FichaController.class);

    private final FichaService service;
    private final InstitucionService institucionService;
    private final CategoriaItemService categoriaItemService;
    private final ItemService itemService;

    public FichaController(FichaService service, InstitucionService institucionService, CategoriaItemService categoriaItemService, ItemService itemService) {
        this.service = service;
        this.institucionService = institucionService;
        this.categoriaItemService = categoriaItemService;
        this.itemService = itemService;
    }

    @RequestMapping("/fichas")
    public String listar(Model model) throws Exception {

        model.addAttribute("titulo", "Evaluaciones realizadas");
        model.addAttribute("fichas", service.listar());
        model.addAttribute("instituciones", institucionService.listar());

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
