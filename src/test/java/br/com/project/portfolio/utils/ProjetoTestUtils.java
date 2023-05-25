package br.com.project.portfolio.utils;

import br.com.project.portfolio.model.Pessoa;
import br.com.project.portfolio.model.Projeto;
import br.com.project.portfolio.model.constants.Risco;
import br.com.project.portfolio.model.constants.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProjetoTestUtils {

    public static List<Projeto> projetoEntityCreator() {

        List<Projeto> listProjeto = new ArrayList<Projeto>();
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd H:mm");
        LocalDateTime dateTime = LocalDateTime.parse("2023-02-01 09:30", formatterDate);

        listProjeto.add(new Projeto("Projeto Software 001", null, null, null,
                "Projeto destinado para o desenvolvimento de software", Status.INICIADO,
                5000.0, Risco.MEDIO));

        return listProjeto;
    }

}
