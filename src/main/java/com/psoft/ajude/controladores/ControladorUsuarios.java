package com.psoft.ajude.controladores;

import com.psoft.ajude.dtos.DTOUsuario;
import com.psoft.ajude.dtos.DTOUsuarioPerfil;
import com.psoft.ajude.entidades.Usuario;
import com.psoft.ajude.servicos.ServicoUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class ControladorUsuarios {

    @Autowired
    private ServicoUsuario servicoUsuario;

    @PostMapping()
    public ResponseEntity<DTOUsuario> cadastraUsuario(@RequestBody Usuario usuario) {
        return new ResponseEntity<>(servicoUsuario.cadastraUsuario(usuario), HttpStatus.CREATED);
    }
    @GetMapping("/{userName}")
    public ResponseEntity<DTOUsuarioPerfil> pegaUsuario(@PathVariable String userName){
        return new ResponseEntity<>(servicoUsuario.pegaUsuario(userName), HttpStatus.OK);
    }
}
