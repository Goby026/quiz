package dev.server.quiz.services;

import dev.server.quiz.entities.Ficha;
import dev.server.quiz.entities.FichaItem;
import dev.server.quiz.entities.Item;
import dev.server.quiz.repositories.FichaItemRepo;
import dev.server.quiz.repositories.FichaRepo;
import dev.server.quiz.repositories.ItemRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FichaItemService implements DAOService<FichaItem>{

    private final FichaItemRepo repo;
    private final ItemRepo itemRepo;
    private final FichaRepo fichaRepo;

    public FichaItemService(FichaItemRepo repo, ItemRepo itemRepo, FichaRepo fichaRepo) {
        this.repo = repo;
        this.itemRepo = itemRepo;
        this.fichaRepo = fichaRepo;
    }

    @Override
    public FichaItem registrar(FichaItem p) throws Exception {
        return repo.save(p);
    }

    public List<FichaItem> registrarTodos(List<FichaItem> fichaItems) throws Exception {
        return repo.saveAll(fichaItems);
    }

    public List<FichaItem> registrarItems(Ficha ficha) throws Exception {
        List<Item> items = itemRepo.findAll();
        List<FichaItem> fichaItems = new ArrayList<FichaItem>();

        for (Item item: items) {
            FichaItem fichaItem = new FichaItem();
            fichaItem.setDescripcion("");
            fichaItem.setValoracion(0);
            fichaItem.setFicha(ficha);
            fichaItem.setItem(item);
            fichaItem.setEvidencias(null);
            repo.save(fichaItem);
            fichaItems.add(fichaItem);
        }

        return fichaItems;
    }

    @Override
    public void eliminar(Long id) throws Exception {
        repo.deleteById(id);
    }

    @Override
    public FichaItem obtener(Long id) throws Exception {
        return repo.findById(id).get();
    }

    @Override
    public List<FichaItem> listar() throws Exception {
        return repo.findAll();
    }

    public List<FichaItem> listar(Long fichaId) throws Exception {
        return repo.findByFichaId(fichaId);
    }
}
