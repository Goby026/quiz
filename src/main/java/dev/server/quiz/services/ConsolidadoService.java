package dev.server.quiz.services;

import dev.server.quiz.entities.Consolidado;
import dev.server.quiz.entities.Ficha;
import dev.server.quiz.repositories.ConsolidadoRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsolidadoService implements DAOService<Consolidado>{

    private final ConsolidadoRepo repo;

    public ConsolidadoService(ConsolidadoRepo repo) {
        this.repo = repo;
    }

    @Override
    public Consolidado registrar(Consolidado p) throws Exception {
        return repo.save(p);
    }

    public List<Consolidado> registrarTodos(List<Consolidado> consolidados) throws Exception {
        return repo.saveAll(consolidados);
    }

    @Override
    public void eliminar(Long id) throws Exception {
        repo.deleteById(id);
    }

    @Override
    public Consolidado obtener(Long id) throws Exception {
        return repo.findById(id).get();
    }

    @Override
    public List<Consolidado> listar() throws Exception {
        return repo.findAll();
    }

    public List<Consolidado> listarPorFicha(Ficha ficha) throws Exception {
        return repo.findByFicha(ficha);
    }

    public List<Consolidado> listarPorIdFicha(long id) throws Exception {
        return repo.findByFichaId(id);
    }
}
