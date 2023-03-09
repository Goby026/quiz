package dev.server.quiz.repositories;

import dev.server.quiz.entities.Actividad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActividadRepo extends JpaRepository<Actividad, Long> {
}
