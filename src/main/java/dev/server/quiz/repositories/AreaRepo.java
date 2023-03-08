package dev.server.quiz.repositories;

import dev.server.quiz.entities.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaRepo extends JpaRepository<Area, Long> {
}
