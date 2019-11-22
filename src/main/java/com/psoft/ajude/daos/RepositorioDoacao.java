package com.psoft.ajude.daos;

import com.psoft.ajude.entidades.Doacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface RepositorioDoacao<T, ID extends Serializable> extends JpaRepository<Doacao, Integer> {
}
