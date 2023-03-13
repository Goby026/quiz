package dev.server.quiz.services;

import dev.server.quiz.entities.Medio;
import dev.server.quiz.repositories.MedioRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedioService implements DAOService<Medio> {

    private final MedioRepo repo;

    public MedioService(MedioRepo repo) {
        this.repo = repo;
    }

    @Override
    public Medio registrar(Medio p) throws Exception {
        return repo.save(p);
    }

    @Override
    public void eliminar(Long id) throws Exception {
        repo.deleteById(id);
    }

    @Override
    public Medio obtener(Long id) throws Exception {
        return repo.findById(id).get();
    }

    @Override
    public List<Medio> listar() throws Exception {
        return repo.findAll();
    }
}
