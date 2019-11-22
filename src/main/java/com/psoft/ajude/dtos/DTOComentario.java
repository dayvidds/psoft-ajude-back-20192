package com.psoft.ajude.dtos;

import com.psoft.ajude.entidades.Campanha;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class DTOComentario {

    private String idCampanha;
    private Integer idComentarioPai;
    private String conteudo;
    private DTOUsuario donoComentario;
    private Integer id;

    public DTOComentario(DTOUsuario dtoUsuario, String conteudo, String idCampanha, Integer idComentarioPai){
        this.conteudo = conteudo;
        this.donoComentario = dtoUsuario;
        this.idCampanha = idCampanha;
        this.idComentarioPai = idComentarioPai;
    }

    public String getIdCampanha() {
        return idCampanha;
    }

    public Integer getIdComentarioPai() {
        return idComentarioPai;
    }

    public String getConteudo() {
        return conteudo;
    }

    public DTOUsuario getDonoComentario() {
        return donoComentario;
    }

    public Integer getId() {
        return id;
    }

    public void setIdComentario(Integer id) {
        this.id = id;
    }
}
