package dev.server.quiz.services;

import dev.server.quiz.entities.Item;
import dev.server.quiz.repositories.ItemRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService implements DAOService<Item>{

    private final ItemRepo repo;

    public ItemService(ItemRepo repo) {
        this.repo = repo;
    }

    @Override
    public Item registrar(Item p) throws Exception {
        return repo.save(p);
    }

    @Override
    public void eliminar(Long id) throws Exception {
        repo.deleteById(id);
    }

    @Override
    public Item obtener(Long id) throws Exception {
        return repo.findById(id).get();
    }

    @Override
    public List<Item> listar() throws Exception {
        return repo.findAll();
    }
}
