package dev.server.quiz.repositories;

import dev.server.quiz.entities.Consolidado;
import dev.server.quiz.entities.Ficha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsolidadoRepo extends JpaRepository<Consolidado, Long> {
    List<Consolidado> findByFicha(Ficha ficha);
}
