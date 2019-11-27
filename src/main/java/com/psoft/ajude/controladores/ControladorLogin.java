package com.psoft.ajude.controladores;

import com.psoft.ajude.entidades.Usuario;
import com.psoft.ajude.excecoes.BadRequestException;
import com.psoft.ajude.excecoes.NotFoundException;
import com.psoft.ajude.servicos.ServicoJWT;
import com.psoft.ajude.servicos.ServicoUsuario;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Api(value = "Login API")
@RestController
@RequestMapping("/autorizacao")
public class ControladorLogin {

    @Autowired
    private ServicoUsuario servicoUsuario;

    @Autowired
    private ServicoJWT servicoJwt;


    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Autenticacao invalida"),
            @ApiResponse(code = 200, message = "Login realizado com sucesso"),
            @ApiResponse(code = 404, message = "Usuario nao esta cadastrado")})
    @ApiOperation(value = "Autentica usuario", notes = "Realiza o login do usuario no sistema e retorna o token de acesso")
    @PostMapping("/login")
    public LoginResponse authenticate(@ApiParam(value = "Usuario") @RequestBody Usuario usuario) {
        Optional<Usuario> authUsuario = servicoUsuario.getUsuario(usuario.getEmail());

        if (!authUsuario.isPresent()) {
            throw new NotFoundException("Usuario nao encontrado!");
        }

        verificaSenha(usuario, authUsuario);

        String token = servicoJwt.geraToken(authUsuario.get().getEmail());

        return new LoginResponse(token);

    }

    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Autenticacao invalida"),
            @ApiResponse(code = 404, message = "Usuario nao esta cadastrado")})
    private void verificaSenha(Usuario usuario, Optional<Usuario> authUsuario) {
        servicoUsuario.getUsuario(usuario.getEmail());
        if (!authUsuario.get().getSenha().equals(usuario.getSenha())) {
            throw new BadRequestException("Senha invalida!");
        }
    }

    private class LoginResponse {
        public String token;

        public LoginResponse(String token) {
            this.token = token;
        }
    }
}
