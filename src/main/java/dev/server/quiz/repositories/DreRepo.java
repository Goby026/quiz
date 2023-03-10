package dev.server.quiz.repositories;

import dev.server.quiz.entities.Dre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DreRepo extends JpaRepository<Dre, Long> {
}
