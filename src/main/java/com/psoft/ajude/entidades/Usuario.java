package com.psoft.ajude.entidades;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    private String email;
    private String primeiroNome;
    private String ultimoNome;
    private String numCartao;
    private String senha;

    public String getEmail() {
        return email;
    }

    public String getPrimeiroNome() {
        return primeiroNome;
    }

    public String getUltimoNome() {
        return ultimoNome;
    }

    public String getNumCartao() {
        return numCartao;
    }

    public String getSenha() {
        return senha;
    }

    public void setPrimeiroNome(String primeiroNome) {
        this.primeiroNome = primeiroNome;
    }

    public void setUltimoNome(String ultimoNome) {
        this.ultimoNome = ultimoNome;
    }

    public void setNumCartao(String numCartao) {
        this.numCartao = numCartao;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(email, usuario.email) &&
                Objects.equals(primeiroNome, usuario.primeiroNome) &&
                Objects.equals(ultimoNome, usuario.ultimoNome) &&
                Objects.equals(numCartao, usuario.numCartao) &&
                Objects.equals(senha, usuario.senha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, primeiroNome, ultimoNome, numCartao, senha);
    }
}
