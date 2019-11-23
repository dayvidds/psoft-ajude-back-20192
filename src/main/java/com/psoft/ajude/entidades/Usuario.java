package com.psoft.ajude.entidades;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String numeroDoCartao;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String senha;
    private String userName;

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPrimeiroNome() {
        return primeiroNome;
    }

    public void setPrimeiroNome(String primeiroNome) {
        this.primeiroNome = primeiroNome;
    }

    public String getUltimoNome() {
        return ultimoNome;
    }

    public void setUltimoNome(String ultimoNome) {
        this.ultimoNome = ultimoNome;
    }

    public String getNumeroDoCartao() {
        return numeroDoCartao;
    }

    public void setNumeroDoCartao(String numeroDoCartao) {
        this.numeroDoCartao = numeroDoCartao;
    }

    public String getSenha() {
        return senha;
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
                Objects.equals(numeroDoCartao, usuario.numeroDoCartao) &&
                Objects.equals(senha, usuario.senha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, primeiroNome, ultimoNome, numeroDoCartao, senha);
    }
}
