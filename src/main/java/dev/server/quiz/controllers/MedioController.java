package dev.server.quiz.controllers;

import dev.server.quiz.entities.Medio;
import dev.server.quiz.services.MedioService;
import dev.server.quiz.services.TipoMedioService;
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
public class MedioController {

    private final MedioService service;
    private final TipoMedioService tipoMedioService;

    public MedioController(MedioService service, TipoMedioService tipoMedioService) {
        this.service = service;
        this.tipoMedioService = tipoMedioService;
    }

    @RequestMapping("/medios")
    public String listar(Model model) throws Exception {
        model.addAttribute("activeLink", "/medios");
        model.addAttribute("titulo", "Lista de medios de comunicación");
        model.addAttribute("medios", this.service.listar());
        return "pages/medios/index";
    }

    @RequestMapping("/medios/formulario")
    public String formulario(Map<String, Object> model) throws Exception {
        Medio medio = new Medio();
        model.put("medio", medio);
        model.put("tipos", tipoMedioService.listar());
        model.put("titulo", "Registrar Medio de comunicación");
        return "pages/medios/formulario";
    }

    @RequestMapping(value = "/medios/formulario", method = RequestMethod.POST)
    public String guardar(@Valid Medio medio, BindingResult result, Model model, RedirectAttributes flash) throws Exception {

        // 👀 Binding result, siempre va junto al objeto que se envia, en este caso cargo
        if (result.hasErrors()){
            model.addAttribute("titulo", "Registrar Medio de comunicación");
            return "pages/medios/formulario";
        }

        String mensaje = ( medio.getId() != null ) ? "Medio modificado correctamente." : "Medio registrado " +
                "exitosamente.";

        service.registrar(medio);
        flash.addFlashAttribute("success", mensaje );
        return "redirect:/medios";
    }

    @RequestMapping(value = "/medios/formulario/{id}")
    public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) throws Exception {
        Medio medio = null;
        if (id>0){
            medio = service.obtener(id);
            if (medio == null){
                flash.addFlashAttribute("error", "No se puede cargar el medio seleccionado 👀");
            }else{
                flash.addFlashAttribute("success", "Medio de comunicación modificado correctamente.");
            }
        }else{
            flash.addFlashAttribute("error", "No existe el id seleccionado.");
            return "redirect:/medios";
        }
        model.put("medio", medio);
        model.put("titulo", "Registrar Medio de comunicación");

        return "pages/medios/formulario";
    }

    @RequestMapping(value = "/medios/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) throws Exception {
        if (id > 0 ){
            service.eliminar(id);
            flash.addFlashAttribute("success", "Se eliminó el medio de comunicación");
        }
        return "redirect:/medios";
    }

}
