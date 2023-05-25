package main.java.br.com.project.portfolio.controller.Impl;

import main.br.com.project.portfolio.controller.ProjetoController;
import main.br.com.project.portfolio.model.Projeto;
import main.br.com.project.portfolio.model.constants.Status;
import main.br.com.project.portfolio.service.ProjetoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin(origins = "http:localhost:8081")
@RestController
@RequestMapping("/api/v1/project/portfolio")
public class ProjetoControllerImpl implements ProjetoController {
	
	private static final org.slf4j.Logger log  = org.slf4j.LoggerFactory.getLogger(ProjetoControllerImpl.class);

	@Autowired
	ProjetoService projetoService;

	@GetMapping(value = "/projetos")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Projeto>> getAllProjetos(@RequestParam(required = false) String nome) {
		log.info("Get All Projeto");
		return projetoService.findAllProjeto(nome);
	}

	@GetMapping("/projetos/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Projeto> getProjetoById(@PathVariable("id") long id) {
		log.info("Get Projeto by Id: [{}]", id);
		return projetoService.findProjetoById(id);
	}

	@PostMapping("/projetos")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Projeto> createProjeto(@RequestBody Projeto projeto) {
		log.info("Projeto Create [{}]", projeto);
		return projetoService.saveProjeto(projeto);
	}

	@PutMapping("/projetos/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Projeto> updateProjeto(@PathVariable("id") long id, @RequestBody Projeto projeto) {
		log.info("Update Projeto by Id: [{}]", id);
		return projetoService.updateProjeto(id, projeto);
	}

	@DeleteMapping("/projetos/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<HttpStatus> deleteProjeto(@PathVariable("id") long id) {
		log.info("Delete Projeto by Id: [{}]", id);
		return projetoService.deleteProjeto(id);
	}

	@DeleteMapping("/projetos")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<HttpStatus> deleteAllProjetos() {
		log.info("Delete all Projeto");
		return projetoService.deleteAllProjetos();
	}

	@GetMapping("/projetos/status")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Projeto>> findByStatus(Status status) {
		log.info("Get Projeto by funcionario");
		return projetoService.findByStatus(status);
	}

}
