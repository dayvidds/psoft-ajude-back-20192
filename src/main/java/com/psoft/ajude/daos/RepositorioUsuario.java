package com.psoft.ajude.daos;

import com.psoft.ajude.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Optional;

@Repository
public interface RepositorioUsuario<T, ID extends Serializable> extends JpaRepository<Usuario, String> {

    Optional<T> findByEmail(String email);
}
