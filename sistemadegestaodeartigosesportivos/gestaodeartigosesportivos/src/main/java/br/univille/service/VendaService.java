package br.univille.gestaodeartigosesportivos.entity;

import java.util.List;

import br.univille.sistemachocolateria.entity.Venda;

public interface VendaService {
    List<Venda> getAll();

    void save(Venda venda);

    Venda findById(long id);

}
