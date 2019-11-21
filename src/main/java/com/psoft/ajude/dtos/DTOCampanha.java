package com.psoft.ajude.dtos;

import com.psoft.ajude.entidades.Campanha;
import com.psoft.ajude.entidades.StatusCampanha;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
public class DTOCampanha {

    private String nomeCurto;
    private String descricao;
    private Date deadline;
    private Double meta;
    private StatusCampanha statusCampanha;
    private String url;
    private DTOUsuario usuarioDono;

    public DTOCampanha(Campanha campanha, DTOUsuario dtoUsuario) {
        this.nomeCurto = campanha.getNomeCurto();
        this.descricao = campanha.getDescricao();
        this.deadline = campanha.getDeadline();
        this.meta = campanha.getMeta();
        this.statusCampanha = campanha.getStatusCampanha();
        this.url = campanha.getURL();
        this.usuarioDono = dtoUsuario;

    }

    public String getNomeCurto() {
        return nomeCurto;
    }

    public String getDescricao() {
        return descricao;
    }

    public Date getDeadline() {
        return deadline;
    }

    public Double getMeta() {
        return meta;
    }

    public StatusCampanha getStatusCampanha() {
        return statusCampanha;
    }

    public String getUrl() {
        return url;
    }

    public DTOUsuario getUsuarioDono() {
        return usuarioDono;
    }
}
