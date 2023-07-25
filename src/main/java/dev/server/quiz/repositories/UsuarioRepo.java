package dev.server.quiz.repositories;

import dev.server.quiz.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepo extends JpaRepository<Usuario, Long> {

//    @Query("select a from Article a where a.creationDateTime <= :creationDateTime")
//    List<Article> findAllWithCreationDateTimeBefore(
//            @Param("creationDateTime") Date creationDateTime);

    Usuario findByUsername( String username );
}
