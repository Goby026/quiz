package dev.server.quiz.repositories;

import dev.server.quiz.entities.FichaItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FichaItemRepo extends JpaRepository<FichaItem, Long> {
}
