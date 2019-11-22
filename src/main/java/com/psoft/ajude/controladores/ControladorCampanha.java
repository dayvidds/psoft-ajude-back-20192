package com.psoft.ajude.controladores;

import com.psoft.ajude.dtos.DTOCampanha;
import com.psoft.ajude.dtos.DTOPesquisa;
import com.psoft.ajude.entidades.Campanha;
import com.psoft.ajude.entidades.MetodoComparacaoCampanha;
import com.psoft.ajude.entidades.Usuario;
import com.psoft.ajude.servicos.ServicoCampanha;
import com.psoft.ajude.servicos.ServicoJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/campanha")
public class ControladorCampanha {

    @Autowired
    private ServicoCampanha servicoCampanha;
    @Autowired
    private ServicoJWT servicoJWT;

    @PostMapping()
    public ResponseEntity<DTOCampanha> cadastraCampanha(@RequestBody DTOCampanha campanha, @RequestHeader("Authorization") String token) throws ServletException {
        return new ResponseEntity<>(servicoCampanha.cadastrarCampanha(campanha, servicoJWT.getSujeitoDoToken(token)), HttpStatus.CREATED);
    }

    @PostMapping("/pesquisa")
    public ResponseEntity<List<Campanha>> pesquisaCampanha(@RequestBody DTOPesquisa dtoPesquisa, @RequestHeader("Authorization") String token) throws ServletException {
        return new ResponseEntity<>(servicoCampanha.pesquisarCampanha(dtoPesquisa), HttpStatus.OK);
    }

    @GetMapping("/{urlCampanha}")
    public ResponseEntity<Campanha> retornaCampanha(@PathVariable String urlCampanha, @RequestHeader("Authorization") String token) throws ServletException {
        return new ResponseEntity<>(servicoCampanha.retornaCampanha(urlCampanha), HttpStatus.OK);
    }

    @PostMapping("/{urlCampanha}/likes")
    public ResponseEntity<Set<Usuario>> toggleLike(@PathVariable String urlCampanha, @RequestHeader("Authorization") String token) throws ServletException {
        return new ResponseEntity<>(servicoCampanha.toggleLike(urlCampanha, servicoJWT.getUsuario(token)), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Campanha>> retornaCampanhas(@RequestHeader("Metodo") MetodoComparacaoCampanha metodoComparacaoCampanha) {
        return new ResponseEntity<>(servicoCampanha.retornaCampanhas(metodoComparacaoCampanha), HttpStatus.OK);
    }

}
