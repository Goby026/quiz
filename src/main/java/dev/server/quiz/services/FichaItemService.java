package dev.server.quiz.services;

import dev.server.quiz.entities.Ficha;
import dev.server.quiz.entities.FichaItem;
import dev.server.quiz.entities.Item;
import dev.server.quiz.repositories.FichaItemRepo;
import dev.server.quiz.repositories.FichaRepo;
import dev.server.quiz.repositories.ItemRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

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

    public List<FichaItem> registrarItems() throws Exception {
        List<Item> items = itemRepo.findAll();
        List<FichaItem> fichaItems = new ArrayList<FichaItem>();

        Ficha ficha = new Ficha();
        ficha.setUsuario("User-test");
        ficha.setFecha(new Date());

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
}
