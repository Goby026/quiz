package dev.server.quiz.services;

import dev.server.quiz.entities.Ficha;
import dev.server.quiz.repositories.FichaRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FichaService implements DAOService<Ficha> {

    private final FichaRepo repo;

    public FichaService(FichaRepo repo) {
        this.repo = repo;
    }

    @Override
    public Ficha registrar(Ficha p) throws Exception {
        return repo.save(p);
    }

    @Override
    public void eliminar(Long id) throws Exception {
        repo.deleteById(id);
    }

    @Override
    public Ficha obtener(Long id) throws Exception {
        return repo.findById(id).get();
    }

    @Override
    public List<Ficha> listar() throws Exception {
        return repo.findAll();
    }
}
