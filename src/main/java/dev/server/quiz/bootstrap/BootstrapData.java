package dev.server.quiz.bootstrap;

import dev.server.quiz.QuizApplication;
import dev.server.quiz.entities.Cargo;
import dev.server.quiz.entities.Usuario;
import dev.server.quiz.services.CargoService;
import dev.server.quiz.services.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

//    private CargoService cargoService;
//
//    private UsuarioService usuarioService;
//
//    public BootstrapData(CargoService cargoService, UsuarioService usuarioService) {
//        this.cargoService = cargoService;
//        this.usuarioService = usuarioService;
//    }

    private static final Logger logger = LoggerFactory.getLogger(QuizApplication.class);

    @Override
    public void run(String... args) throws Exception {

//        Cargo cargo = cargoService.obtener(1L);
//
//        Usuario usuario = new Usuario();
//
//        usuario.setUsername("jose@mpfn.gob.pe");
//        usuario.setPassword("123456");
//        usuario.setNombres("Jose Luis");
//        usuario.setApellidos("Rojas Jurado");
//        usuario.setTelefono("98765432");
//        usuario.setTelefono("954659865");
//        usuario.setDni("98765432");
//        usuario.setEmail("jose@mpfn.gob.pe");
//        usuario.setEnabled(true);
//        usuario.setCargo(cargo);
//
//        usuarioService.registrar(usuario);

        logger.info("*********************Aplicacion inciada*********************");
    }
}
