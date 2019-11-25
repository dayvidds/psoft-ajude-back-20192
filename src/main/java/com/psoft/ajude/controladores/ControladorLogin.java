package com.psoft.ajude.controladores;

import com.psoft.ajude.entidades.Usuario;
import com.psoft.ajude.servicos.ServicoJWT;
import com.psoft.ajude.servicos.ServicoUsuario;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import java.util.Optional;

@Api(value="Login API")
@RestController
@RequestMapping("/autorizacao")
public class ControladorLogin {

    @Autowired
    private ServicoUsuario servicoUsuario;

    @Autowired
    private ServicoJWT servicoJwt;


    @ApiOperation(value="Realiza o login do usuario no sistema")
    @PostMapping("/login")
    public LoginResponse authenticate(@ApiParam(value="Usuario") @RequestBody Usuario usuario) throws ServletException {

        Optional<Usuario> authUsuario = servicoUsuario.getUsuario(usuario.getEmail());

        if (!authUsuario.isPresent()) {
            throw new ServletException("Usuario nao encontrado!");
        }

        verificaSenha(usuario, authUsuario);

        String token = servicoJwt.geraToken(authUsuario.get().getEmail());

        return new LoginResponse(token);

    }

    private void verificaSenha(Usuario usuario, Optional<Usuario> authUsuario) throws ServletException {
        if (!authUsuario.get().getSenha().equals(usuario.getSenha())) {
            throw new ServletException("Senha invalida!");
        }
    }

    private class LoginResponse {
        public String token;

        public LoginResponse(String token) {
            this.token = token;
        }
    }

}
