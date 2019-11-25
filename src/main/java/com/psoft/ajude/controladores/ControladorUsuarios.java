package com.psoft.ajude.controladores;

import com.psoft.ajude.dtos.DTOUsuario;
import com.psoft.ajude.dtos.DTOUsuarioPerfil;
import com.psoft.ajude.entidades.Usuario;
import com.psoft.ajude.servicos.ServicoUsuario;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value="Campanhas API")
@RestController
@RequestMapping("/usuario")
public class ControladorUsuarios {

    @Autowired
    private ServicoUsuario servicoUsuario;

    @ApiOperation(value="Cadastra um usuario no sistema")
    @PostMapping()
    public ResponseEntity<DTOUsuario> cadastraUsuario(@ApiParam(value="Usuario") @RequestBody Usuario usuario) {
        return new ResponseEntity<>(servicoUsuario.cadastraUsuario(usuario), HttpStatus.CREATED);
    }

    @ApiOperation(value="Retorna um usuario cadastrado no sistema")
    @GetMapping("/{email}")
    public ResponseEntity<DTOUsuarioPerfil> pegaUsuario(@ApiParam(value="Email do usuario") @PathVariable String email){
        return new ResponseEntity<>(servicoUsuario.pegaUsuario(email), HttpStatus.OK);
    }
}
