package dev.server.quiz.services;

import dev.server.quiz.entities.Docente;
import dev.server.quiz.repositories.DocenteRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocenteService implements DAOService<Docente> {

    private final DocenteRepo repo;

    public DocenteService(DocenteRepo repo) {
        this.repo = repo;
    }

    @Override
    public Docente registrar(Docente p) throws Exception {
        return repo.save(p);
    }

    @Override
    public void eliminar(Long id) throws Exception {
        repo.deleteById(id);
    }

    @Override
    public Docente obtener(Long id) throws Exception {
        return repo.findById(id).get();
    }

    @Override
    public List<Docente> listar() throws Exception {
        return repo.findAll();
    }
}
