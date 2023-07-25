package dev.server.quiz.controllers;

import dev.server.quiz.entities.Canal;
import dev.server.quiz.services.CanalService;
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
public class CanalController {

    private final CanalService service;
    private final String TITULO = "Lista de Canales de Comunicación";
    private final String TITULO_FORM = "Registrar Canal de Comunicación";

    public CanalController(CanalService service) {
        this.service = service;
    }

    @RequestMapping("/canales")
    public String listar(Model model) throws Exception {

        model.addAttribute("activeLink", "/canales");
        model.addAttribute("titulo", TITULO);
        model.addAttribute("canales", service.listar());

        return "pages/canales/index";
    }

    @RequestMapping("/canales/formulario")
    public String formulario(Map<String, Object> model) throws Exception {
        Canal canal = new Canal();
        model.put("canal", canal);
        model.put("titulo", TITULO_FORM);
        return "pages/canales/formulario";
    }

    @RequestMapping(value = "/canales/formulario", method = RequestMethod.POST)
    public String guardar(@Valid Canal canal, BindingResult result, Model model, RedirectAttributes flash) throws Exception {

        // 👀 Binding result, siempre va junto al objeto que se envia, en este caso cargo
        if (result.hasErrors()){
            model.addAttribute("titulo", TITULO_FORM);
            return "pages/canales/formulario";
        }

        String mensaje = ( canal.getId() != null ) ? "Canal modificado correctamente." : "Canal creado exitosamente.";

        service.registrar(canal);
        flash.addFlashAttribute("success", mensaje );
        return "redirect:/canales";
    }

    @RequestMapping(value = "/canales/formulario/{id}")
    public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) throws Exception {
        Canal canal = null;
        if (id>0){
            canal = service.obtener(id);
            if (canal == null){
                flash.addFlashAttribute("error", "No se puede cargar el canal seleccionado 👀");
            }else{
                flash.addFlashAttribute("success", "Canal modificado correctamente.");
            }
        }else{
            flash.addFlashAttribute("error", "No existe el id seleccionado.");
            return "redirect:/canales";
        }

        model.put("canal", canal);
        model.put("titulo", TITULO_FORM);

        return "pages/canales/formulario";
    }

    @RequestMapping(value = "/canales/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) throws Exception {
        if (id > 0 ){
            service.eliminar(id);
            flash.addFlashAttribute("success", "Se eliminó el canal");
        }
        return "redirect:/canales";
    }
}
