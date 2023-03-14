package dev.server.quiz.repositories;

import dev.server.quiz.entities.Indicador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndicadorRepo extends JpaRepository<Indicador, Long> {
}
