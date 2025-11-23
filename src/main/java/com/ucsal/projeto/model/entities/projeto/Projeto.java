package com.ucsal.projeto.model.entities.projeto;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "projetos")
public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String objetivo;
    private String escopoResumo;
    private String publicoAlvo;
    private LocalDate dataPrevistaInicio;

    private String professorSolicitante;

    @Enumerated(EnumType.STRING)
    private StatusProjeto status = StatusProjeto.AGUARDANDO_ANALISE_PRELIMINAR;

}
