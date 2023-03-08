package dev.server.quiz.services;

import dev.server.quiz.entities.Canal;
import dev.server.quiz.repositories.CanalRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CanalService implements DAOService<Canal> {

    private final CanalRepo repo;

    public CanalService(CanalRepo repo) {
        this.repo = repo;
    }

    @Override
    public Canal registrar(Canal p) throws Exception {
        return repo.save(p);
    }

    @Override
    public void eliminar(Long id) throws Exception {
        repo.deleteById(id);
    }

    @Override
    public Canal obtener(Long id) throws Exception {
        return repo.findById(id).get();
    }

    @Override
    public List<Canal> listar() throws Exception {
        return repo.findAll();
    }
}
