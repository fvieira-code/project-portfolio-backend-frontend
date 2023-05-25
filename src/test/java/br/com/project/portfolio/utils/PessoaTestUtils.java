package br.com.project.portfolio.utils;

import br.com.project.portfolio.model.Pessoa;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PessoaTestUtils {

    public static List<Pessoa> pessoaEntityCreator() {

        List<Pessoa> listPessoa = new ArrayList<Pessoa>();
        Date date = new Date();

        listPessoa.add( new Pessoa("Mario dos Santos Silva", null, "888.774.254-87", Boolean.TRUE));

        return listPessoa;
    }

}
