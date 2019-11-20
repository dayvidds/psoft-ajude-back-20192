package com.psoft.ajude.dtos;

import com.psoft.ajude.entidades.Usuario;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class DTOUsuario {
    private String email;
    private String primeiroNome;
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
