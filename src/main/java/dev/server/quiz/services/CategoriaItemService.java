package dev.server.quiz.services;

import dev.server.quiz.entities.CategoriaItem;
import dev.server.quiz.repositories.CategoriaItemRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaItemService implements DAOService<CategoriaItem>{

    private final CategoriaItemRepo repo;

    public CategoriaItemService(CategoriaItemRepo repo) {
        this.repo = repo;
    }

    @Override
    public CategoriaItem registrar(CategoriaItem p) throws Exception {
        return repo.save(p);
    }

    @Override
    public void eliminar(Long id) throws Exception {
        repo.deleteById(id);
    }

    @Override
    public CategoriaItem obtener(Long id) throws Exception {
        return repo.findById(id).get();
    }

    @Override
    public List<CategoriaItem> listar() throws Exception {
        return repo.findAll();
    }
}
