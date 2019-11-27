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

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Campanha cadastrada com sucesso"),
            @ApiResponse(code = 400, message = "Erro no cadastro"),
            @ApiResponse(code = 401, message = "Erro na autenticacao do usuario") })
    @ApiOperation(value="Cadastra campanha", notes= "Cadastra uma campanha no sistema a partir de nome, descricao, deadline, meta e usuario dono da campanha")
    @PostMapping()
    public ResponseEntity<DTOCampanha> cadastraCampanha(@ApiParam(value="DTO da campanha") @RequestBody DTOCampanha campanha, @ApiParam(value="Token do usuario")@RequestHeader("Authorization") String token) throws ServletException {
        return new ResponseEntity<>(servicoCampanha.cadastrarCampanha(campanha, servicoJWT.getSujeitoDoToken(token)), HttpStatus.CREATED);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "busca realizada com sucesso"),
            @ApiResponse(code = 400, message = "Erro na busca"),
            @ApiResponse(code = 401, message = "Erro na autenticacao do usuario") })
    @ApiOperation(value="Retorna campanhas", notes= "Pesquisa campanhas no sistema a partir de uma substring")
    @PostMapping("/pesquisa")
    public ResponseEntity<List<Campanha>> pesquisaCampanha(@ApiParam(value="DTO da pesquisa")@RequestBody DTOPesquisa dtoPesquisa, @ApiParam(value="Token do usuario") @RequestHeader("Authorization") String token) throws ServletException {
        this.servicoJWT.getSujeitoDoToken(token);
        return new ResponseEntity<>(servicoCampanha.pesquisarCampanha(dtoPesquisa), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorno realizado com sucesso"),
            @ApiResponse(code = 400, message = "Erro na busca"),
            @ApiResponse(code = 401, message = "Erro na autenticacao do usuario"),
            @ApiResponse(code = 404, message = "Campanha nao encontrada")})
    @ApiOperation(value="Retorna uma campanha", notes = "Retorna uma campanha a partir de sua url")
    @GetMapping("/{urlCampanha}")
    public ResponseEntity<Campanha> retornaCampanha(@ApiParam(value="URL da campanha") @PathVariable String urlCampanha, @ApiParam(value="Token do usuario") @RequestHeader("Authorization") String token) throws ServletException {
        this.servicoJWT.getSujeitoDoToken(token);
        return new ResponseEntity<>(servicoCampanha.retornaCampanha(urlCampanha), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Acao realizada com sucesso"),
            @ApiResponse(code = 401, message = "Erro na autenticacao do usuario"),
            @ApiResponse(code = 404, message = "Campanha ou usuario nao encontrado")})
    @ApiOperation(value="Altera like", notes = "Altera a quantidade de likes de uma campanha, caso o usuario ainda nao tenha curtido entao sera adicionado, caso ja tenha curtido entao sera removido")
    @PostMapping("/{urlCampanha}/likes")
    public ResponseEntity<Set<Usuario>> toggleLike(@ApiParam(value="URL da campanha") @PathVariable String urlCampanha, @ApiParam(value="Token do usuario")@RequestHeader("Authorization") String token) throws ServletException {
        return new ResponseEntity<>(servicoCampanha.toggleLike(urlCampanha, servicoJWT.getUsuario(token)), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Comentario adicionado com sucesso"),
            @ApiResponse(code = 401, message = "Erro na autenticacao do usuario"),
            @ApiResponse(code = 404, message = "Campanha ou usuario nao encontrado")})
    @ApiOperation(value="Adiciona comentario", notes = "Adiciona um comentario realizado por um usuario em uma determinada campanha")
    @PostMapping("/{urlCampanha}/comentario")
    public ResponseEntity<List<Comentario>> adicionaComentario(@ApiParam(value="DTO do comentario") @RequestBody DTOComentario dtoComentario, @ApiParam(value="URL da campanha") @PathVariable String urlCampanha, @ApiParam(value="Token do usuario") @RequestHeader("Authorization") String token) throws ServletException {
        return new ResponseEntity<>(servicoCampanha.adicionaComentario(dtoComentario, urlCampanha, servicoJWT.getUsuario(token)), HttpStatus.CREATED);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Comentario removido com sucesso"),
            @ApiResponse(code = 401, message = "Erro na autenticacao do usuario"),
            @ApiResponse(code = 404, message = "Campanha, usuario ou comentario nao encontrado")})
    @ApiOperation(value="Remove comentario", notes = "Remove um comentario realizado por um usuario em uma determinada campanha")
    @DeleteMapping("/{urlCampanha}/comentario")
    public ResponseEntity<List<Comentario>> removeComentario(@ApiParam(value="DTO do comentario") @RequestBody DTOComentarioDelete dtoComentario, @ApiParam(value="URL da campanha") @PathVariable String urlCampanha, @ApiParam(value="Token do usuario") @RequestHeader("Authorization") String token) throws ServletException {
        return new ResponseEntity<>(servicoCampanha.removeComentario(dtoComentario.getId(), urlCampanha, servicoJWT.getUsuario(token)), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Campanha realizada com sucesso"),
            @ApiResponse(code = 401, message = "Erro na autenticacao do usuario"),
            @ApiResponse(code = 404, message = "Campanha, usuario ou comentario nao encontrado")})
    @ApiOperation(value="Realiza doação", notes = "Realiza uma doacao de um usuario a uma campanha ativa no sistema")
    @PostMapping("/{urlCampanha}/doacoes")
    public ResponseEntity<List<Doacao>> adicionaDoacao(@ApiParam(value="URL da campanha") @PathVariable String urlCampanha, @ApiParam(value="DTO da dacao") @RequestBody DTODoacao dtoDoacao, @ApiParam(value="Token do usuario") @RequestHeader("Authorization") String token) throws ServletException {
        return new ResponseEntity<>(servicoCampanha.adicionaDoacao(urlCampanha, dtoDoacao, servicoJWT.getUsuario(token)), HttpStatus.CREATED);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Campanhas retornadas com sucesso")})
    @ApiOperation(value="Retorna todas as campanhas", notes = "Retorna todas as campanhas do sistema de acordo com o metodo de busca desejado")
    @GetMapping()
    public ResponseEntity<List<Campanha>> retornaCampanhas(@ApiParam(value="Metodo de comparacao da campanha") @RequestHeader("Metodo") MetodoComparacaoCampanha metodoComparacaoCampanha) {
        return new ResponseEntity<>(servicoCampanha.retornaCampanhas(metodoComparacaoCampanha), HttpStatus.OK);
    }
}
