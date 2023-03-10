package dev.server.quiz.repositories;

import dev.server.quiz.entities.Ugel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UgelRepo extends JpaRepository<Ugel, Long> {
}
