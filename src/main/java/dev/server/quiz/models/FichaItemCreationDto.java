package dev.server.quiz.models;

import dev.server.quiz.entities.FichaItem;

import java.util.List;

public class FichaItemCreationDto {

    private List<FichaItem> fichaItems;

    public FichaItemCreationDto(List<FichaItem> fichaItems) {
        this.fichaItems = fichaItems;
    }

    public void addFichaItem(FichaItem fichaItem){
        this.fichaItems.add(fichaItem);
    }

    public List<FichaItem> getFichaItems() {
        return fichaItems;
    }

    public void setFichaItems(List<FichaItem> fichaItems) {
        this.fichaItems = fichaItems;
    }
}
