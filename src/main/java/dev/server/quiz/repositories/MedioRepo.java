package dev.server.quiz.repositories;

import dev.server.quiz.entities.Medio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedioRepo extends JpaRepository<Medio, Long> {
}
