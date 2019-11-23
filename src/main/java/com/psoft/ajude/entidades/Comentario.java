package com.psoft.ajude.entidades;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Comentario {

    @Id
    @GeneratedValue
    private Integer id;
    private String conteudo;
    @OneToOne
    @JoinColumn(name = "idCampanha")
    @JsonIgnore
    private Campanha campanha;
    @OneToMany
    @JoinColumn(name = "idComentarioRespondido")
    private List<Comentario> respostas;
    @OneToOne
    private Usuario donoComentario;

    public Comentario(Campanha campanha, String conteudo, Usuario donoComentario) {
        this.campanha = campanha;
        this.respostas = new ArrayList<>();
        this.conteudo = conteudo;
        this.donoComentario = donoComentario;
    }

    public Integer getId() {
        return id;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public Campanha getCampanha() {
        return campanha;
    }

    public List<Comentario> getRespostas() {
        return respostas;
    }

    public void adicionarResposta(Comentario resposta) {
        this.respostas.add(resposta);
    }

    public Usuario getDonoComentario() {
        return donoComentario;
    }

    public void setDonoComentario(Usuario donoComentario) {
        this.donoComentario = donoComentario;
    }
}
