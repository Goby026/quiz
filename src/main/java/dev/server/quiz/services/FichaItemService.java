package dev.server.quiz.services;

import dev.server.quiz.entities.*;
import dev.server.quiz.repositories.ConsolidadoRepo;
import dev.server.quiz.repositories.FichaItemRepo;
import dev.server.quiz.repositories.ItemRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FichaItemService implements DAOService<FichaItem>{

    private static final Logger logger = LoggerFactory.getLogger(FichaItemService.class);

    private final FichaItemRepo repo;
//    private final ItemRepo itemRepo;
    private final ItemService itemService;
    private final CategoriaItemService categoriaItemService;
    private final ConsolidadoRepo consolidadoRepo;

    public FichaItemService(FichaItemRepo repo, ItemRepo itemRepo, ItemService itemService, CategoriaItemService categoriaItemService, ConsolidadoRepo consolidadoRepo) {
        this.repo = repo;
        this.itemService = itemService;
        this.categoriaItemService = categoriaItemService;
        this.consolidadoRepo = consolidadoRepo;
    }

    @Override
    public FichaItem registrar(FichaItem p) throws Exception {
        return repo.save(p);
    }

    public List<FichaItem> registrarTodos(List<FichaItem> fichaItems) throws Exception {
        return repo.saveAll(fichaItems);
    }

    public List<FichaItem> registrarItems(Ficha ficha) throws Exception {
//        List<Item> items = itemRepo.findAll();
        List<FichaItem> fichaItems = new ArrayList<FichaItem>();

        for (Item item: itemService.listar() ) {

            FichaItem fichaItem = new FichaItem();
            fichaItem.setDescripcion("");
            fichaItem.setValoracion(0);
            fichaItem.setFicha(ficha);
            fichaItem.setItem(item);
//            fichaItem.setEvidencias(null);
            repo.save(fichaItem);
            fichaItems.add(fichaItem);
        }



//***********REGISTRA CONSOLIDADO POR CADA CATEGORIA ITEM***********
        List<CategoriaItem> categorias = categoriaItemService.listar();

        //TODO: REVISAR BUG AQUI
        for (CategoriaItem cat : categorias) {
            int cantItems = 0;
            int cantNo = 0;
            Consolidado consolidado = new Consolidado();

            for (FichaItem fi: fichaItems ) {
                if (fi.getItem().getCategoriaItem() == cat){
                    cantItems += 1;
                }
            }

            consolidado.setCantidadItem(cantItems);
            consolidado.setNoCat(0);
            consolidado.setProcesoCat(0);
            consolidado.setSiCat(0);
            consolidado.setTotal(0);
            consolidado.setNivelAvance(0.0);

            consolidado.setCategoriaItem(cat);
            consolidado.setFicha(ficha);

            consolidadoRepo.save(consolidado);
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
