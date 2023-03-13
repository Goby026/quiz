package dev.server.quiz.services;

import dev.server.quiz.entities.Nivel;
import dev.server.quiz.repositories.NivelRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NivelService implements DAOService<Nivel> {

    private final NivelRepo repo;

    public NivelService(NivelRepo repo) {
        this.repo = repo;
    }

    @Override
    public Nivel registrar(Nivel p) throws Exception {
        return repo.save(p);
    }

    @Override
    public void eliminar(Long id) throws Exception {
        repo.deleteById(id);
    }

    @Override
    public Nivel obtener(Long id) throws Exception {
        return repo.findById(id).get();
    }

    @Override
    public List<Nivel> listar() throws Exception {
        return repo.findAll();
    }
}
