package com.psoft.ajude.entidades;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Usuario {
    @Id @GeneratedValue
    @Getter private String email;
    @Getter @Setter private String primeiroNome;
    @Getter @Setter private String ultimoNome;
    @Getter @Setter private String numCartao;
    @Getter @Setter private String senha;

}
