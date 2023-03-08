package dev.server.quiz.services;

import dev.server.quiz.entities.Cargo;
import dev.server.quiz.repositories.CargoRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CargoService implements DAOService<Cargo> {

    private final CargoRepo repo;

    public CargoService(CargoRepo repo) {
        this.repo = repo;
    }

    @Override
    public Cargo registrar(Cargo p) throws Exception {
        return repo.save(p);
    }

    @Override
    public void eliminar(Long id) throws Exception {
        repo.deleteById(id);
    }

    @Override
    public Cargo obtener(Long id) throws Exception {
        return repo.findById(id).get();
    }

    @Override
    public List<Cargo> listar() throws Exception {
        return repo.findAll();
    }

    public Page<Cargo> listar(Pageable pageable) throws Exception {
        return repo.findAll(pageable);
    }
}
