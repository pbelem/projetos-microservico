package com.ucsal.projeto.controller;

import com.ucsal.projeto.model.entities.projeto.Projeto;
import com.ucsal.projeto.model.entities.projeto.StatusProjeto;
import com.ucsal.projeto.model.repository.ProjetoRepository;
import com.ucsal.projeto.model.service.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/projetos")
public class ProjetoController {

    @Autowired
    private ProjetoService projetoService;

    @Autowired
    private ProjetoRepository projetoRepository;

    @PostMapping("/solicitar")
    @PreAuthorize("hasRole('PROFESSOR')")
    public ResponseEntity<?> solicitarProjeto(@RequestBody Projeto projeto, Authentication authentication) {
        try {
            String professorEmail = authentication.getName();

            Projeto novoProjeto = projetoService.solicitarProjeto(projeto, professorEmail);

            return ResponseEntity.status(HttpStatus.CREATED).body(novoProjeto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESSOR')")
    public List<Projeto> listarTodos() {
        return projetoRepository.findAll();
    }

    // apenas adm
    @PatchMapping("/{id}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Projeto> atualizarStatus(@PathVariable Long id,
                                                   @RequestParam StatusProjeto novoStatus) {
        Optional<Projeto> projetoAtualizado = projetoService.alterarStatus(id, novoStatus);

        return projetoAtualizado.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // apenas adm
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletarProjeto(@PathVariable Long id) {
        if (projetoRepository.existsById(id)) {
            projetoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
