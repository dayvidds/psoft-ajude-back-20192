package com.psoft.ajude.servicos;

import com.psoft.ajude.daos.RespositorioUsuario;
import com.psoft.ajude.entidades.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServicoUsuario {

    @Autowired
    private RespositorioUsuario<Usuario, String> usuariosDAO;
    @Autowired
    private ServicoEmail emailService;

    public ServicoUsuario(RespositorioUsuario<Usuario, String> usuariosDAO){
        super();
        this.usuariosDAO = usuariosDAO;
    }

    public Usuario cadastraUsuario(Usuario usuario) {
        Usuario usuarioSalvo = usuariosDAO.save(usuario);

        emailService.mandarEmail(usuarioSalvo.getEmail(), emailService.getCadastroEmailCorpo(), emailService.getCadastroEmailSubject());

        return usuarioSalvo;
    }


    public Optional<Usuario> getUsuario(String email) {
        return this.usuariosDAO.findByEmail(email);
    }
}
