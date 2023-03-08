package dev.server.quiz.repositories;

import dev.server.quiz.entities.Institucion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface InstitucionRepo extends JpaRepository<Institucion, Long> {
}
