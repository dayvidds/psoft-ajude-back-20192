package com.psoft.ajude.dtos;

import com.psoft.ajude.entidades.Campanha;
import com.psoft.ajude.entidades.Usuario;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import java.util.Set;

@ApiModel(value = "DTOUsuarioPerfil")
public class DTOUsuarioPerfil {

    @ApiModelProperty(value = "Campanhas a qual o usuario doou")
    private Set<Campanha> campanhasDoacao;
    @ApiModelProperty(value = "Campanhas a qual o usuario é dono")
    private List<Campanha> campanhasDono;
    @ApiModelProperty(value = "Email do usuario")
    private String email;
    @ApiModelProperty(value = "Primeiro nome do usuario")
    private String primeiroNome;
    @ApiModelProperty(value = "Ultimo nome do usuario")
    private String ultimoNome;

    public DTOUsuarioPerfil(Usuario usuario, Set<Campanha> campanhasDoacao, List<Campanha> campanhasDono) {
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

    public Set<Campanha> getCampanhasDoacao() {
        return campanhasDoacao;
    }

    public List<Campanha> getCampanhasDono() {
        return campanhasDono;
    }
}
