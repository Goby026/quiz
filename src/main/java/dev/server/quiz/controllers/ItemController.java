package dev.server.quiz.controllers;

import dev.server.quiz.entities.Item;
import dev.server.quiz.services.CategoriaItemService;
import dev.server.quiz.services.ItemService;
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
public class ItemController {

    private final ItemService service;
    private final CategoriaItemService categoriaService;

    public ItemController(ItemService service, CategoriaItemService categoriaService) {
        this.service = service;
        this.categoriaService = categoriaService;
    }

    @RequestMapping("/items")
    public String listar(Model model) throws Exception {

        model.addAttribute("titulo", "Lista de items");
        model.addAttribute("items", service.listar());

        return "pages/items/index";
    }

    @RequestMapping("/items/formulario")
    public String formulario(Map<String, Object> model) throws Exception {
        Item item = new Item();
        model.put("item", item);
        model.put("titulo", "Registrar item");
        model.put("categorias", categoriaService.listar());
        return "pages/items/formulario";
    }

    @RequestMapping(value = "/items/formulario", method = RequestMethod.POST)
    public String guardar(@Valid Item item, BindingResult result, Model model, RedirectAttributes flash) throws Exception {

        // 👀 Binding result, siempre va junto al objeto que se envia, en este caso cargo
        if (result.hasErrors()){
            model.addAttribute("titulo", "Registrar Item");
            return "pages/items/formulario";
        }

        String mensaje = ( item.getId() != null ) ? "Item modificado correctamente." : "Item creado exitosamente.";

        service.registrar(item);
        flash.addFlashAttribute("success", mensaje );
        return "redirect:/items";
    }

    @RequestMapping(value = "/items/formulario/{id}")
    public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) throws Exception {
        Item item = null;
        if (id>0){
            item = service.obtener(id);
            if (item == null){
                flash.addFlashAttribute("error", "No se puede cargar el item seleccionado 👀");
            }else{
                flash.addFlashAttribute("success", "Item modificado correctamente.");
            }
        }else{
            flash.addFlashAttribute("error", "No existe el id seleccionado.");
            return "redirect:/items";
        }

        model.put("item", item);
        model.put("titulo", "Registrar Item");
        model.put("categorias", categoriaService.listar());

        return "pages/items/formulario";
    }

    @RequestMapping(value = "/items/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) throws Exception {
        if (id > 0 ){
            service.eliminar(id);
            flash.addFlashAttribute("success", "Se eliminó el item");
        }
        return "redirect:/items";
    }
}
