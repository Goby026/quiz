package dev.server.quiz.repositories;

import dev.server.quiz.entities.Canal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CanalRepo extends JpaRepository<Canal, Long> {
}
