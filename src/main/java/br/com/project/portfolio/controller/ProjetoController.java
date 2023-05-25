package main.java.br.com.project.portfolio.controller;

import main.br.com.project.portfolio.model.Projeto;
import main.br.com.project.portfolio.model.constants.Status;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ProjetoController {
    @Operation(description = "Get all Projetos")
     @ApiResponses(value = {
              @ApiResponse(responseCode = "400", description = "Bad Request")
     })
    public ResponseEntity<List<Projeto>> getAllProjetos(@RequestParam(required = false) String nome);

    @Operation(description = "Get Projeto by id")
     @ApiResponses(value = {
              @ApiResponse(responseCode = "400", description = "Bad Request")
     })
    public ResponseEntity<Projeto> getProjetoById(@PathVariable("id") long id);

    @Operation(description = "Create Projeto")
     @ApiResponses(value = {
              @ApiResponse(responseCode = "400", description = "Bad Request")
     })
    public ResponseEntity<Projeto> createProjeto(@RequestBody Projeto projeto);

    @Operation(description = "Update Projeto")
     @ApiResponses(value = {
              @ApiResponse(responseCode = "400", description = "Bad Request")
     })
    public ResponseEntity<Projeto> updateProjeto(@PathVariable("id") long id, @RequestBody Projeto projeto);

    @Operation(description = "Delete Projeto")
     @ApiResponses(value = {
              @ApiResponse(responseCode = "400", description = "Bad Request")
     })
    public ResponseEntity<HttpStatus> deleteProjeto(@PathVariable("id") long id);

    @Operation(description = "Delete all Projeto")
     @ApiResponses(value = {
              @ApiResponse(responseCode = "400", description = "Bad Request")
     })
    public ResponseEntity<HttpStatus> deleteAllProjetos();

    @Operation(description = "Get Projeto by funcionario")
     @ApiResponses(value = {
              @ApiResponse(responseCode = "400", description = "Bad Request")
     })
    public ResponseEntity<List<Projeto>> findByStatus(Status status);

}
