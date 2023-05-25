package br.com.project.portfolio.service;

import br.com.project.portfolio.exception.ResourceNotFoundException;
import br.com.project.portfolio.model.Pessoa;
import br.com.project.portfolio.model.Projeto;
import br.com.project.portfolio.repository.PessoaRepository;
import br.com.project.portfolio.repository.ProjetoRepository;
import br.com.project.portfolio.utils.PessoaTestUtils;
import br.com.project.portfolio.utils.ProjetoTestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.stubbing.Answer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@Slf4j
class PessoaServiceTest {
    public static final long ID = 1;
    public static final String NAME = "Mario dos Santos Silva";
    @Mock
    ResponseEntity<Pessoa> pessoaCreate;
    @Mock
    private ResponseEntity<List<Pessoa>> entityListPessoa;
    @Mock
    private ResponseEntity<Pessoa> pessoa;
    @Mock
    private List<Pessoa> listPessoa;

    @Mock
    private Pessoa pessoas;
    @Mock
    private ResponseEntity<List<Projeto>> listProjeto;
    @Mock
    private List<Projeto> entityProjeto;
    @InjectMocks
    private PessoaService pessoaService;
    @Mock
    private PessoaRepository pessoaRepository;

    @Mock
    private ProjetoRepository projetoRepository;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        startPPessoaServiceTest();
    }

    private List<Pessoa> pessoaIterable;
    @Test
    @DisplayName("Search all pessoas - Ok")
    void findAllPessoa() {
        when(pessoaRepository.findAll()).thenReturn(pessoaIterable);
        ResponseEntity<List<Pessoa>> iterable = pessoaService.findAllPessoa(NAME);
        assertNotNull(iterable);
    }

    @Test
    @DisplayName("Search pessoa - Ok")
    void findPessoaById() {
        Optional<Pessoa> pessoaData = pessoaRepository.findById(ID);
        when(pessoaRepository.findById(anyLong())).thenReturn(Optional.of(listPessoa.get(0)));
        assertNotNull(Optional.of(listPessoa.get(0)));
    }

    @Test
    @DisplayName("Update pessoa - Ok")
    void updatePessoa() {
        Optional<Pessoa> optionalPessoa = Optional.of(listPessoa.get(0));
        //when(pessoaService.updatePessoa(anyLong(), anyObject())).thenReturn((ResponseEntity<Pessoa>) listPessoa);
        when(pessoaRepository.findById(anyLong())).thenReturn(Optional.of(listPessoa.get(0)));
    }

    @Test
    @DisplayName("Delete pessoa - Ok")
    void deletePessoa() {
        when(pessoaRepository.findById(anyLong())).thenReturn(Optional.of(listPessoa.get(0)));
        doNothing().when(pessoaRepository).deleteById(ID);
        pessoaService.deletePessoa(ID);
        verify(pessoaRepository, times(1)).deleteById(ID);
    }

    @Test
    @DisplayName("Delete all pessoas - Ok")
    void deleteAllPessoas() {

        when(pessoaRepository.findAll()).thenReturn(listPessoa);
        doNothing().when(pessoaRepository).deleteAll();
        pessoaService.deleteAllPessoas();
        verify(pessoaRepository, times(1)).deleteAll();
    }
    @Test
    @DisplayName("Search pessoa by funcionario - Ok")
    void findByFuncionario() {

        when(pessoaRepository.findByFuncionario(true)).thenReturn(pessoaIterable);
        ResponseEntity<List<Pessoa>> iterable = pessoaService.findByFuncionario();
        assertNotNull(iterable);
        List<Pessoa> pessoas = pessoaRepository.findByFuncionario(true);
        assertNull(pessoas);
    }

    @Test
    @DisplayName("Search all projetos by pessoa - Ok")
    void getAllProjetosByPessoaId() {
        when(pessoaRepository.existsById(anyLong())).thenReturn(true);

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            pessoaService.getAllProjetosByPessoaId(null);
        });

        String expectedMessage = "Not found Pessoa  with id";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("Save pessoa - Ok")
    void testSavePessoa() {
        when(pessoaRepository.save(anyObject())).thenReturn(pessoa);
        assertEquals("Mario dos Santos Silva", listPessoa.get(0).getNome());
    }

    @Test
    @DisplayName("Add pessoa - Ok")
    void addPessoa() {
        when(pessoaRepository.existsById(anyLong())).thenReturn(true);
        when(pessoaRepository.save(anyObject())).thenReturn(pessoa);
        assertEquals("Mario dos Santos Silva", listPessoa.get(0).getNome());

    }

    @Test
    @DisplayName("Delete pessoa from projeto - Ok")
    void deletePessoaFromProjeto() {

        when(projetoRepository.findById(anyLong())).thenReturn(Optional.ofNullable(entityProjeto.get(0)));
    }

    @Test
    @DisplayName("Delete pessoa from projeto - Ok")
    void deletePessoaFromProjetoReturnNull() {

        when(projetoRepository.findById(anyLong())).thenReturn(Optional.of(new Projeto()));

        /*Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            projetoRepository.findById(11L);
        });
        String expectedMessage = "Not found Projeto with id = ";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));*/
    }

    @Test
    @DisplayName("search pessoa null - Ok")
    void findAllPessoaNull() {

        when(pessoaRepository.findAll()).thenReturn(pessoaIterable);
        ResponseEntity<List<Pessoa>> iterable = pessoaService.findAllPessoa(null);
        assertNotNull(iterable);
    }

    @DisplayName("Start")
    private void startPPessoaServiceTest() {
        entityListPessoa = new ResponseEntity<>(PessoaTestUtils.pessoaEntityCreator(), HttpStatus.OK);
        pessoa = new ResponseEntity<>(PessoaTestUtils.pessoaEntityCreator().get(0), HttpStatus.OK);
        pessoaCreate = new ResponseEntity<>(PessoaTestUtils.pessoaEntityCreator().get(0), HttpStatus.CREATED);
        listPessoa = PessoaTestUtils.pessoaEntityCreator();
        pessoas = PessoaTestUtils.pessoaEntityCreator().get(0);
        listProjeto = new ResponseEntity<>(ProjetoTestUtils.projetoEntityCreator(), HttpStatus.OK);
        entityProjeto = ProjetoTestUtils.projetoEntityCreator();
    }
}