package dev.server.quiz.controllers;

import dev.server.quiz.entities.CategoriaItem;
import dev.server.quiz.entities.Indicador;
import dev.server.quiz.services.IndicadorService;
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
public class IndicadorController {

    private final IndicadorService service;

    public IndicadorController(IndicadorService service) {
        this.service = service;
    }

    @RequestMapping("/indicadores")
    public String listar(Model model) throws Exception {

        model.addAttribute("titulo", "Lista de indicadores");
        model.addAttribute("indicadores", service.listar());

        return "pages/indicadores/index";
    }

    @RequestMapping("/indicadores/formulario")
    public String formulario(Map<String, Object> model) throws Exception {
        Indicador indicador = new Indicador();
        model.put("indicador", indicador);
        model.put("titulo", "Registrar Indicador");
        return "pages/indicadores/formulario";
    }

    @RequestMapping(value = "/indicadores/formulario", method = RequestMethod.POST)
    public String guardar(@Valid Indicador indicador, BindingResult result, Model model,
                          RedirectAttributes flash) throws Exception {

        // 👀 Binding result, siempre va junto al objeto que se envia, en este caso institucion
        if (result.hasErrors()){
            model.addAttribute("titulo", "Registrar Indicador");
            return "pages/indicadores/formulario";
        }

        String mensaje = ( indicador.getId() != null ) ? "Indicador modificado correctamente." : "Indicador " +
                "registrado exitosamente.";

        service.registrar(indicador);
        flash.addFlashAttribute("success", mensaje );
        return "redirect:/indicadores";
    }

    @RequestMapping(value = "/indicadores/formulario/{id}")
    public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) throws Exception {
        Indicador indicador = null;
        if (id>0){
            indicador = service.obtener(id);
            if (indicador == null){
                flash.addFlashAttribute("error", "No se puede cargar el indicador seleccionado 👀");
            }else{
                flash.addFlashAttribute("success", "Indicador modificado correctamente.");
            }
        }else{
            flash.addFlashAttribute("error", "No existe el id seleccionado.");
            return "redirect:/indicadores";
        }

        model.put("indicador", indicador);
        model.put("titulo", "Registrar Indicador");

        return "pages/indicadores/formulario";
    }

    @RequestMapping(value = "/indicadores/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) throws Exception {
        if (id > 0 ){
            service.eliminar(id);
            flash.addFlashAttribute("success", "Se eliminó el indicador");
        }
        return "redirect:/indicadores";
    }

}
