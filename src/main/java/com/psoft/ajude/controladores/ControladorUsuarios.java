package com.psoft.ajude.controladores;

import com.psoft.ajude.dtos.DTOUsuario;
import com.psoft.ajude.dtos.DTOUsuarioPerfil;
import com.psoft.ajude.entidades.Usuario;
import com.psoft.ajude.servicos.ServicoUsuario;
import io.swagger.annotations.*;
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

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Usuario cadastrado com sucesso"),
            @ApiResponse(code = 400, message = "Usuario ja cadastrado")})
    @ApiOperation(value="Cadastra um usuario no sistema")
    @PostMapping()
    public ResponseEntity<DTOUsuario> cadastraUsuario(@ApiParam(value="Usuario") @RequestBody Usuario usuario) {
        return new ResponseEntity<>(servicoUsuario.cadastraUsuario(usuario), HttpStatus.CREATED);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Usuario retornado com sucesso"),
            @ApiResponse(code = 400, message = "Usuario nao cadastrado")})
    @ApiOperation(value="Retorna um usuario cadastrado no sistema")
    @GetMapping("/{email}")
    public ResponseEntity<DTOUsuarioPerfil> pegaUsuario(@ApiParam(value="Email do usuario") @PathVariable String email){
        return new ResponseEntity<>(servicoUsuario.pegaUsuario(email), HttpStatus.OK);
    }
}
