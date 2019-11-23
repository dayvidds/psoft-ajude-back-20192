package com.psoft.ajude.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.psoft.ajude.dtos.DTODoacao;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Doacao {

    @Id
    @GeneratedValue
    private Integer id;
    @OneToOne
    @JoinColumn(name = "idCampanha")
    @JsonIgnore
    private Campanha campanha;
    private Double quantiaDoada;
    private Date dataDoacao;
    @OneToOne
    private Usuario doador;

    public Doacao(Campanha campanha, DTODoacao dtoDoacao, Usuario usuario) {
        this.campanha = campanha;
        this.quantiaDoada = dtoDoacao.getQuantiaDoada();
        this.dataDoacao = dtoDoacao.getDataDoacao();
        this.doador = usuario;
    }

    public Integer getId() {
        return id;
    }

    public Campanha getCampanha() {
        return campanha;
    }

    public Double getQuantiaDoada() {
        return quantiaDoada;
    }

    public void setQuantiaDoada(Double quantiaDoada) {
        this.quantiaDoada = quantiaDoada;
    }

    public Date getDataDoacao() {
        return dataDoacao;
    }

    public void setDataDoacao(Date dataDoacao) {
        this.dataDoacao = dataDoacao;
    }

    public Usuario getDoador() {
        return doador;
    }

    public void setDoador(Usuario doador) {
        this.doador = doador;
    }
}
