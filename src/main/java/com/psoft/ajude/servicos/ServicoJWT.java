package com.psoft.ajude.servicos;

import java.util.Date;
import java.util.Optional;

import com.psoft.ajude.entidades.Usuario;

import com.psoft.ajude.filtros.TokenFilter;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import java.util.Optional;

@Service
public class ServicoJWT {
    private ServicoUsuario servicoUsuario;
    private final String TOKEN_KEY = "bdNaoehbomtaokay?";

    public ServicoJWT(ServicoUsuario servicoUsuario){
        super();
        this.servicoUsuario = servicoUsuario;
    }

    public boolean usuarioCadastrado (String authorizationHeader, String email) throws ServletException {
        String subject =  getSujeitoDoToken(authorizationHeader);

        Optional<Usuario> optionalUsuario = servicoUsuario.getUsuario(subject);
        return optionalUsuario.isPresent() && optionalUsuario.get().getEmail().equals(email);
    }

    private String getSujeitoDoToken(String authorizationHeader) throws ServletException {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new ServletException("Token inexistente ou mal formatado!");
        }

        String token = authorizationHeader.substring(TokenFilter.TOKEN_INDEX);

        String subject = null;
        try {
            subject = Jwts.parser().setSigningKey("bdNaoehbomtaokay?").parseClaimsJws(token).getBody().getSubject();
        } catch (SignatureException e) {
            throw new ServletException("Token invalido ou expirado!");
        }
        return subject;
    }

    public String geraToken(String email) {
        return Jwts.builder().setSubject(email)
                .signWith(SignatureAlgorithm.HS512, TOKEN_KEY)
                .setExpiration(new Date(System.currentTimeMillis() + 30 * 60 * 1000)).compact(); //30 min
    }
}