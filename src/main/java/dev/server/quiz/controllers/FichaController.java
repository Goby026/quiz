package dev.server.quiz.controllers;

import dev.server.quiz.entities.Actividad;
import dev.server.quiz.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FichaController {

    private final static Logger logger = LoggerFactory.getLogger(FichaController.class);

    private final FichaService service;
    private final InstitucionService institucionService;
    private final CategoriaItemService categoriaItemService;
    private final ItemService itemService;
    private final CanalService canalService;
    private final DocenteService docenteService;
    private final AreaService areaService;

    public FichaController(FichaService service, InstitucionService institucionService, CategoriaItemService categoriaItemService, ItemService itemService, CanalService canalService, DocenteService docenteService, AreaService areaService) {
        this.service = service;
        this.institucionService = institucionService;
        this.categoriaItemService = categoriaItemService;
        this.itemService = itemService;
        this.canalService = canalService;
        this.docenteService = docenteService;
        this.areaService = areaService;
    }

    @RequestMapping("/fichas")
    public String listar(Model model) throws Exception {

        Actividad actividad = new Actividad();

        model.addAttribute("titulo", "Evaluaciones realizadas");
        model.addAttribute("actividad", actividad);
        model.addAttribute("fichas", service.listar());
        model.addAttribute("instituciones", institucionService.listar());
        model.addAttribute("canales", canalService.listar());
        model.addAttribute("docentes", docenteService.listar());
        model.addAttribute("areas", areaService.listar());

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
