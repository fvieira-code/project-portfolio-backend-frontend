package main.java.br.com.project.portfolio.controller;

import main.br.com.project.portfolio.model.Pessoa;
import main.br.com.project.portfolio.model.Projeto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface PessoaController {
    @Operation(description = "Get all pessoas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    public ResponseEntity<List<Pessoa>> getAllPessoas(@RequestParam(required = false) String nome);

    @Operation(description = "Get pessoa by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    public ResponseEntity<Pessoa> getPessoaById(@PathVariable("id") long id);

    @Operation(description = "Create pessoa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    public ResponseEntity<Pessoa> createPessoa(@RequestBody Pessoa pessoa);

    @Operation(description = "Update pessoa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    public ResponseEntity<Pessoa> updatePessoa(@PathVariable("id") long id, @RequestBody Pessoa pessoa);

    @Operation(description = "Delete pessoa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    public ResponseEntity<HttpStatus> deletePessoa(@PathVariable("id") long id);

    @Operation(description = "Delete all pessoa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    public ResponseEntity<HttpStatus> deleteAllPessoas();

    @Operation(description = "Get pessoa by funcionario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    public ResponseEntity<List<Pessoa>> findByFuncionario();

    @Operation(description = "Get all pessoa by projeto id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    public ResponseEntity<List<Pessoa>> getAllPessoasByProjetoId(@PathVariable(value = "idProjeto") Long idProjeto);

    @Operation(description = "Get all projeto by pessoa id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    public ResponseEntity<List<Projeto>> getAllProjetosByPessoaId(@PathVariable(value = "idPessoa") Long idPessoa);

    @Operation(description = "Create projeto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    public ResponseEntity<Pessoa> addPessoa(@PathVariable(value = "idProjeto") Long idProjeto, @RequestBody Pessoa pessoaRequest);

    @Operation(description = "Create pessoa from projeto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    public ResponseEntity<HttpStatus> deletePessoaFromProjeto(@PathVariable(value = "idProjeto") Long idProjeto, @PathVariable(value = "idPessoa") Long idPessoa);

}
