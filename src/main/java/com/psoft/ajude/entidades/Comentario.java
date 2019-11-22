package com.psoft.ajude.entidades;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Comentario {

    @Id
    @GeneratedValue
    private Integer id;
    private String conteudo;
    @OneToOne
    private Comentario comentarioRespondido;
    @OneToOne
    private Usuario donoComentario;

    public Comentario(String conteudo, Usuario donoComentario, Comentario comentarioRespondido) {
        this.comentarioRespondido = comentarioRespondido;
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

    public Comentario getComentarioRespondido() {
        return comentarioRespondido;
    }

    public void setComentarioRespondido(Comentario comentarioRespondido) {
        this.comentarioRespondido = comentarioRespondido;
    }

    public Usuario getDonoComentario() {
        return donoComentario;
    }

    public void setDonoComentario(Usuario donoComentario) {
        this.donoComentario = donoComentario;
    }
}
