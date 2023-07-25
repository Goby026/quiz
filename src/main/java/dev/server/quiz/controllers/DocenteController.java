package dev.server.quiz.controllers;

import dev.server.quiz.entities.Docente;
import dev.server.quiz.services.DocenteService;
import dev.server.quiz.services.InstitucionService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
public class DocenteController {

    private final DocenteService service;
    private final InstitucionService instService;

    private static final Logger logger = LoggerFactory.getLogger(DocenteController.class);

    public DocenteController(DocenteService service, InstitucionService instService) {
        this.service = service;
        this.instService = instService;
    }

    @RequestMapping("/docentes")
    public String listar(Model model) throws Exception {

        model.addAttribute("activeLink", "/docentes");
        model.addAttribute("titulo", "Lista de docentes");
        model.addAttribute("docentes", this.service.listar());
        return "pages/docentes/index";
    }

    @RequestMapping("/docentes/formulario")
    public String formulario(Map<String, Object> model) throws Exception {
        Docente docente = new Docente();
        model.put("docente", docente);
        model.put("instituciones", instService.listar());
        model.put("titulo", "Registrar Docente");
        return "pages/docentes/formulario";
    }

    @RequestMapping(value = "/docentes/formulario", method = RequestMethod.POST)
    public String guardar(@Valid Docente docente, BindingResult result, Model model, RedirectAttributes flash) throws Exception {

        // 👀 Binding result, siempre va junto al objeto que se envia, en este caso cargo
        if (result.hasErrors()){
            model.addAttribute("docente", docente);
            model.addAttribute("instituciones", instService.listar());
            model.addAttribute("titulo", "Registrar Docente");
            return "pages/docentes/formulario";
        }

        String mensaje = ( docente.getId() != null ) ? "Docente modificado correctamente." : "Docente registrado " +
                "exitosamente.";

        service.registrar(docente);
        flash.addFlashAttribute("success", mensaje );
        return "redirect:/docentes";
    }

    @RequestMapping(value = "/docentes/formulario/{id}")
    public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) throws Exception {
        Docente docente = service.obtener(id);
        if (id>0){
            docente = service.obtener(id);
            if (docente == null){
                flash.addFlashAttribute("error", "No se puede cargar el docente seleccionado 👀");
            }else{
                flash.addFlashAttribute("success", "Docente modificado correctamente.");
            }
        }else{
            flash.addFlashAttribute("error", "No existe el id seleccionado.");
            return "redirect:/docentes";
        }
        model.put("docente", docente);
        model.put("instituciones", instService.listar());
        model.put("titulo", "Registrar Docente");

        return "pages/docentes/formulario";
    }

    @RequestMapping(value = "/docentes/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) throws Exception {
        if (id > 0 ){
            service.eliminar(id);
            flash.addFlashAttribute("success", "Se eliminó el docente");
        }
        return "redirect:/docentes";
    }
}
