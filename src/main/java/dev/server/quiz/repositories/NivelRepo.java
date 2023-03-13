package dev.server.quiz.repositories;

import dev.server.quiz.entities.Nivel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NivelRepo extends JpaRepository<Nivel, Long> {
}
