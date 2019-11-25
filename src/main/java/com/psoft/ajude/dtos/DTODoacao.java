package com.psoft.ajude.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(value = "DTODoacao")
public class DTODoacao {

    @ApiModelProperty(value = "Quantidade doada para campanha.")
    private Double quantiaDoada;
    @ApiModelProperty(value = "Data em que a doacao foi realizada")
    private Date dataDoacao;

    public Double getQuantiaDoada() {
        return quantiaDoada;
    }

    public Date getDataDoacao() {
        return dataDoacao;
    }

}
