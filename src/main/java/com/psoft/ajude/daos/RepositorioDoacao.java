package com.psoft.ajude.daos;

import com.psoft.ajude.entidades.Doacao;
import com.psoft.ajude.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface RepositorioDoacao<T, ID extends Serializable> extends JpaRepository<Doacao, Integer> {
    List<Doacao> findByDoador(Usuario usuario);
}
