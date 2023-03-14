package dev.server.quiz.services;

import dev.server.quiz.entities.Indicador;
import dev.server.quiz.repositories.IndicadorRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndicadorService implements DAOService<Indicador> {

    private final IndicadorRepo repo;

    public IndicadorService(IndicadorRepo repo) {
        this.repo = repo;
    }

    @Override
    public Indicador registrar(Indicador p) throws Exception {
        return repo.save(p);
    }

    @Override
    public void eliminar(Long id) throws Exception {
        repo.deleteById(id);
    }

    @Override
    public Indicador obtener(Long id) throws Exception {
        return repo.findById(id).get();
    }

    @Override
    public List<Indicador> listar() throws Exception {
        return repo.findAll();
    }
}
