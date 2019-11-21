package com.psoft.ajude.dtos;

import com.psoft.ajude.entidades.Campanha;
import com.psoft.ajude.entidades.Usuario;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class DTOComentario {

    private Campanha campanha;
    private Integer idComentarioPai;
    private String conteudo;
    private Usuario donoComentario;
    private Integer id;

    public DTOComentario(Usuario usuario, String conteudo, Campanha campanha, Integer idComentarioPai){
        this.conteudo = conteudo;
        this.donoComentario = usuario;
        this.campanha = campanha;
        this.idComentarioPai = idComentarioPai;
    }

    public Campanha getCampanha() {
        return campanha;
    }

    public Integer getIdComentarioPai() {
        return idComentarioPai;
    }

    public String getConteudo() {
        return conteudo;
    }

    public Usuario getDonoComentario() {
        return donoComentario;
    }

    public Integer getId() {
        return id;
    }

    public void setIdComentario(Integer id) {
        this.id = id;
    }
}
