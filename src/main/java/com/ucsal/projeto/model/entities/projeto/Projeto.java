package com.ucsal.projeto.model.entities.projeto;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects; // Necessário para hashCode e equals

@Entity
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

    // --- 1. CONSTRUTORES (Substituindo @NoArgsConstructor e @AllArgsConstructor) ---

    // Construtor padrão vazio (necessário para JPA)
    public Projeto() {
    }

    // Construtor completo (opcional, mas útil)
    public Projeto(Long id, String nome, String objetivo, String escopoResumo, String publicoAlvo,
                   LocalDate dataPrevistaInicio, String professorSolicitante, StatusProjeto status) {
        this.id = id;
        this.nome = nome;
        this.objetivo = objetivo;
        this.escopoResumo = escopoResumo;
        this.publicoAlvo = publicoAlvo;
        this.dataPrevistaInicio = dataPrevistaInicio;
        this.professorSolicitante = professorSolicitante;
        this.status = status;
    }

    // --- 2. GETTERS E SETTERS (Substituindo @Getter e @Setter) ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String getEscopoResumo() {
        return escopoResumo;
    }

    public void setEscopoResumo(String escopoResumo) {
        this.escopoResumo = escopoResumo;
    }

    public String getPublicoAlvo() {
        return publicoAlvo;
    }

    public void setPublicoAlvo(String publicoAlvo) {
        this.publicoAlvo = publicoAlvo;
    }

    public LocalDate getDataPrevistaInicio() {
        return dataPrevistaInicio;
    }

    public void setDataPrevistaInicio(LocalDate dataPrevistaInicio) {
        this.dataPrevistaInicio = dataPrevistaInicio;
    }

    public String getProfessorSolicitante() {
        return professorSolicitante;
    }

    public void setProfessorSolicitante(String professorSolicitante) {
        this.professorSolicitante = professorSolicitante;
    }

    public StatusProjeto getStatus() {
        return status;
    }

    public void setStatus(StatusProjeto status) {
        this.status = status;
    }

    // --- 3. toString (Substituindo parte do @Data) ---

    @Override
    public String toString() {
        return "Projeto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", objetivo='" + objetivo + '\'' +
                ", escopoResumo='" + escopoResumo + '\'' +
                ", publicoAlvo='" + publicoAlvo + '\'' +
                ", dataPrevistaInicio=" + dataPrevistaInicio +
                ", professorSolicitante='" + professorSolicitante + '\'' +
                ", status=" + status +
                '}';
    }

    // --- 4. equals e hashCode (Substituindo parte do @Data, fundamental para coleções) ---

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Projeto projeto = (Projeto) o;
        return Objects.equals(id, projeto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}















//
//import jakarta.persistence.*;
//import lombok.Data;
//import java.time.LocalDate;
//
//@Entity
//@Data
//@Table(name = "projetos")
//public class Projeto {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String nome;
//    private String objetivo;
//    private String escopoResumo;
//    private String publicoAlvo;
//    private LocalDate dataPrevistaInicio;
//
//    private String professorSolicitante;
//
//    @Enumerated(EnumType.STRING)
//    private StatusProjeto status = StatusProjeto.AGUARDANDO_ANALISE_PRELIMINAR;
//
//}
