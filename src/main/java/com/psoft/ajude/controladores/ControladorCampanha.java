package com.psoft.ajude.controladores;

import com.psoft.ajude.dtos.*;
import com.psoft.ajude.entidades.*;
import com.psoft.ajude.servicos.ServicoCampanha;
import com.psoft.ajude.servicos.ServicoJWT;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import java.util.List;
import java.util.Set;

@Api(value="Campanhas API")
@RestController
@RequestMapping("/campanha")
public class ControladorCampanha {

    @Autowired
    private ServicoCampanha servicoCampanha;
    @Autowired
    private ServicoJWT servicoJWT;

    @ApiOperation(value="Cadastra uma campanha no sistema")
    @PostMapping()
    public ResponseEntity<DTOCampanha> cadastraCampanha(@ApiParam(value="DTO da campanha") @RequestBody DTOCampanha campanha, @ApiParam(value="Token do usuario")@RequestHeader("Authorization") String token) throws ServletException {
        return new ResponseEntity<>(servicoCampanha.cadastrarCampanha(campanha, servicoJWT.getSujeitoDoToken(token)), HttpStatus.CREATED);
    }

    @ApiOperation(value="Pesquisa campanhas no sistema")
    @PostMapping("/pesquisa")
    public ResponseEntity<List<Campanha>> pesquisaCampanha(@ApiParam(value="DTO da pesquisa")@RequestBody DTOPesquisa dtoPesquisa, @ApiParam(value="Token do usuario") @RequestHeader("Authorization") String token) throws ServletException {
        return new ResponseEntity<>(servicoCampanha.pesquisarCampanha(dtoPesquisa), HttpStatus.OK);
    }

    @ApiOperation(value="Pesquisa uma campanha no sistema")
    @GetMapping("/{urlCampanha}")
    public ResponseEntity<Campanha> retornaCampanha(@ApiParam(value="URL da campanha") @PathVariable String urlCampanha, @ApiParam(value="Token do usuario") @RequestHeader("Authorization") String token) throws ServletException {
        return new ResponseEntity<>(servicoCampanha.retornaCampanha(urlCampanha), HttpStatus.OK);
    }

    @ApiOperation(value="Altera quantidade de likes de uma campanha no sistema")
    @PostMapping("/{urlCampanha}/likes")
    public ResponseEntity<Set<Usuario>> toggleLike(@ApiParam(value="URL da campanha") @PathVariable String urlCampanha, @ApiParam(value="Token do usuario")@RequestHeader("Authorization") String token) throws ServletException {
        return new ResponseEntity<>(servicoCampanha.toggleLike(urlCampanha, servicoJWT.getUsuario(token)), HttpStatus.OK);
    }

    @ApiOperation(value="Adiciona um comentario em uma campanha do sistema")
    @PostMapping("/{urlCampanha}/comentario")
    public ResponseEntity<List<Comentario>> adicionaComentario(@ApiParam(value="DTO do comentario") @RequestBody DTOComentario dtoComentario, @ApiParam(value="URL da campanha") @PathVariable String urlCampanha, @ApiParam(value="Token do usuario") @RequestHeader("Authorization") String token) throws ServletException {
        return new ResponseEntity<>(servicoCampanha.adicionaComentario(dtoComentario, urlCampanha, servicoJWT.getUsuario(token)), HttpStatus.OK);
    }

    @ApiOperation(value="Remove um comentario em uma campanha do sistema")
    @DeleteMapping("/{urlCampanha}/comentario")
    public ResponseEntity<List<Comentario>> removeComentario(@ApiParam(value="DTO do comentario") @RequestBody DTOComentarioDelete dtoComentario, @ApiParam(value="URL da campanha") @PathVariable String urlCampanha, @ApiParam(value="Token do usuario") @RequestHeader("Authorization") String token) throws ServletException {
        return new ResponseEntity<>(servicoCampanha.removeComentario(dtoComentario.getId(), urlCampanha, servicoJWT.getUsuario(token)), HttpStatus.OK);
    }

    @ApiOperation(value="Realiza doação em uma campanha do sistema")
    @PostMapping("/{urlCampanha}/doacoes")
    public ResponseEntity<List<Doacao>> adicionaDoacao(@ApiParam(value="URL da campanha") @PathVariable String urlCampanha, @ApiParam(value="DTO da dacao") @RequestBody DTODoacao dtoDoacao, @ApiParam(value="Token do usuario") @RequestHeader("Authorization") String token) throws ServletException {
        return new ResponseEntity<>(servicoCampanha.adicionaDoacao(urlCampanha, dtoDoacao, servicoJWT.getUsuario(token)), HttpStatus.OK);
    }

    @ApiOperation(value="Retorna todas as campanhas")
    @GetMapping()
    public ResponseEntity<List<Campanha>> retornaCampanhas(@ApiParam(value="Metodo de comparacao da campanha") @RequestHeader("Metodo") MetodoComparacaoCampanha metodoComparacaoCampanha) {
        return new ResponseEntity<>(servicoCampanha.retornaCampanhas(metodoComparacaoCampanha), HttpStatus.OK);
    }
}
