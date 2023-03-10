package dev.server.quiz.services;

import dev.server.quiz.entities.Dre;
import dev.server.quiz.repositories.DreRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DreService implements DAOService<Dre> {

    private final DreRepo repo;

    public DreService(DreRepo repo) {
        this.repo = repo;
    }

    @Override
    public Dre registrar(Dre p) throws Exception {
        return repo.save(p);
    }

    @Override
    public void eliminar(Long id) throws Exception {
        repo.deleteById(id);
    }

    @Override
    public Dre obtener(Long id) throws Exception {
        return repo.findById(id).get();
    }

    @Override
    public List<Dre> listar() throws Exception {
        return repo.findAll();
    }
}
