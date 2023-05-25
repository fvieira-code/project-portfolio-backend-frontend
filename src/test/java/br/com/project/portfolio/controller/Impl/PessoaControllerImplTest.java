package br.com.project.portfolio.controller.Impl;

import br.com.project.portfolio.model.Pessoa;
import br.com.project.portfolio.model.Projeto;
import br.com.project.portfolio.service.PessoaService;
import br.com.project.portfolio.service.ProjetoService;
import br.com.project.portfolio.utils.AbstractTestUtils;
import br.com.project.portfolio.utils.PessoaTestUtils;
import br.com.project.portfolio.utils.ProjetoTestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PessoaControllerImplTest {

    public static final long ID = 1;
    public static final String NAME = "Mario dos Santos Silva";
    @Mock
    ResponseEntity<List<Pessoa>> listPessoa;
    @Mock
    ResponseEntity<Pessoa> pessoa;
    @Mock
    List<Pessoa> entityPessoa;
    @Mock
    ResponseEntity<List<Projeto>> listProjeto;
    @Mock
    List<Projeto> entityProjeto;
    @MockBean
    ProjetoService projetoService;
    @MockBean
    PessoaService pessoaService;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        startPessoaControllerImplTest();
    }

    @Test
    @DisplayName("Search all pessoas")
    void getAllPessoas() throws Exception {
        when(pessoaService.findAllPessoa(anyString())).thenReturn(listPessoa);
        MvcResult result = mockMvc.perform(
                get("/api/v1/project/portfolio/pessoas")
                        .param("nome", NAME)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        var response = result.getResponse();
        String jsonResponse = response.getContentAsString();
        assertEquals(response.getStatus(), HttpServletResponse.SC_OK);
    }

    @Test
    @DisplayName("Search pessoas by id")
    void getPessoaById() throws Exception {
        when(pessoaService.findPessoaById(anyLong())).thenReturn(pessoa);
        MvcResult result = mockMvc.perform(
                        get("/api/v1/project/portfolio/pessoas/{id}", ID)
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        var response = result.getResponse();
        String jsonResponse = response.getContentAsString();
        assertEquals(AbstractTestUtils.mapToJson(entityPessoa.get(0


        )), jsonResponse);
        assertEquals(response.getStatus(), HttpServletResponse.SC_OK);
    }

    @Test
    @DisplayName("Create pessoa")
    void createPessoa() throws Exception {
        when(pessoaService.savePessoa(any())).thenReturn(pessoa);
        String content = AbstractTestUtils.mapToJson(pessoa);
        MvcResult result = mockMvc.perform(
                        post("/api/v1/project/portfolio/pessoas")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content.getBytes()))
                .andExpect(status().isOk())
                .andReturn();
        assertEquals( result.getResponse().getStatus() , HttpStatus.OK.value() );
    }

    @Test
    @DisplayName("Update pessoa")
    void updatePessoa() throws Exception {
        when(pessoaService.updatePessoa(anyLong(), anyObject())).thenReturn(pessoa);

        String content = AbstractTestUtils.mapToJson(pessoa);

        MvcResult result = mockMvc.perform(
                        put("/api/v1/project/portfolio/pessoas/{id}", ID)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content.getBytes()))
                .andExpect(status().isOk())
                .andReturn();
        assertEquals( result.getResponse().getStatus() , HttpStatus.OK.value() );
    }

    @Test
    @DisplayName("Delete pessoa")
    void deletePessoa() throws Exception {
        when(pessoaService.deletePessoa(anyLong())).thenReturn(new ResponseEntity<>(HttpStatus.NO_CONTENT));
        MvcResult result = mockMvc.perform(
                        delete("/api/v1/project/portfolio/pessoas/{id}", ID)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andReturn();
        assertEquals( result.getResponse().getStatus() , HttpStatus.NO_CONTENT.value() );
    }

    @Test
    @DisplayName("Delete all pessoas")
    void deleteAllPessoas() throws Exception {
        when(pessoaService.deleteAllPessoas()).thenReturn(new ResponseEntity<>(HttpStatus.NO_CONTENT));
        MvcResult result = mockMvc.perform(
                        delete("/api/v1/project/portfolio/pessoas")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andReturn();
        assertEquals( result.getResponse().getStatus() , HttpStatus.NO_CONTENT.value() );
    }

    @Test
    @DisplayName("Search pessoas by funcionario")
    void findByFuncionario() throws Exception {
        when(pessoaService.findByFuncionario()).thenReturn(listPessoa);
        MvcResult result = mockMvc.perform(
                        get("/api/v1/project/portfolio/pessoas/funcionario")
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        var response = result.getResponse();
        String jsonResponse = response.getContentAsString();
        assertEquals(AbstractTestUtils.mapToJson(PessoaTestUtils.pessoaEntityCreator()), jsonResponse);
        assertEquals(response.getStatus(), HttpServletResponse.SC_OK);
    }

    @Test
    @DisplayName("Search projeto by pessoa")
    void getAllPessoasByProjetoId() throws Exception {
        when(projetoService.getAllPessoasByProjetoId(anyLong())).thenReturn(listPessoa);
        MvcResult result = mockMvc.perform(
        get("/api/v1/project/portfolio/projetos/{idProjeto}/pessoas", ID)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        var response = result.getResponse();

        String jsonResponse = response.getContentAsString();
        assertEquals(AbstractTestUtils.mapToJson(entityPessoa), jsonResponse);
        assertEquals(response.getStatus(), HttpServletResponse.SC_OK);
    }

    @Test
    @DisplayName("Search pessoa by projeto")
    void getAllProjetosByPessoaId() throws Exception {
        when(pessoaService.getAllProjetosByPessoaId(anyLong())).thenReturn(listProjeto);
        MvcResult result = mockMvc.perform(
                        get("/api/v1/project/portfolio/pessoas/{idPessoa}/projetos", ID)
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        var response = result.getResponse();
        String jsonResponse = response.getContentAsString();
        assertEquals(AbstractTestUtils.mapToJson(entityProjeto), jsonResponse);
        assertEquals(response.getStatus(), HttpServletResponse.SC_OK);
    }

    @Test
    @DisplayName("Post projeto by pessoa")
    void addPessoa() throws Exception {
        when(pessoaService.addPessoa(anyLong(), anyObject())).thenReturn(pessoa);
        String content = AbstractTestUtils.mapToJson(pessoa);
        MvcResult result = mockMvc.perform(
                        post("/api/v1/project/portfolio/projetos/{idProjeto}/pessoas", ID)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content.getBytes()))
                .andExpect(status().isOk())
                .andReturn();
        assertEquals( result.getResponse().getStatus() , HttpStatus.OK.value() );
    }

    @Test
    @DisplayName("Delete pessoa from projeto")
    void deletePessoaFromProjeto() throws Exception {
        when(pessoaService.deletePessoaFromProjeto(anyLong(), anyLong())).thenReturn(new ResponseEntity<>(HttpStatus.NO_CONTENT));
        MvcResult result = mockMvc.perform(
                        delete("/api/v1/project/portfolio/projetos/{idProjeto}/pessoas/{idPessoa}", 0L, ID)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andReturn();
        assertEquals( result.getResponse().getStatus() , HttpStatus.NO_CONTENT.value() );
    }

    @DisplayName("Start")
    private void startPessoaControllerImplTest() {
        listPessoa = new ResponseEntity<>(PessoaTestUtils.pessoaEntityCreator(), HttpStatus.OK);
        pessoa = new ResponseEntity<>(PessoaTestUtils.pessoaEntityCreator().get(0), HttpStatus.OK);
        entityPessoa = PessoaTestUtils.pessoaEntityCreator();
        listProjeto = new ResponseEntity<>(ProjetoTestUtils.projetoEntityCreator(), HttpStatus.OK);
        entityProjeto = ProjetoTestUtils.projetoEntityCreator();
    }
}