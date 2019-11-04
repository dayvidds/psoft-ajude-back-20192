package com.psoft.ajude.daos;

import com.psoft.ajude.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface RespositorioUsuario<T, email extends Serializable> extends JpaRepository<Usuario, String> {
}
