package com.psoft.ajude.dtos;

import com.psoft.ajude.entidades.Usuario;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@ApiModel(value = "DTOUsuario")
@AllArgsConstructor
@NoArgsConstructor
public class DTOUsuario {

    @ApiModelProperty(value = "Email do usuario.")
    private String email;
    @ApiModelProperty(value = "Primeiro nome do usuario.")
    private String primeiroNome;
    @ApiModelProperty(value = "Ultimo nome do usuario.")
    private String ultimoNome;

    public DTOUsuario(Usuario usuario) {
        this.email = usuario.getEmail();
        this.primeiroNome = usuario.getPrimeiroNome();
        this.ultimoNome = usuario.getUltimoNome();
    }

    public String getEmail() {
        return email;
    }

    public String getPrimeiroNome() {
        return primeiroNome;
    }

    public String getUltimoNome() {
        return ultimoNome;
    }
}
