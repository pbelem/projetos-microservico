package com.ucsal.projeto.model.repository;

import com.ucsal.projeto.model.entities.projeto.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
    boolean existsByNome(String nome);
}
