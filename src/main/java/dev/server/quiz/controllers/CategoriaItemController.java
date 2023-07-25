package dev.server.quiz.controllers;

import dev.server.quiz.entities.CategoriaItem;
import dev.server.quiz.services.CategoriaItemService;
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
public class CategoriaItemController {

    private final CategoriaItemService service;

    public CategoriaItemController(CategoriaItemService service) {
        this.service = service;
    }

    @RequestMapping("/categorias")
    public String listar(Model model) throws Exception {

        model.addAttribute("activeLink", "/categorias");
        model.addAttribute("titulo", "Lista de categorias");
        model.addAttribute("categorias", service.listar());

        return "pages/categorias/index";
    }

    @RequestMapping("/categorias/formulario")
    public String formulario(Map<String, Object> model) throws Exception {
        CategoriaItem categoria = new CategoriaItem();
        model.put("categoria", categoria);
        model.put("titulo", "Registrar Categoría");
        return "pages/categorias/formulario";
    }

    @RequestMapping(value = "/categorias/formulario", method = RequestMethod.POST)
    public String guardar(@Valid CategoriaItem categoria, BindingResult result, Model model,
                          RedirectAttributes flash) throws Exception {

        // 👀 Binding result, siempre va junto al objeto que se envia, en este caso institucion
        if (result.hasErrors()){
            model.addAttribute("titulo", "Registrar Categoría");
            return "pages/categorias/formulario";
        }

        String mensaje = ( categoria.getId() != null ) ? "Categoría modificada correctamente." : "Categoría " +
                "registrada exitosamente.";

        service.registrar(categoria);
        flash.addFlashAttribute("success", mensaje );
        return "redirect:/categorias";
    }

    @RequestMapping(value = "/categorias/formulario/{id}")
    public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) throws Exception {
        CategoriaItem categoria = null;
        if (id>0){
            categoria = service.obtener(id);
            if (categoria == null){
                flash.addFlashAttribute("error", "No se puede cargar la categoría seleccionada 👀");
            }else{
                flash.addFlashAttribute("success", "Categoría modificada correctamente.");
            }
        }else{
            flash.addFlashAttribute("error", "No existe el id seleccionado.");
            return "redirect:/instituciones";
        }

        model.put("categoria", categoria);
        model.put("titulo", "Registrar Categoría");

        return "pages/categorias/formulario";
    }

    @RequestMapping(value = "/categorias/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) throws Exception {
        if (id > 0 ){
            service.eliminar(id);
            flash.addFlashAttribute("success", "Se eliminó la categoría");
        }
        return "redirect:/categorias";
    }
}
