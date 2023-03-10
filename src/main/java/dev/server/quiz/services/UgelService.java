package dev.server.quiz.services;

import dev.server.quiz.entities.Ugel;
import dev.server.quiz.repositories.UgelRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UgelService implements DAOService<Ugel> {

    private final UgelRepo repo;

    public UgelService(UgelRepo repo) {
        this.repo = repo;
    }

    @Override
    public Ugel registrar(Ugel p) throws Exception {
        return repo.save(p);
    }

    @Override
    public void eliminar(Long id) throws Exception {
        repo.deleteById(id);
    }

    @Override
    public Ugel obtener(Long id) throws Exception {
        return repo.findById(id).get();
    }

    @Override
    public List<Ugel> listar() throws Exception {
        return repo.findAll();
    }
}
