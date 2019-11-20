package com.psoft.ajude.entidades;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Comentario {

    @Id
    @GeneratedValue
    private Integer id;
    private String conteudo;
    @OneToOne
    private Comentario resposta;
    @OneToOne
    private Usuario donoComentario;

    public Integer getId() {
        return id;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public Comentario getResposta() {
        return resposta;
    }

    public void setResposta(Comentario resposta) {
        this.resposta = resposta;
    }

    public Usuario getDonoComentario() {
        return donoComentario;
    }

    public void setDonoComentario(Usuario donoComentario) {
        this.donoComentario = donoComentario;
    }
}
