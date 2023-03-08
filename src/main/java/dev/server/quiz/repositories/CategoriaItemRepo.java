package dev.server.quiz.repositories;


import dev.server.quiz.entities.CategoriaItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaItemRepo extends JpaRepository<CategoriaItem, Long> {
}
