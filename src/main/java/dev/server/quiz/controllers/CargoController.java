package dev.server.quiz.controllers;

import dev.server.quiz.entities.Cargo;
import dev.server.quiz.services.CargoService;
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
public class CargoController {

    private static final Logger logger = LoggerFactory.getLogger(CargoController.class);
    private final CargoService service;

    public CargoController(CargoService service) {
        this.service = service;
    }

    @RequestMapping("/cargos")
    public String listar(Model model) throws Exception {

        model.addAttribute("activeLink", "/cargos");
        model.addAttribute("titulo", "Lista de cargos");
        model.addAttribute("cargos", service.listar());

        return "pages/cargos/index";
    }

//    @RequestMapping("/cargos")
//    public String getCargos(@RequestParam(name = "page", defaultValue = "0") int page, Model model) throws Exception {
//        Pageable pageRequest = PageRequest.of(page, 4);
//
//        Page<Cargo> cargos = this.service.listar(pageRequest);
//
//        PageRender<Cargo> pageRender = new PageRender<>("/cargos", cargos);
//
//        model.addAttribute("titulo", "Lista de cargos");
//        model.addAttribute("cargos", cargos);
//        model.addAttribute("page", pageRender);
//
//        return "pages/cargos/index";
//    }

    @RequestMapping("/cargos/formulario")
    public String formulario(Map<String, Object> model) throws Exception {
        Cargo cargo = new Cargo();
        model.put("cargo", cargo);
        model.put("titulo", "Registrar cargo");
//        model.addAttribute("cargos", this.service.listar());
        return "pages/cargos/formulario";
    }

    @RequestMapping(value = "/cargos/formulario", method = RequestMethod.POST)
    public String guardar(@Valid Cargo cargo, BindingResult result, Model model, RedirectAttributes flash) throws Exception {

        // 👀 Binding result, siempre va junto al objeto que se envia, en este caso cargo
        if (result.hasErrors()){
            model.addAttribute("titulo", "Registrar cargo");
            return "pages/cargos/formulario";
        }

        String mensaje = ( cargo.getId() != null ) ? "Cargo modificado correctamente." : "Cargo creado exitosamente.";

        service.registrar(cargo);
        flash.addFlashAttribute("success", mensaje );
        return "redirect:/cargos";
    }

    @RequestMapping(value = "/cargos/formulario/{id}")
    public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) throws Exception {
        Cargo cargo = null;
        if (id>0){
            cargo = service.obtener(id);
            if (cargo == null){
                flash.addFlashAttribute("error", "No se puede cargar el cliente seleccionado 👀");
            }else{
                flash.addFlashAttribute("success", "Cargo modificado correctamente.");
            }
        }else{
            flash.addFlashAttribute("error", "No existe el id seleccionado.");
            return "redirect:/cargos";
        }

        model.put("cargo", cargo);
        model.put("titulo", "Registrar cargo");

        return "pages/cargos/formulario";
    }

    @RequestMapping(value = "/cargos/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) throws Exception {
        if (id > 0 ){
            service.eliminar(id);
            flash.addFlashAttribute("success", "Se eliminó el cargo");
        }
        return "redirect:/cargos";
    }
}
