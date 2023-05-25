package main.java.br.com.project.portfolio.controller.Impl;

import main.br.com.project.portfolio.controller.PessoaController;
import main.br.com.project.portfolio.model.Pessoa;
import main.br.com.project.portfolio.model.Projeto;
import main.br.com.project.portfolio.service.PessoaService;
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
public class PessoaControllerImpl implements PessoaController {
	
	private static final org.slf4j.Logger log  = org.slf4j.LoggerFactory.getLogger(PessoaControllerImpl.class);

	@Autowired
	ProjetoService projetoService;
	@Autowired
	PessoaService pessoaService;

	@GetMapping(value = "/pessoas")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Pessoa>> getAllPessoas(@RequestParam(required = false) String nome) {
		log.info("Get All Pessoa");
		return pessoaService.findAllPessoa(nome);
	}

	@GetMapping("/pessoas/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Pessoa> getPessoaById(@PathVariable("id") long id) {
		log.info("Get Pessoa by Id: [{}]", id);
		return pessoaService.findPessoaById(id);
	}

	@PostMapping("/pessoas")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Pessoa> createPessoa(@RequestBody Pessoa pessoa) {
		log.info("Pessoa Create [{}]", pessoa);
		return pessoaService.savePessoa(pessoa);
	}

	@PutMapping("/pessoas/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Pessoa> updatePessoa(@PathVariable("id") long id, @RequestBody Pessoa pessoa) {
		log.info("Update Pessoa by Id: [{}]", id);
		return pessoaService.updatePessoa(id, pessoa);
	}

	@DeleteMapping("/pessoas/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<HttpStatus> deletePessoa(@PathVariable("id") long id) {
		log.info("Delete Pessoa by Id: [{}]", id);
		return pessoaService.deletePessoa(id);
	}

	@DeleteMapping("/pessoas")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<HttpStatus> deleteAllPessoas() {
		log.info("Delete all pessoa");
		return pessoaService.deleteAllPessoas();
	}

	@GetMapping("/pessoas/funcionario")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Pessoa>> findByFuncionario() {
		log.info("Get pessoa by funcionario");
		return pessoaService.findByFuncionario();
	}

	@GetMapping("/projetos/{idProjeto}/pessoas")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Pessoa>> getAllPessoasByProjetoId(@PathVariable(value = "idProjeto") Long idProjeto) {
		log.info("Get pessoa by projeto");
		return projetoService.getAllPessoasByProjetoId(idProjeto);
	}

	@GetMapping("/pessoas/{idPessoa}/projetos")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Projeto>> getAllProjetosByPessoaId(@PathVariable(value = "idPessoa") Long idPessoa) {
		log.info("Get projeto by pessoa");
		return pessoaService.getAllProjetosByPessoaId(idPessoa);
	}

	@PostMapping("/projetos/{idProjeto}/pessoas")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Pessoa> addPessoa(@PathVariable(value = "idProjeto") Long idProjeto, @RequestBody Pessoa pessoaRequest) {
		log.info("Post projeto by pessoa");
		return pessoaService.addPessoa(idProjeto, pessoaRequest);
	}

	@DeleteMapping("/projetos/{idProjeto}/pessoas/{idPessoa}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<HttpStatus> deletePessoaFromProjeto(@PathVariable(value = "idProjeto") Long idProjeto,
															  @PathVariable(value = "idPessoa") Long idPessoa) {
		log.info("Delete pessoa from projeto");
		return pessoaService.deletePessoaFromProjeto( idProjeto, idPessoa);
	}

}
