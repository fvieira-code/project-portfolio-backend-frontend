package main.java.br.com.project.portfolio.repository;

import main.br.com.project.portfolio.model.Projeto;
import main.br.com.project.portfolio.model.constants.Risco;
import main.br.com.project.portfolio.model.constants.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjetoRepository extends JpaRepository<Projeto, Long> {

  List<Projeto> findByStatus(Status status);

  List<Projeto> findByNome(String nome);

  List<Projeto> findProjetosByPessoasId(Long idPessoa);

}
