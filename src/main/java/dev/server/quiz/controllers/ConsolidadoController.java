package dev.server.quiz.controllers;

import dev.server.quiz.entities.Consolidado;
import dev.server.quiz.entities.Ficha;
import dev.server.quiz.entities.FichaItem;
import dev.server.quiz.entities.Indicador;
import dev.server.quiz.models.ConsolidadoCreationDto;
import dev.server.quiz.models.FichaItemCreationDto;
import dev.server.quiz.services.ConsolidadoService;
import dev.server.quiz.services.FichaItemService;
import dev.server.quiz.services.FichaService;
import dev.server.quiz.services.IndicadorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@Controller
public class ConsolidadoController {

    private final static Logger logger = LoggerFactory.getLogger(ConsolidadoController.class);

    private final FichaService fichaService;
    private final FichaItemService fichaItemService;
    private final IndicadorService indicadorService;
    private final ConsolidadoService consolidadoService;

    public ConsolidadoController(FichaService fichaService, FichaItemService fichaItemService, IndicadorService indicadorService, ConsolidadoService consolidadoService) {
        this.fichaService = fichaService;
        this.fichaItemService = fichaItemService;
        this.indicadorService = indicadorService;
        this.consolidadoService = consolidadoService;
    }

    @RequestMapping(value = "/consolidados/formulario/{id}")
    public String formulario(@PathVariable(value = "id") Long id,
                             Map<String, Object> model, Principal principal, RedirectAttributes flash) throws Exception {
        List<FichaItem> fichasItems = fichaItemService.listar(id);
        List<Indicador> indicadores = indicadorService.listar();
        Ficha ficha = fichaService.obtener(id);
        List<Consolidado> consolidados = consolidadoService.listarPorFicha(ficha);
        String recomendacion = "";

        int planCom = 0;
        int evaFor = 0;
        int reuCom = 0;
        int pracInc = 0;

        for (Consolidado c : consolidados) {
            for (FichaItem fi : fichasItems) {
                if (fi.getItem().getCategoriaItem().getId() == 1) {
                    planCom += fi.getValoracion();
                    c.setTotal(planCom);
                } else if (fi.getItem().getCategoriaItem().getId() == 2) {
                    evaFor += fi.getValoracion();
                    c.setTotal(evaFor);
                } else if (fi.getItem().getCategoriaItem().getId() == 3) {
                    reuCom += fi.getValoracion();
                    c.setTotal(reuCom);
                } else if (fi.getItem().getCategoriaItem().getId() == 4) {
                    pracInc += fi.getValoracion();
                    c.setTotal(pracInc);
                }
            }
        }
//
//        double total = planCom + evaFor + reuCom + pracInc;

        for (Indicador i : indicadores) {
            for (int nota = 0; nota <= 20; nota++) {
                if (i.getResultado() == nota) {
                    recomendacion = i.getRecomendacion();
                }
            }
        }

//        model.put("planCom", planCom);
//        model.put("evaFor", evaFor);
//        model.put("reuCom", reuCom);
//        model.put("pracInc", pracInc);

        model.put("ficha", ficha);
        model.put("consolidados", consolidados);
        model.put("recomendacion", recomendacion);
        model.put("form", new ConsolidadoCreationDto(consolidados));
        model.put("principal", principal.getName());
        model.put("titulo", "CONSOLIDADO");

        return "pages/consolidados/formulario";
    }

    @RequestMapping(value = "/consolidados/registro", method = RequestMethod.POST)
    public String guardar(@ModelAttribute ConsolidadoCreationDto form, Model model, RedirectAttributes flash) throws Exception {


        consolidadoService.registrarTodos(form.getConsolidados());
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
        model.addAttribute("fichas", fichaService.listar());
        return "redirect:/fichas";
    }
}
