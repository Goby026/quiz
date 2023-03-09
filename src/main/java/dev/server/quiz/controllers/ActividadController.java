package dev.server.quiz.controllers;

import dev.server.quiz.entities.Actividad;
import dev.server.quiz.services.ActividadService;
import dev.server.quiz.services.AreaService;
import dev.server.quiz.services.CanalService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ActividadController {

    private final String TITULO = "INFORMACIÓN DE LA PROGRAMACIÓN DE LA ACTIVIDAD O SESIÓN";
    private final ActividadService service;
    private final CanalService canalService;
    private final AreaService areaService;

    public ActividadController(ActividadService service, CanalService canalService, AreaService areaService) {
        this.service = service;
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

    @RequestMapping(value = "/actividad/formulario", method = RequestMethod.POST)
    public String guardar(@Valid Actividad actividad, BindingResult result, Model model, RedirectAttributes flash) throws Exception {

        // 👀 Binding result, siempre va junto al objeto que se envia, en este caso cargo
//        if (result.hasErrors()){
//            model.addAttribute("titulo", "Registrar Área");
//            return "pages/areas/formulario";
//        }

        String mensaje = ( actividad.getId() != null ) ? "Actividad modificada correctamente." : "Actividad " +
                "registrada exitosamente.";

        service.registrar(actividad);
        flash.addFlashAttribute("success", mensaje );
        return "redirect:/ficha-items";
    }
}
