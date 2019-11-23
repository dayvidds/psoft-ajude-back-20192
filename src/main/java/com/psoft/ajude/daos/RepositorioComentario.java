package com.psoft.ajude.daos;

import com.psoft.ajude.entidades.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface RepositorioComentario<T, ID extends Serializable> extends JpaRepository<Comentario, Integer> {
}
