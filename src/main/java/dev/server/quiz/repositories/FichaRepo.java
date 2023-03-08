package dev.server.quiz.repositories;

import dev.server.quiz.entities.Ficha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FichaRepo extends JpaRepository<Ficha, Long> {
}
