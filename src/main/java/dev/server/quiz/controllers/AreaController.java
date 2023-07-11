package dev.server.quiz.controllers;

import dev.server.quiz.entities.Area;
import dev.server.quiz.services.AreaService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
public class AreaController {
    private final AreaService service;

    public AreaController(AreaService service) {
        this.service = service;
    }

    @RequestMapping("/areas")
    public String listar(Model model) throws Exception {
        model.addAttribute("titulo", "Lista de areas / cursos");
        model.addAttribute("areas", this.service.listar());
        return "pages/areas/index";
    }

    @RequestMapping("/areas/formulario")
    public String formulario(Map<String, Object> model) throws Exception {
        Area area = new Area();
        model.put("area", area);
        model.put("titulo", "Registrar Área");
        return "pages/areas/formulario";
    }

    @RequestMapping(value = "/areas/formulario", method = RequestMethod.POST)
    public String guardar(@Valid Area area, BindingResult result, Model model, RedirectAttributes flash) throws Exception {

//        if (area.getNombreCurso().isEmpty()) {
//            model.addAttribute("titulo", "Registrar Área");
//            flash.addFlashAttribute("error", "Indique el nombre de área");
//            return "redirect:/areas/formulario";
//        }

        // 👀 Binding result, siempre va junto al objeto que se envia, en este caso cargo
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Registrar Área");
            return "pages/areas/formulario";
        }

        String mensaje = (area.getId() != null) ? "Área modificada correctamente." : "Área registrada " +
                "exitosamente.";

        service.registrar(area);
        flash.addFlashAttribute("success", mensaje);
        return "redirect:/areas";
    }

    @RequestMapping(value = "/areas/formulario/{id}")
    public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) throws Exception {
        Area area = service.obtener(id);

        if (id > 0) {

//            if (area.getNombreCurso().isEmpty()) {
//                model.put("area", area);
//                model.put("titulo", "Registrar Área");
//                flash.addFlashAttribute("error", "Indique el nombre de área");
//                return "pages/areas/formulario";
//            }
//            area = service.obtener(id);
            if (area == null) {
                flash.addFlashAttribute("error", "No se puede cargar el area seleccionada 👀");
            } else {
                flash.addFlashAttribute("success", "Área modificada correctamente.");
            }
        } else {
            flash.addFlashAttribute("error", "No existe el id seleccionado.");
            return "redirect:/areas";
        }
        model.put("area", area);
        model.put("titulo", "Registrar Área");

        return "pages/areas/formulario";
    }

    @RequestMapping(value = "/areas/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) throws Exception {
        if (id > 0) {
            service.eliminar(id);
            flash.addFlashAttribute("success", "Se eliminó el área");
        }
        return "redirect:/areas";
    }
}
