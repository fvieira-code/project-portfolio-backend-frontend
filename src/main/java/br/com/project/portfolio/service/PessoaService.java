package main.java.br.com.project.portfolio.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.project.portfolio.exception.ResourceNotFoundException;
import br.com.project.portfolio.model.Pessoa;
import br.com.project.portfolio.model.Projeto;
import br.com.project.portfolio.repository.PessoaRepository;
import br.com.project.portfolio.repository.ProjetoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//@Slf4j
@Service
@RequiredArgsConstructor
public class PessoaService {
	
	private static final org.slf4j.Logger log  = org.slf4j.LoggerFactory.getLogger(PessoaService.class);

    @Autowired
    PessoaRepository pessoaRepository;

    @Autowired
    ProjetoRepository projetoRepository;

    public ResponseEntity<List<Pessoa>>  findAllPessoa(String nome){
         log.info("Find all pessoa ");

        try {
            List<Pessoa> pessoas = new ArrayList<Pessoa>();

            if (nome == null)
                pessoaRepository.findAll().forEach(pessoas::add);
            else
                pessoaRepository.findByNome(nome).forEach(pessoas::add);

            if (pessoas.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(pessoas, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Pessoa> findPessoaById(long id) {
         log.info("Find pessoa: Id: [{}] ", id);

        Optional<Pessoa> pessoaData = pessoaRepository.findById(id);

        if (pessoaData.isPresent()) {
            return new ResponseEntity<>(pessoaData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Pessoa> updatePessoa(long id, Pessoa pessoa) {
         log.info("Update pessoa: Id: [{}] - nome: [{}]",  pessoa.getId(), pessoa.getNome());

        Optional<Pessoa> pessoaData = pessoaRepository.findById(id);

        if (pessoaData.isPresent()) {
            Pessoa newPessoa = pessoaData.get();
            newPessoa.setNome(pessoa.getNome());
            newPessoa.setDataNascimento(pessoa.getDataNascimento());
            newPessoa.setCpf(pessoa.getCpf());
            newPessoa.setFuncionario(pessoa.getFuncionario());

            return new ResponseEntity<>(pessoaRepository.save(newPessoa), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<HttpStatus> deletePessoa(long id) {
         log.info("Delete pessoa: Id: [{}]", id);

        try {
            pessoaRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<HttpStatus> deleteAllPessoas() {
         log.info("Delete all pessoa");

        try {
            pessoaRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Pessoa>> findByFuncionario() {
         log.info("Get pessoa by funcionario");

        try {
            List<Pessoa> pessoas = pessoaRepository.findByFuncionario(true);

            if (pessoas.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(pessoas, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Projeto>> getAllProjetosByPessoaId(Long idPessoa) {
        log.info("Get all Projetos By Pessoa Id");
        if (!pessoaRepository.existsById(idPessoa)) {
            throw new ResourceNotFoundException("Not found Pessoa  with id = " + idPessoa);
        }

        List<Projeto> projetos = projetoRepository.findProjetosByPessoasId(idPessoa);
        return new ResponseEntity<>(projetos, HttpStatus.OK);
    }

    public ResponseEntity<Pessoa> savePessoa(Pessoa pessoa) {
        log.info("Save pessoa: Id: [{}] - nome: [{}]",  pessoa.getId(), pessoa.getNome());
        try {
            Pessoa newPessoa = pessoaRepository
                    .save(new Pessoa(pessoa.getNome(), pessoa.getDataNascimento(), pessoa.getCpf(), pessoa.getFuncionario()));
            return new ResponseEntity<>(newPessoa, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Pessoa> addPessoa(Long idProjeto, Pessoa pessoaRequest) {
        log.info("Add Pessoa");
        Pessoa pessoa = projetoRepository.findById(idProjeto).map(projeto -> {
            long idPessoa = pessoaRequest.getId();

             //Pessoa is existed
            if (idPessoa != 0L) {
                Pessoa newPessoa = pessoaRepository.findById(idPessoa)
                        .orElseThrow(() -> new ResourceNotFoundException("Not found Pessoa with id = " + idPessoa));
                projeto.addPessoa(newPessoa);
                projetoRepository.save(projeto);
                return newPessoa;
            }

             //Add and create new Pessoa
            projeto.addPessoa(pessoaRequest);
            return pessoaRepository.save(pessoaRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found Projeto with id = " + idProjeto));

        return new ResponseEntity<>(pessoa, HttpStatus.CREATED);
    }

    public ResponseEntity<HttpStatus> deletePessoaFromProjeto( Long idProjeto, Long idPessoa) {
        log.info("Delete Pessoa From Projeto");
        Projeto projeto = projetoRepository.findById(idProjeto)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Projeto with id = " + idProjeto));

        projeto.removePessoa(idPessoa);
        projetoRepository.save(projeto);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
