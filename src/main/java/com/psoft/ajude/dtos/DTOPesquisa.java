package com.psoft.ajude.dtos;


import com.psoft.ajude.entidades.StatusCampanha;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Set;

@ApiModel(value = "DTOCampanha")
@AllArgsConstructor
@NoArgsConstructor
public class DTOPesquisa {

    @ApiModelProperty(value = "Substring que sera pesquisada.")
    private String parametro;

    @ApiModelProperty(value = "Status das campanhas.")
    private Set<StatusCampanha> statusProcurados;

    public String getParametro() {
        return parametro;
    }

    public Set<StatusCampanha> getStatusProcurados() {
        return statusProcurados;
    }
}
