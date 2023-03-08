package dev.server.quiz.bootstrap;

import dev.server.quiz.QuizApplication;
import dev.server.quiz.entities.Cargo;
import dev.server.quiz.entities.Docente;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(QuizApplication.class);

    @Override
    public void run(String... args) throws Exception {

        logger.info("*********************Aplicacion inciada*********************");
    }
}
