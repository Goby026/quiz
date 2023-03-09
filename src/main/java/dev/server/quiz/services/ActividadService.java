package dev.server.quiz.services;

import dev.server.quiz.entities.Actividad;
import dev.server.quiz.repositories.ActividadRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActividadService implements DAOService<Actividad> {

    private final ActividadRepo repo;

    public ActividadService(ActividadRepo repo) {
        this.repo = repo;
    }

    @Override
    public Actividad registrar(Actividad p) throws Exception {
        return repo.save(p);
    }

    @Override
    public void eliminar(Long id) throws Exception {
        repo.deleteById(id);
    }

    @Override
    public Actividad obtener(Long id) throws Exception {
        return repo.findById(id).get();
    }

    @Override
    public List<Actividad> listar() throws Exception {
        return repo.findAll();
    }
}
