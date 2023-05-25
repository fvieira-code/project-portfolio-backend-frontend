package main.java.br.com.project.portfolio.repository;

import main.br.com.project.portfolio.model.Pessoa;
import main.br.com.project.portfolio.repository.ProjetoRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PessoaRepository extends ProjetoRepository<Pessoa, Long> {

  List<Pessoa> findByFuncionario(boolean funcionario);

  List<Pessoa> findByNome(String nome);

  List<Pessoa> findPessoasByProjetosId(Long idProjeto);

}
