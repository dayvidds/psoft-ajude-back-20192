package com.psoft.ajude.dtos;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class DTOPesquisa {
    private String parametro;

    public String getParametro() {
        return parametro;
    }
}
