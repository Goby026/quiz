package dev.server.quiz.controllers;

import dev.server.quiz.entities.Ficha;
import dev.server.quiz.entities.FichaItem;
import dev.server.quiz.entities.Institucion;
import dev.server.quiz.entities.Item;
import dev.server.quiz.services.CategoriaItemService;
import dev.server.quiz.services.FichaItemService;
import dev.server.quiz.services.ItemService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class FichaItemController {

    private final FichaItemService service;
    private final CategoriaItemService categoriaItemService;
    private final ItemService itemService;

    public FichaItemController(FichaItemService service, CategoriaItemService categoriaItemService, ItemService itemService) {
        this.service = service;
        this.categoriaItemService = categoriaItemService;
        this.itemService = itemService;
    }

    @RequestMapping("/ficha-items")
    public String listar(Model model) throws Exception {

        model.addAttribute("titulo", "Valoración de items");
        model.addAttribute("categorias", categoriaItemService.listar());
        model.addAttribute("items", itemService.listar());

        return "pages/fichas/fichaRegistro";
    }

    @RequestMapping(value = "/ficha-items/registro", method = RequestMethod.POST)
    public String guardar(BindingResult result, Model model, RedirectAttributes flash) throws Exception {

//        Ficha ficha = ficha
//
//        List<Item> items = itemService.listar();

        // 👀 Binding result, siempre va junto al objeto que se envia, en este caso institucion
        if (result.hasErrors()){
            model.addAttribute("titulo", "Registrar Institución");
            return "pages/instituciones/formulario";
        }

//        String mensaje = ( institucion.getId() != null ) ? "Institución modificada correctamente." : "Institución " +
//                "registrada exitosamente.";
//
//        service.registrar(institucion);
//        flash.addFlashAttribute("success", mensaje );
        return "redirect:/ficha-items";
    }
}
