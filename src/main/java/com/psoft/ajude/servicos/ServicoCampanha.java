package com.psoft.ajude.servicos;

import com.psoft.ajude.comparadores.CampanhaDeadlineComparator;
import com.psoft.ajude.comparadores.CampanhaMetaComparator;
import com.psoft.ajude.daos.RepositorioCampanha;
import com.psoft.ajude.daos.RepositorioComentario;
import com.psoft.ajude.daos.RepositorioDoacao;
import com.psoft.ajude.daos.RepositorioUsuario;
import com.psoft.ajude.dtos.*;
import com.psoft.ajude.entidades.*;
import com.psoft.ajude.excecoes.BadRequestException;
import com.psoft.ajude.excecoes.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ServicoCampanha {

    @Autowired
    private RepositorioCampanha<Campanha, Integer> campanhaDAO;
    @Autowired
    private RepositorioUsuario<Usuario, String> usuariosDAO;
    @Autowired
    private RepositorioDoacao<Doacao, Integer> doacoesDAO;
    @Autowired
    private RepositorioComentario<Comentario, Integer> comentarioDAO;
    private Map<MetodoComparacaoCampanha, Comparator<Campanha>> metodosComparacao;

    public ServicoCampanha() {
        this.metodosComparacao = new HashMap<>();

        metodosComparacao.put(MetodoComparacaoCampanha.META, new CampanhaMetaComparator());
        metodosComparacao.put(MetodoComparacaoCampanha.DEADLINE, new CampanhaDeadlineComparator());
        metodosComparacao.put(MetodoComparacaoCampanha.LIKES, new CampanhaMetaComparator());
    }

    public DTOCampanha cadastrarCampanha(DTOCampanha dtoCampanha, String emailDono) {
        if (dtoCampanha.getMeta() < 0) {
            throw new BadRequestException("Meta nao pode ser negativa");
        }

        Date dataAtual = new Date();
        if (dataAtual.after(dtoCampanha.getDeadline())) {
            throw new BadRequestException("Deadline invalida, nao pode ser antes da data atual");
        }

        Usuario usuario = this.usuariosDAO.findById(emailDono).get();
        Campanha campanha = new Campanha(
                dtoCampanha.getNomeCurto(),
                dtoCampanha.getDescricao(),
                dtoCampanha.getDeadline(),
                dtoCampanha.getMeta(),
                usuario
        );

        if (campanhaDAO.findAll().contains(campanha)) {
            throw new BadRequestException("Campanha já existente, escolha outro nome");
        }

        this.campanhaDAO.save(campanha);

        return new DTOCampanha(campanha, new DTOUsuario(usuario));
    }

    public List<Campanha> pesquisarCampanha(DTOPesquisa dtoPesquisa) {
        return campanhaDAO.findByNomeCurtoIgnoreCaseContaining(dtoPesquisa.getParametro()).stream()
                .filter(campanha -> dtoPesquisa.getStatusProcurados().contains(campanha.getStatusCampanha()))
                .collect(Collectors.toList());
    }

    public Campanha retornaCampanha(String urlCampanha) {
        Optional<Campanha> optionalCampanha = campanhaDAO.findById(urlCampanha);

        if (!optionalCampanha.isPresent()) {
            throw new NotFoundException("Campanha nao cadastrada");
        }
        return optionalCampanha.get();
    }

    public List<Comentario> adicionaComentario(DTOComentario dtoComentario, String urlCampanha, Usuario usuario) {
        verificaExistenciaURL(urlCampanha);
        verificaExistenciaUsuario(usuario);

        Comentario comentarioPai = dtoComentario.getIdComentarioPai() == 0 ? null : comentarioDAO.findById(dtoComentario.getIdComentarioPai()).get();

        Campanha campanha = campanhaDAO.findById(urlCampanha).get();
        Comentario comentario = new Comentario(campanha, dtoComentario.getConteudo(), usuario);

        comentarioDAO.save(comentario);

        if (comentarioPai != null) {
            comentarioPai.adicionarResposta(comentario);
            comentarioDAO.save(comentarioPai);
        } else {
            campanha.adicionarComentario(comentario);
        }

        return campanhaDAO.save(campanha).getComentarios();
    }

    public Set<Usuario> toggleLike(String urlCampanha, Usuario usuario) {
        verificaExistenciaURL(urlCampanha);
        verificaExistenciaUsuario(usuario);

        Campanha campanha = campanhaDAO.findById(urlCampanha).get();
        campanha.toggleLike(usuario);
        return campanhaDAO.save(campanha).getLikesUsuarios();
    }

    public List<Campanha> retornaCampanhas(MetodoComparacaoCampanha metodoComparacaoCampanha) {
        return campanhaDAO.findAll().stream()
                .filter(c -> c.isAtiva())
                .sorted(this.metodosComparacao.get(metodoComparacaoCampanha))
                .collect(Collectors.toList());
    }

    public List<Doacao> adicionaDoacao(String urlCampanha, DTODoacao dtoDoacao, Usuario usuario) {
        verificaExistenciaURL(urlCampanha);
        verificaExistenciaUsuario(usuario);

        Campanha campanha = campanhaDAO.findById(urlCampanha).get();
        Doacao doacao = new Doacao(campanha, dtoDoacao, usuario);
        doacoesDAO.save(doacao);
        campanha.adicionarDoacao(doacao);
        return campanhaDAO.save(campanha).getDoacoes();
    }

    public List<Comentario> removeComentario(Integer id, String urlCampanha, Usuario usuario) {
        verificaExistenciaURL(urlCampanha);
        verificaExistenciaUsuario(usuario);
        verificaExistenciaComentario(id);

        if (!comentarioDAO.findById(id).get().getDonoComentario().equals(usuario)) {
            throw new BadRequestException("Usuario nao e o dono do comentario");
        }

        comentarioDAO.deleteById(id);
        return campanhaDAO.findById(urlCampanha).get().getComentarios();
    }

    private boolean verificaExistenciaComentario(Integer id) {
        if (!this.comentarioDAO.findById(id).isPresent()) {
            throw new NotFoundException("Comentario nao cadastrado");
        } else return true;
    }

    private boolean verificaExistenciaUsuario(Usuario usuario) {
        this.usuariosDAO.findAll().contains(usuario);
        if (!this.usuariosDAO.findAll().contains(usuario)) {
            throw new NotFoundException("Usuario nao cadastrado");
        } else return true;
    }

    private boolean verificaExistenciaURL(String urlCampanha) {
        Optional<Campanha> optionalCampanha = campanhaDAO.findById(urlCampanha);
        if (!optionalCampanha.isPresent()) {
            throw new NotFoundException("Campanha nao cadastrada");
        } else return true;
    }
}
