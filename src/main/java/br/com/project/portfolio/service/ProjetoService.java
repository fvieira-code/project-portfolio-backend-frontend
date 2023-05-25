package main.java.br.com.project.portfolio.service;

import br.com.project.portfolio.exception.ResourceNotFoundException;
import br.com.project.portfolio.model.Pessoa;
import br.com.project.portfolio.model.Projeto;
import br.com.project.portfolio.model.constants.Status;
import br.com.project.portfolio.repository.PessoaRepository;
import br.com.project.portfolio.repository.ProjetoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProjetoService {
	
	private static final org.slf4j.Logger log  = org.slf4j.LoggerFactory.getLogger(ProjetoService.class);

    @Autowired
    ProjetoRepository projetoRepository;

    @Autowired
    PessoaRepository pessoaRepository;

    public ResponseEntity<List<Projeto>>  findAllProjeto(String nome){
         log.info("Find all Projeto ");

        try {
            List<Projeto> Projetos = new ArrayList<Projeto>();

            if (nome == null)
                projetoRepository.findAll().forEach(Projetos::add);
            else
                projetoRepository.findByNome(nome).forEach(Projetos::add);

            if (Projetos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(Projetos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Projeto> findProjetoById(long id) {
         log.info("Find Projeto: Id: [{}] ", id);

        Optional<Projeto> ProjetoData = projetoRepository.findById(id);

        if (ProjetoData.isPresent()) {
            return new ResponseEntity<>(ProjetoData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Projeto> saveProjeto(Projeto projeto) {
         log.info("Save Projeto: Id: [{}] - nome: [{}]", projeto.getId(), projeto.getNome());
        try {
            Projeto newProjeto = projetoRepository
                    .save(new Projeto(projeto.getNome(), projeto.getDataInicio(), projeto.getDataPrevisaoFim(),
                            projeto.getDataFim(), projeto.getDescricao(), projeto.getStatus(), projeto.getOrcamento(),
                            projeto.getRisco()));

            return new ResponseEntity<>(newProjeto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Projeto> updateProjeto(long id, Projeto projeto) {
         log.info("Update Projeto: Id: [{}] - nome: [{}]", projeto.getId(), projeto.getNome());

        Optional<Projeto> projetoData = projetoRepository.findById(id);

        if (projetoData.isPresent()) {
            Projeto newProjeto = projetoData.get();
            newProjeto.setNome(projeto.getNome());
            newProjeto.setDataInicio(projeto.getDataInicio());
            newProjeto.setDataPrevisaoFim(projeto.getDataPrevisaoFim());
            newProjeto.setDataFim(projeto.getDataFim());
            newProjeto.setDescricao(projeto.getDescricao());
            newProjeto.setStatus(projeto.getStatus());
            newProjeto.setOrcamento(projeto.getOrcamento());
            newProjeto.setRisco(projeto.getRisco());

            return new ResponseEntity<>(projetoRepository.save(newProjeto), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<HttpStatus> deleteProjeto(long id) {
         log.info("Delete Projeto: Id: [{}]", id);

        try {
            projetoRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<HttpStatus> deleteAllProjetos() {
         log.info("Delete all Projeto");

        try {
            projetoRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Projeto>> findByStatus(Status status) {
         log.info("Get Projeto by funcionario");

        try {
            List<Projeto> projetos = projetoRepository.findByStatus(status);

            if (projetos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(projetos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Pessoa>> getAllPessoasByProjetoId(Long idProjeto) {
        log.info("Get All Pessoas By ProjetoId ");
        if (!projetoRepository.existsById(idProjeto)) {
            throw new ResourceNotFoundException("Not found Projeto with id = " + idProjeto);
        }
        List<Pessoa> pessoass = pessoaRepository.findPessoasByProjetosId(idProjeto);
        return new ResponseEntity<>(pessoass, HttpStatus.OK);
    }







}
