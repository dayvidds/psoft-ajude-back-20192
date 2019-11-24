package com.psoft.ajude.entidades;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(value = "Id do comentario.")
    private Integer id;
    @ApiModelProperty(value = "Conteudo do comentario.")
    private String conteudo;
    @OneToOne
    @JoinColumn(name = "idCampanha")
    @JsonIgnore
    @ApiModelProperty(value = "Campanha a qual o comentario pertence.")
    private Campanha campanha;
    @OneToMany
    @JoinColumn(name = "idComentarioRespondido")
    @ApiModelProperty(value = "Respostas do comentario.")
    private List<Comentario> respostas;
    @OneToOne
    @ApiModelProperty(value = "Dono do comentario.")
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
