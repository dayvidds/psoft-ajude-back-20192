package com.psoft.ajude.servicos;

import com.psoft.ajude.daos.RepositorioUsuario;
import com.psoft.ajude.dtos.DTOUsuario;
import com.psoft.ajude.entidades.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServicoUsuario {

    @Autowired
    private RepositorioUsuario<Usuario, String> usuariosDAO;
    @Autowired
    private ServicoEmail emailService;

    public ServicoUsuario(RepositorioUsuario<Usuario, String> usuariosDAO) {
        super();
        this.usuariosDAO = usuariosDAO;
    }

    public DTOUsuario cadastraUsuario(Usuario usuario) {
        Usuario usuarioSalvo = usuariosDAO.save(usuario);

        emailService.mandarEmail(usuarioSalvo.getEmail(), emailService.getCadastroEmailCorpo(), emailService.getCadastroEmailSubject());

        return new DTOUsuario(usuario);
    }


    public Optional<Usuario> getUsuario(String email) {
        return this.usuariosDAO.findByEmail(email);
    }
}
