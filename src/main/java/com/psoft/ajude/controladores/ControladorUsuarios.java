package com.psoft.ajude.controladores;

import com.psoft.ajude.entidades.Usuario;
import com.psoft.ajude.servicos.ServicoUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControladorUsuarios {

    @Autowired
    private ServicoUsuario servicoUsuario;

    @PostMapping("/cadastro")
    public ResponseEntity<Usuario> cadastraUsuario(@RequestBody Usuario usuario) {
        return new ResponseEntity<Usuario>(servicoUsuario.cadastraUsuario(usuario), HttpStatus.CREATED);
    }
}
