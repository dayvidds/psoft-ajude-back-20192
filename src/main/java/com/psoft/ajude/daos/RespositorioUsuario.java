package com.psoft.ajude.daos;

import com.psoft.ajude.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.Optional;

public interface RespositorioUsuario<T, ID extends Serializable> extends JpaRepository<Usuario, String> {


    Optional<T> findByEmail(String email);
}
