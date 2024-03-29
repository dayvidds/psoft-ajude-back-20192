package com.psoft.ajude.daos;

import com.psoft.ajude.entidades.Campanha;
import com.psoft.ajude.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface RepositorioCampanha<T, ID extends Serializable> extends JpaRepository<Campanha, String> {
    List<Campanha> findByNomeCurtoIgnoreCaseContaining(String parametro);
    List<Campanha> findByUsuarioDono(Usuario usuario);
}
