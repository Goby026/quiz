package dev.server.quiz.repositories;

import dev.server.quiz.entities.FichaItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FichaItemRepo extends JpaRepository<FichaItem, Long> {

//    @Query(value = "SELECT i.id, i.descripcion, i.categoria_item_id, i.created_at, i.updated_at FROM ficha_item fi INNER JOIN " +
//            "item i on fi.id" +
//            " = i.id WHERE " +
//            "ficha_id =:IDFICHA",
//            nativeQuery = true)
//    List<Object> buscarPorFicha(@Param("IDFICHA") Long idFicha);

    List<FichaItem> findByFichaId(Long idFicha);
}