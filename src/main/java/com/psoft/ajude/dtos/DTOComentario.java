package com.psoft.ajude.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@ApiModel(value = "DTOComentario")
@AllArgsConstructor
@NoArgsConstructor
public class DTOComentario {

    @ApiModelProperty(value = "Id do comentario a qual ele se refere.")
    private Integer idComentarioPai;
    @ApiModelProperty(value = "Conteudo do comentario")
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
