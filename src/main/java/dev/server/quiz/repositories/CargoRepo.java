package dev.server.quiz.repositories;

import dev.server.quiz.entities.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargoRepo extends JpaRepository<Cargo, Long> {
}
