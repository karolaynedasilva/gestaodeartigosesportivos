package br.univille.gestaodeartigosesportivos.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.univille.sistemachocolateria.entity.Funcionario;

@Repository
public interface FuncinarioRepository extends JpaRepository<Funcionario, Long>{
    
    
}
