package dev.server.quiz.controllers;

import dev.server.quiz.services.AreaService;
import dev.server.quiz.services.CanalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ActividadController {

    private final String TITULO = "INFORMACIÓN DE LA PROGRAMACIÓN DE LA ACTIVIDAD O SESIÓN";
    private final CanalService canalService;
    private final AreaService areaService;

    public ActividadController(CanalService canalService, AreaService areaService) {
        this.canalService = canalService;
        this.areaService = areaService;
    }

    @RequestMapping("/actividad")
    public String listar(Model model) throws Exception {
        model.addAttribute("titulo", TITULO);
        model.addAttribute("areas", this.areaService.listar());
        model.addAttribute("canales", this.canalService.listar());
//        model.addAttribute("cargos", this.service.listar());
        return "pages/actividades/index";
    }
}
