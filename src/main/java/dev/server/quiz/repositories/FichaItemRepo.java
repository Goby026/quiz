package dev.server.quiz.repositories;

import dev.server.quiz.entities.FichaItem;
import dev.server.quiz.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FichaItemRepo extends JpaRepository<FichaItem, Long> {
    public List<Item> findByFichaId(Long id);
}
