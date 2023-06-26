package dev.server.quiz.models;

import dev.server.quiz.entities.Consolidado;

import java.util.List;

public class ConsolidadoCreationDto {

    private List<Consolidado> consolidados;

    public List<Consolidado> getConsolidados() {
        return consolidados;
    }

    public void setConsolidados(List<Consolidado> consolidados) {
        this.consolidados = consolidados;
    }

    public ConsolidadoCreationDto(List<Consolidado> consolidados) {
        this.consolidados = consolidados;
    }

    public void addConsolidado(Consolidado consolidado){
        this.consolidados.add(consolidado);
    }
}
