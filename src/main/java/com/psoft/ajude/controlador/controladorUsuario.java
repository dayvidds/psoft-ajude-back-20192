package com.psoft.ajude.controlador;

import com.psoft.ajude.entidades.Usuario;
import com.psoft.ajude.servicos.servicoUsuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

public class controladorUsuario {

    @PostMapping("api/v1/cadastro")
    public ResponseEntity<Usuario> cadastraUsuario(@RequestBody Usuario usuario){
        return new ResponseEntity<Usuario> (servicoUsuario.cadastraUsuario(usuario), HttpStatus.OK);
    }
}
