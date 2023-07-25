package dev.server.quiz.services;

import dev.server.quiz.entities.TipoMedio;
import dev.server.quiz.repositories.TipoMedioRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoMedioService implements DAOService<TipoMedio> {

    private final TipoMedioRepo repo;

    public TipoMedioService(TipoMedioRepo repo) {
        this.repo = repo;
    }

    @Override
    public TipoMedio registrar(TipoMedio p) throws Exception {
        return repo.save(p);
    }

    @Override
    public void eliminar(Long id) throws Exception {
        repo.deleteById(id);
    }

    @Override
    public TipoMedio obtener(Long id) throws Exception {
        return repo.findById(id).get();
    }

    @Override
    public List<TipoMedio> listar() throws Exception {
        return repo.findAll();
    }
}
