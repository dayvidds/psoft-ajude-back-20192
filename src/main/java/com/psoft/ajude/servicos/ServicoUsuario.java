package com.psoft.ajude.servicos;

import com.psoft.ajude.daos.RepositorioCampanha;
import com.psoft.ajude.daos.RepositorioDoacao;
import com.psoft.ajude.daos.RepositorioUsuario;
import com.psoft.ajude.dtos.DTOUsuario;
import com.psoft.ajude.dtos.DTOUsuarioPerfil;
import com.psoft.ajude.entidades.Campanha;
import com.psoft.ajude.entidades.Doacao;
import com.psoft.ajude.entidades.Usuario;
import com.psoft.ajude.excecoes.BadRequestException;
import com.psoft.ajude.excecoes.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicoUsuario {

    @Autowired
    private RepositorioUsuario<Usuario, String> usuariosDAO;

    @Autowired
    private RepositorioCampanha<Campanha, String> campanhasDAO;

    @Autowired
    private RepositorioDoacao<Doacao, Integer> doacaoDAO;

    @Autowired
    private ServicoEmail emailService;

    public ServicoUsuario(RepositorioUsuario<Usuario, String> usuariosDAO) {
        super();
        this.usuariosDAO = usuariosDAO;
    }

    public DTOUsuario cadastraUsuario(Usuario usuario) {
        if (usuarioCadastrado(usuario.getEmail())) {
            throw new BadRequestException("Usuario já cadastrado");
        }

        Usuario usuarioSalvo = usuariosDAO.save(usuario);

        emailService.mandarEmail(usuarioSalvo.getEmail(), emailService.getCadastroEmailCorpo(), emailService.getCadastroEmailSubject());

        return new DTOUsuario(usuario);
    }

    public Optional<Usuario> getUsuario(String email) {
        if (!usuarioCadastrado(email)) {
            throw new NotFoundException("Usuario nao cadastrado");
        }
        return this.usuariosDAO.findById(email);
    }

    private boolean usuarioCadastrado(String email) {
        return this.usuariosDAO.findById(email).isPresent();
    }

    public DTOUsuarioPerfil pegaUsuario(String email) {
        if (!this.usuarioCadastrado(email)) {
            throw new BadRequestException("Usuario nao cadastrado");
        }

        Usuario usuario = this.usuariosDAO.findById(email).get();
        List<Doacao> campanhasDoacao = this.doacaoDAO.findByDoador(usuario);
        List<Campanha> campanhasDono = this.campanhasDAO.findByUsuarioDono(usuario);

        return new DTOUsuarioPerfil(usuario, campanhasDoacao, campanhasDono);
    }
}
