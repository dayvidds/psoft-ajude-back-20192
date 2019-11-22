package com.psoft.ajude.servicos;

import com.psoft.ajude.daos.RepositorioCampanha;
import com.psoft.ajude.daos.RepositorioDoacao;
import com.psoft.ajude.daos.RepositorioUsuario;
import com.psoft.ajude.dtos.DTOCampanha;
import com.psoft.ajude.dtos.DTODoacao;
import com.psoft.ajude.dtos.DTOPesquisa;
import com.psoft.ajude.dtos.DTOUsuario;
import com.psoft.ajude.entidades.Campanha;
import com.psoft.ajude.entidades.Doacao;
import com.psoft.ajude.entidades.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ServicoCampanha {

    @Autowired
    private RepositorioCampanha<Campanha, Integer> campanhaDAO;
    @Autowired
    private RepositorioUsuario<Usuario, String> usuariosDAO;
    @Autowired
    private RepositorioDoacao<Doacao, Integer> doacoesDAO;

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

    public Campanha retornaCampanha(String urlCampanha) throws ServletException {
        Optional<Campanha> optionalCampanha = campanhaDAO.findById(urlCampanha);

        if (!optionalCampanha.isPresent()) {
            throw new ServletException("Campanha não cadastrada");
        }
        return optionalCampanha.get();
    }

    public Set<Usuario> toggleLike(String urlCampanha, Usuario usuario) {
        Campanha campanha = campanhaDAO.findById(urlCampanha).get();
        campanha.toggleLike(usuario);
        return campanhaDAO.save(campanha).getLikesUsuarios();
    }

    public List<Doacao> adicionaDoacao(String urlCampanha, DTODoacao dtoDoacao, Usuario usuario) {
        Campanha campanha = campanhaDAO.findById(urlCampanha).get();
        Doacao doacao = new Doacao(dtoDoacao, usuario);
        doacoesDAO.save(doacao);
        campanha.adicionarDoacao(doacao);
        return campanhaDAO.save(campanha).getDoacoes();
    }
}
