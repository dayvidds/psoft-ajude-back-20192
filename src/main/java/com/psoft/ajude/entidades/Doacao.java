package com.psoft.ajude.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Date;

@Entity
public class Doacao {

    @Id
    @GeneratedValue
    private Integer id;
    private Double quantiaDoada;
    private Date dataDoacao;
    @OneToOne
    private Usuario doador;

    public Integer getId() {
        return id;
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
