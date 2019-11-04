package com.psoft.ajude.servicos;

import com.psoft.ajude.daos.RespositorioUsuario;
import com.psoft.ajude.entidades.Usuario;
import org.springframework.stereotype.Service;

@Service
public class ServicoUsuario {

    private RespositorioUsuario<Usuario, String> usuariosDAO;

    public ServicoUsuario(RespositorioUsuario<Usuario, String> usuariosDAO){
        super();
        this.usuariosDAO = usuariosDAO;
    }

    public Usuario cadastraUsuario(Usuario usuario) {
        return usuariosDAO.save(usuario);
    }
}
