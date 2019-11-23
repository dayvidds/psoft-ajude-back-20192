package com.psoft.ajude.dtos;

import com.psoft.ajude.entidades.Campanha;
import com.psoft.ajude.entidades.Doacao;
import com.psoft.ajude.entidades.Usuario;

import java.util.List;

public class DTOUsuarioPerfil {
    private List<Doacao> campanhasDoacao;
    private List<Campanha> campanhasDono;
    private String email;
    private String primeiroNome;
    private String ultimoNome;

    public DTOUsuarioPerfil(Usuario usuario, List<Doacao> campanhasDoacao, List<Campanha> campanhasDono) {
        this.campanhasDoacao = campanhasDoacao;
        this.campanhasDono = campanhasDono;
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

    public List<Doacao> getCampanhasDoacao() {
        return campanhasDoacao;
    }

    public List<Campanha> getCampanhasDono() {
        return campanhasDono;
    }
}
