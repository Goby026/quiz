package dev.server.quiz.controllers;

import dev.server.quiz.entities.*;
import dev.server.quiz.models.FichaItemCreationDto;
import dev.server.quiz.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    private final ConsolidadoService consolidadoService;
    private final CategoriaItemService categoriaItemService;
    private final ItemService itemService;
    private final FichaService fichaService;
    private final IndicadorService indicadorService;

    public FichaItemController(FichaItemService service, ConsolidadoService consolidadoService, CategoriaItemService categoriaItemService, ItemService itemService, FichaService fichaService, IndicadorService indicadorService) {
        this.service = service;
        this.consolidadoService = consolidadoService;
        this.categoriaItemService = categoriaItemService;
        this.itemService = itemService;
        this.fichaService = fichaService;
        this.indicadorService = indicadorService;
    }

    //  *****METODO PARA MOSTRAR FORMULARIO CON LA LISTA DE FICHA-ITEMS REGISTRADOS*****
    @RequestMapping("/ficha-items/{id}")
    public String listar(@PathVariable(value = "id") Long id, Model model) throws Exception {

        List<FichaItem> items = service.listar(id);

        model.addAttribute("titulo", "Valoración de items");
        model.addAttribute("form", new FichaItemCreationDto(items));
        model.addAttribute("categorias", categoriaItemService.listar());
//        cargar los items de una determinada Ficha
        model.addAttribute("ficha_id", id);

        return "pages/fichas/fichaRegistro";
    }

    @RequestMapping(value = "/ficha-items/registro", method = RequestMethod.POST)
    public String guardar(@ModelAttribute FichaItemCreationDto form, Model model, RedirectAttributes flash) throws Exception {

        List<FichaItem> fichaItems = service.registrarTodos(form.getFichaItems());
        Ficha ficha = fichaItems.get(0).getFicha();

        List<Indicador> indicadores = indicadorService.listar();
        List<CategoriaItem> categorias = categoriaItemService.listar();
        List<Consolidado> consolidados = consolidadoService.listarPorFicha(ficha);

        int nocat = 0, procesocat = 0, sicat = 0;

        for (CategoriaItem ci : categorias) {
            nocat = 0; procesocat = 0; sicat = 0;
            for (FichaItem fi : fichaItems) {
                if (ci.getId() == fi.getItem().getCategoriaItem().getId()) {
                    switch (fi.getValoracion()) {
                        case 0:
                            nocat++;
                            break;
                        case 1:
                            procesocat++;
                            break;
                        case 2:
                            sicat++;
                            break;
                    }
                }
            }

            for (Consolidado c : consolidados) {
                if (c.getCategoriaItem().getId() == ci.getId()){
                    c.setNoCat(nocat);
                    c.setProcesoCat(procesocat);
                    c.setSiCat(sicat);
                    c.setTotal(nocat + procesocat + sicat);
                    c.setNivelAvance((nocat + procesocat + sicat)/100);
                    consolidadoService.registrar(c);
                }
            }

        }



//        logger.info("NOCAT: " + nocat + "PROCESO: " + procesocat + "SICAT: " + sicat);


//        FichaItem fichaItem = new FichaItem();
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

//        model.addAttribute("fichas", service.listar());
        return "redirect:/fichas";
    }
}
