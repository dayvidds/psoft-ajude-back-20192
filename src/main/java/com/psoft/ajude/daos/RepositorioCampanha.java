package com.psoft.ajude.daos;

import com.psoft.ajude.entidades.Campanha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface RepositorioCampanha<T, ID extends Serializable> extends JpaRepository<Campanha, Integer> {
}
