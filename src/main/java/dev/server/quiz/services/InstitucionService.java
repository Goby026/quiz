package dev.server.quiz.services;

import dev.server.quiz.entities.Institucion;
import dev.server.quiz.repositories.InstitucionRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstitucionService implements DAOService<Institucion> {

    InstitucionRepo repo;

    public InstitucionService(InstitucionRepo repo) {
        this.repo = repo;
    }

    @Override
    public Institucion registrar(Institucion p) throws Exception {
        return repo.save(p);
    }

    @Override
    public void eliminar(Long id) throws Exception {
        repo.deleteById(id);
    }

    @Override
    public Institucion obtener(Long id) throws Exception {
        return repo.findById(id).get();
    }

    @Override
    public List<Institucion> listar() throws Exception {
        return repo.findAll();
    }
}
