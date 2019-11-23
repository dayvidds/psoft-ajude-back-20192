package com.psoft.ajude.dtos;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class DTOComentario {

    private Integer idComentarioPai;
    private String conteudo;

    public DTOComentario(String conteudo, Integer idComentarioPai) {
        this.conteudo = conteudo;
        this.idComentarioPai = idComentarioPai;
    }

    public Integer getIdComentarioPai() {
        return idComentarioPai;
    }

    public String getConteudo() {
        return conteudo;
    }

}
