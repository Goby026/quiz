package dev.server.quiz.controllers;

import dev.server.quiz.entities.FichaItem;
import dev.server.quiz.entities.Item;
import dev.server.quiz.models.FichaItemCreationDto;
import dev.server.quiz.services.CategoriaItemService;
import dev.server.quiz.services.FichaItemService;
import dev.server.quiz.services.FichaService;
import dev.server.quiz.services.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class FichaItemController {

    private static final Logger logger = LoggerFactory.getLogger(FichaItemController.class);
    private final FichaItemService service;
    private final CategoriaItemService categoriaItemService;
    private final ItemService itemService;
    private final FichaService fichaService;

    public FichaItemController(FichaItemService service, CategoriaItemService categoriaItemService, ItemService itemService, FichaService fichaService) {
        this.service = service;
        this.categoriaItemService = categoriaItemService;
        this.itemService = itemService;
        this.fichaService = fichaService;
    }

//  *****METODO PARA MOSTRAR FORMULARIO CON LA LISTA DE FICHA-ITEMS REGISTRADOS*****
    @RequestMapping("/ficha-items/{id}")
    public String listar(@PathVariable(value = "id") Long id, Model model ) throws Exception {

        List<FichaItem> items = service.listar(id);

        model.addAttribute("titulo", "Valoración de items");
        model.addAttribute("form", new FichaItemCreationDto(items));
//        model.addAttribute("categorias", categoriaItemService.listar());
//        cargar los items de una determinada Ficha
        model.addAttribute("ficha_id", id);

        return "pages/fichas/fichaRegistro";
    }

    @RequestMapping(value = "/ficha-items/registro", method = RequestMethod.POST)
    public String guardar(@ModelAttribute FichaItemCreationDto form, Model model, RedirectAttributes flash) throws Exception {


        service.registrarTodos(form.getFichaItems());
//        FichaItem fichaItem = new FichaItem();
//
//        List<Item> items = itemService.listar();

        // 👀 Binding result, siempre va junto al objeto que se envia, en este caso institucion
//        if (result.hasErrors()){
//            model.addAttribute("titulo", "Registrar Institución");
//            return "pages/instituciones/formulario";
//        }

//        String mensaje = ( institucion.getId() != null ) ? "Institución modificada correctamente." : "Institución " +
//                "registrada exitosamente.";
//
//        service.registrar(institucion);
//        flash.addFlashAttribute("success", mensaje );
        model.addAttribute("fichas", service.listar());
        return "redirect:/fichas";
    }
}
