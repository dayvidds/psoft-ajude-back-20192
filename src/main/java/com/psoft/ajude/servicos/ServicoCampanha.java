package com.psoft.ajude.servicos;

import com.psoft.ajude.daos.RepositorioCampanha;
import com.psoft.ajude.daos.RepositorioUsuario;
import com.psoft.ajude.dtos.DTOCampanha;
import com.psoft.ajude.dtos.DTOPesquisa;
import com.psoft.ajude.dtos.DTOUsuario;
import com.psoft.ajude.entidades.Campanha;
import com.psoft.ajude.entidades.StatusCampanha;
import com.psoft.ajude.entidades.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ServicoCampanha {

    @Autowired
    private RepositorioCampanha<Campanha, Integer> campanhaDAO;
    @Autowired
    private RepositorioUsuario<Usuario, String> usuariosDAO;

    public DTOCampanha cadastrarCampanha(DTOCampanha dtoCampanha, String emailDono) {
        Usuario usuario = this.usuariosDAO.findByEmail(emailDono).get();
        Campanha campanha = new Campanha(
                dtoCampanha.getNomeCurto(),
                dtoCampanha.getDescricao(),
                dtoCampanha.getDeadline(),
                dtoCampanha.getMeta(),
                usuario
        );
        this.campanhaDAO.save(campanha);

        return new DTOCampanha(campanha, new DTOUsuario(usuario));
    }

    public List<Campanha> pesquisarCampanha(DTOPesquisa dtoPesquisa) {
        return campanhaDAO.findByNomeCurtoIgnoreCaseContaining(dtoPesquisa.getParametro()).stream()
                .filter(campanha -> dtoPesquisa.getStatusProcurados().contains(campanha.getStatusCampanha()))
                .collect(Collectors.toList());
    }
}