package dev.server.quiz.services;

import dev.server.quiz.entities.Area;
import dev.server.quiz.repositories.AreaRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaService implements DAOService<Area> {

    private final AreaRepo repo;

    public AreaService(AreaRepo repo) {
        this.repo = repo;
    }

    @Override
    public Area registrar(Area p) throws Exception {
        return repo.save(p);
    }

    @Override
    public void eliminar(Long id) throws Exception {
        repo.deleteById(id);
    }

    @Override
    public Area obtener(Long id) throws Exception {
        return repo.findById(id).get();
    }

    @Override
    public List<Area> listar() throws Exception {
        return repo.findAll();
    }
}
