package dev.server.quiz.services;

import dev.server.quiz.entities.TipoMedio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoMedioRepo extends JpaRepository<TipoMedio, Long> {
}
