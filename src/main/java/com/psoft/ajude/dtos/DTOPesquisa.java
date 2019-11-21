package com.psoft.ajude.dtos;


import com.psoft.ajude.entidades.StatusCampanha;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
public class DTOPesquisa {
    private String parametro;
    private Set<StatusCampanha> statusProcurados;

    public String getParametro() {
        return parametro;
    }

    public Set<StatusCampanha> getStatusProcurados() {
        return statusProcurados;
    }
}
