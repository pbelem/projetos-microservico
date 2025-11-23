package com.ucsal.projeto.model.service;

import com.ucsal.projeto.model.entities.projeto.Projeto;
import com.ucsal.projeto.model.entities.projeto.StatusProjeto;
import com.ucsal.projeto.model.repository.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
public class ProjetoService {

    @Autowired
    private ProjetoRepository repository;

    @Transactional
    public Projeto solicitarProjeto(Projeto projeto, String professorSolicitante) {
        if (repository.existsByNome(projeto.getNome())) {
            throw new IllegalArgumentException("Projeto com este nome já foi solicitado.");
        }

        projeto.setProfessorSolicitante(professorSolicitante);
        projeto.setStatus(StatusProjeto.AGUARDANDO_ANALISE_PRELIMINAR);

        return repository.save(projeto);
    }

    @Transactional
    public Optional<Projeto> alterarStatus(Long id, StatusProjeto novoStatus) {
        Optional<Projeto> projetoOpt = repository.findById(id);

        if (projetoOpt.isPresent()) {
            Projeto projeto = projetoOpt.get();
            projeto.setStatus(novoStatus);
            return Optional.of(repository.save(projeto));
        }
        return Optional.empty();
    }
}