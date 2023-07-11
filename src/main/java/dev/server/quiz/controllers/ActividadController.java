package dev.server.quiz.controllers;

import dev.server.quiz.entities.Actividad;
import dev.server.quiz.entities.Ficha;
import dev.server.quiz.services.*;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.time.LocalTime;
import java.util.Date;
import java.util.Map;

@Controller
public class ActividadController {

    private static final Logger logger = LoggerFactory.getLogger(ActividadController.class);

    private final String TITULO = "INFORMACIÓN DE LA PROGRAMACIÓN DE LA ACTIVIDAD O SESIÓN";
    private final ActividadService service;
    private final CanalService canalService;
    private final NivelService nivelService;
    private final AreaService areaService;
    private final FichaService fichaService;
    private final FichaItemService fichaItemService;
    private final InstitucionService institucionService;
    private final DocenteService docenteService;
    private final MedioService medioService;
    private final UsuarioService usuarioService;

    public ActividadController(ActividadService service, CanalService canalService, NivelService nivelService, AreaService areaService, FichaService fichaService, FichaItemService fichaItemService, InstitucionService institucionService, DocenteService docenteService, MedioService medioService, UsuarioService usuarioService) {
        this.service = service;
        this.canalService = canalService;
        this.nivelService = nivelService;
        this.areaService = areaService;
        this.fichaService = fichaService;
        this.fichaItemService = fichaItemService;
        this.institucionService = institucionService;
        this.docenteService = docenteService;
        this.medioService = medioService;
        this.usuarioService = usuarioService;
    }

    @RequestMapping("/actividad")
    public String listar(Model model) throws Exception {
        model.addAttribute("titulo", TITULO);
        model.addAttribute("areas", this.areaService.listar());
        model.addAttribute("canales", this.canalService.listar());
//        model.addAttribute("cargos", this.service.listar());
        return "pages/actividades/index";
    }

    @RequestMapping("/actividad/formulario")
    public String formulario(Map<String, Object> model, Principal principal) throws Exception {

        Actividad actividad = new Actividad();

        model.put("titulo", "Inicio de Actividad");
        model.put("harduser", usuarioService.obtener(1L));
        model.put("actividad", actividad);
        model.put("fichas", service.listar());
        model.put("instituciones", institucionService.listar());
        model.put("canales", canalService.listar());
        model.put("niveles", nivelService.listar());
        model.put("docentes", docenteService.listar());
        model.put("areas", areaService.listar());
        model.put("medios", medioService.listar());
        model.put("principal", principal);

        return "pages/actividades/formulario";
    }

    @RequestMapping(value = "/actividad/formulario", method = RequestMethod.POST)
    public String guardar(@Valid Actividad actividad, BindingResult result, Map<String, Object> model,
                           RedirectAttributes flash) throws Exception {

        // 👀 BindingResult, siempre va junto al objeto que se envia, en este caso actividad
        if (result.hasErrors()){

//            Map<String, String> errores = new HashMap<>();
//            result.getFieldErrors().forEach( err -> {
//                errores.put(err.getField(), "El campo ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage()));
//            });
//            model.addAttribute("titulo", "Registrar Área");
//            model.addAttribute("error", errores);

            model.put("actividad", actividad);
            model.put("fichas", service.listar());
            model.put("instituciones", institucionService.listar());
            model.put("canales", canalService.listar());
            model.put("niveles", nivelService.listar());
            model.put("docentes", docenteService.listar());
            model.put("areas", areaService.listar());
            model.put("medios", medioService.listar());
            model.put("titulo", "Inicio de Actividad");

            return "pages/actividades/formulario";
        }

        String mensaje = ( actividad.getId() != null ) ? "Actividad modificada correctamente." : "Actividad " +
                "registrada exitosamente.";

//        *******REGISTRAR ACTIVIDAD*******
        LocalTime localTime = LocalTime.now();
        actividad.setHora_inicio(localTime);
        actividad.setHora_fin(localTime);
        actividad.setUsuario(usuarioService.obtener(2L));

        Actividad actividadRegistered = service.registrar(actividad);

//        *******REGISTRAR FICHA*******
        Ficha ficha = new Ficha();
        ficha.setUsuario("DEV-USER");
        ficha.setFecha(new Date());
        ficha.setActividad(actividadRegistered);
        ficha.setIndicador(null);

        Ficha fichaRegistered = fichaService.registrar(ficha);

        if (actividadRegistered.getId() > 0 && fichaRegistered.getId() > 0 ){
            fichaItemService.registrarItems(fichaRegistered);
            return "redirect:/ficha-items/"+fichaRegistered.getId();
        }

//        logger.info("ACTIVIDAD ----> " + actividad.toString());
        return "redirect:/actividad/formulario";
    }
}
