package br.univille.gestaodeartigosesportivos.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.univille.sistemachocolateria.entity.Venda;

@Repository
public interface VendaRepository
        extends JpaRepository<Venda, Long> {

}
