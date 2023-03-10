package dev.server.quiz.controllers;

import dev.server.quiz.entities.Institucion;
import dev.server.quiz.services.DreService;
import dev.server.quiz.services.InstitucionService;
import dev.server.quiz.services.UgelService;
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
public class InstitucionController {

    private final InstitucionService service;
    private final DreService dreService;
    private final UgelService ugelService;

    public InstitucionController(InstitucionService service, DreService dreService, UgelService ugelService) {
        this.service = service;
        this.dreService = dreService;
        this.ugelService = ugelService;
    }

    @RequestMapping("/instituciones")
    public String listar(Model model) throws Exception {

        model.addAttribute("titulo", "Lista de Instituciones");
        model.addAttribute("instituciones", service.listar());

        return "pages/instituciones/index";
    }

    @RequestMapping("/instituciones/formulario")
    public String formulario(Map<String, Object> model) throws Exception {
        Institucion institucion = new Institucion();
        model.put("institucion", institucion);
        model.put("titulo", "Registrar Institución");
        model.put("dres", dreService.listar());
        model.put("ugeles", ugelService.listar());
//        model.addAttribute("cargos", this.service.listar());
        return "pages/instituciones/formulario";
    }

    @RequestMapping(value = "/instituciones/formulario", method = RequestMethod.POST)
    public String guardar(@Valid Institucion institucion, BindingResult result, Model model, RedirectAttributes flash) throws Exception {

        // 👀 Binding result, siempre va junto al objeto que se envia, en este caso institucion
        if (result.hasErrors()){
            model.addAttribute("titulo", "Registrar Institución");
            return "pages/instituciones/formulario";
        }

        String mensaje = ( institucion.getId() != null ) ? "Institución modificada correctamente." : "Institución " +
                "registrada exitosamente.";

        service.registrar(institucion);
        flash.addFlashAttribute("success", mensaje );
        return "redirect:/instituciones";
    }

    @RequestMapping(value = "/instituciones/formulario/{id}")
    public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) throws Exception {
        Institucion institucion = null;
        if (id>0){
            institucion = service.obtener(id);
            if (institucion == null){
                flash.addFlashAttribute("error", "No se puede cargar la institución seleccionada 👀");
            }else{
                flash.addFlashAttribute("success", "Institución modificada correctamente.");
            }
        }else{
            flash.addFlashAttribute("error", "No existe el id seleccionado.");
            return "redirect:/instituciones";
        }

        model.put("institucion", institucion);
        model.put("titulo", "Registrar Institución");

        return "pages/instituciones/formulario";
    }

    @RequestMapping(value = "/instituciones/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) throws Exception {
        if (id > 0 ){
            service.eliminar(id);
            flash.addFlashAttribute("success", "Se eliminó la institución");
        }
        return "redirect:/instituciones";
    }
}
