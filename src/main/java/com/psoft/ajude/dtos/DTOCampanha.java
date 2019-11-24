package com.psoft.ajude.dtos;

import com.psoft.ajude.entidades.Campanha;
import com.psoft.ajude.entidades.StatusCampanha;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@ApiModel(value = "DTOCampanha")
@AllArgsConstructor
@NoArgsConstructor
public class DTOCampanha {

    @ApiModelProperty(value = "Nome curto da campanha.")
    private String nomeCurto;
    @ApiModelProperty(value = "Descricao da campanha.")
    private String descricao;
    @ApiModelProperty(value = "Data que a campanha sera encerrada da campanha.")
    private Date deadline;
    @ApiModelProperty(value = "Valor que a campanha deseja obter.")
    private Double meta;
    @ApiModelProperty(value = "Status da campanha.")
    private StatusCampanha statusCampanha;
    @ApiModelProperty(value = "URL da campanha.")
    private String url;
    @ApiModelProperty(value = "Usuario dono da campanha.")
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
