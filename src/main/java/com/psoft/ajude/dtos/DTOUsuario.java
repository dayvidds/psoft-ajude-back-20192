package com.psoft.ajude.dtos;

import com.psoft.ajude.entidades.Campanha;
import com.psoft.ajude.entidades.Doacao;
import com.psoft.ajude.entidades.Usuario;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class DTOUsuario {
    private List<Doacao> campanhasDoacao;
    private List<Campanha> campanhasDono;
    private String email;
    private String primeiroNome;
    private String ultimoNome;

    public DTOUsuario(Usuario usuario) {
        this.email = usuario.getEmail();
        this.primeiroNome = usuario.getPrimeiroNome();
        this.ultimoNome = usuario.getUltimoNome();
    }

    public DTOUsuario(Usuario usuario, List<Doacao> campanhasDoacao, List<Campanha> campanhasDono) {
        this.campanhasDoacao = campanhasDoacao;
        this.campanhasDono = campanhasDono;
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
