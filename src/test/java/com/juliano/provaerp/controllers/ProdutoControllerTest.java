package com.juliano.provaerp.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.juliano.provaerp.Enum.ProdutoCategoriaEnum;
import com.juliano.provaerp.Enum.ProdutoSituacaoEnum;
import com.juliano.provaerp.entity.Produto;
import com.juliano.provaerp.exceptions.ExceptionResponse;
import com.juliano.provaerp.repository.ProdutoRepository;
import com.juliano.provaerp.services.ProdutoService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ProdutoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProdutoRepository produtoRepository;

    @MockBean
    private ProdutoService produtoService;

    @Autowired
    private ProdutoController produtoController;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp(WebApplicationContext context) {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

    }

    @Test
    public void inserirEAtualizar() throws Exception {
        Produto produto = criarProduto();

        String jsonString = objectMapper.writeValueAsString(produto);

        mockMvc.perform(post("/produtos")
                        .contentType("application/json").content(jsonString))
                .andExpect(status().isOk());

        mockMvc.perform(put("/produtos")
                        .contentType("application/json").content(jsonString))
                .andExpect(status().isOk());
    }

    @Test
    public void inserirCodigoExceptionTest() throws Exception {
        Produto produto = criarProduto();
        produto.setCodigo(null);


        String jsonString = objectMapper.writeValueAsString(produto);

        MvcResult resultPost = mockMvc.perform(post("/produtos")
                        .contentType("application/json").content(jsonString))
                .andExpect(status().is4xxClientError()).andReturn();

        MvcResult resultPut = mockMvc.perform(put("/produtos")
                        .contentType("application/json").content(jsonString))
                .andExpect(status().is4xxClientError()).andReturn();

        ExceptionResponse exceptionResponsePost = objectMapper.readValue(resultPost
                .getResponse().getContentAsString(), ExceptionResponse.class);

        ExceptionResponse exceptionResponsePut = objectMapper.readValue(resultPut
                .getResponse().getContentAsString(), ExceptionResponse.class);


        Assert.assertEquals(MethodArgumentNotValidException.class, resultPost.getResolvedException().getClass());
        Assert.assertEquals(MethodArgumentNotValidException.class, resultPut.getResolvedException().getClass());

        Assert.assertEquals(exceptionResponsePost.getMessage(), "codigo necessario");
        Assert.assertEquals(exceptionResponsePut.getMessage(), "codigo necessario");

    }

    @Test
    public void inserirCategoriaExceptionTest() throws Exception {
        Produto produto = criarProduto();
        produto.setCategoria(null);


        String jsonString = objectMapper.writeValueAsString(produto);

        MvcResult resultPost = mockMvc.perform(post("/produtos")
                        .contentType("application/json").content(jsonString))
                .andExpect(status().is4xxClientError()).andReturn();

        MvcResult resultPut = mockMvc.perform(put("/produtos")
                        .contentType("application/json").content(jsonString))
                .andExpect(status().is4xxClientError()).andReturn();

        ExceptionResponse exceptionResponsePost = objectMapper.readValue(resultPost
                .getResponse().getContentAsString(), ExceptionResponse.class);

        ExceptionResponse exceptionResponsePut = objectMapper.readValue(resultPut
                .getResponse().getContentAsString(), ExceptionResponse.class);


        Assert.assertEquals(MethodArgumentNotValidException.class, resultPost.getResolvedException().getClass());
        Assert.assertEquals(MethodArgumentNotValidException.class, resultPut.getResolvedException().getClass());

        Assert.assertEquals(exceptionResponsePost.getMessage(), "insira a categoria do produto");
        Assert.assertEquals(exceptionResponsePut.getMessage(), "insira a categoria do produto");

    }

    @Test
    public void inserirSituacaoExceptionTest() throws Exception {
        Produto produto = criarProduto();
        produto.setSituacao(null);

        String jsonString = objectMapper.writeValueAsString(produto);

        MvcResult resultPost = mockMvc.perform(post("/produtos")
                        .contentType("application/json").content(jsonString))
                .andExpect(status().is4xxClientError()).andReturn();

        MvcResult resultPut = mockMvc.perform(put("/produtos")
                        .contentType("application/json").content(jsonString))
                .andExpect(status().is4xxClientError()).andReturn();

        ExceptionResponse exceptionResponsePost = objectMapper.readValue(resultPost
                .getResponse().getContentAsString(), ExceptionResponse.class);

        ExceptionResponse exceptionResponsePut = objectMapper.readValue(resultPut
                .getResponse().getContentAsString(), ExceptionResponse.class);


        Assert.assertEquals(MethodArgumentNotValidException.class, resultPost.getResolvedException().getClass());
        Assert.assertEquals(MethodArgumentNotValidException.class, resultPut.getResolvedException().getClass());

        Assert.assertEquals(exceptionResponsePost.getMessage(), "insira o status do produto");
        Assert.assertEquals(exceptionResponsePut.getMessage(), "insira o status do produto");

    }

    @Test
    public void inserirNomeExceptionTest() throws Exception {
        Produto produto = criarProduto();
        produto.setNome("vc");

        String jsonString = objectMapper.writeValueAsString(produto);

        MvcResult resultPost = mockMvc.perform(post("/produtos")
                        .contentType("application/json").content(jsonString))
                .andExpect(status().is4xxClientError()).andReturn();

        MvcResult resultPut = mockMvc.perform(put("/produtos")
                        .contentType("application/json").content(jsonString))
                .andExpect(status().is4xxClientError()).andReturn();

        ExceptionResponse exceptionResponsePost = objectMapper.readValue(resultPost
                .getResponse().getContentAsString(), ExceptionResponse.class);

        ExceptionResponse exceptionResponsePut = objectMapper.readValue(resultPut
                .getResponse().getContentAsString(), ExceptionResponse.class);


        Assert.assertEquals(MethodArgumentNotValidException.class, resultPost.getResolvedException().getClass());
        Assert.assertEquals(MethodArgumentNotValidException.class, resultPut.getResolvedException().getClass());

        Assert.assertEquals(exceptionResponsePost.getMessage(), "nome com menos de tres digitos");
        Assert.assertEquals(exceptionResponsePut.getMessage(), "nome com menos de tres digitos");

    }
    @Test
    public void inserirNomeVazioExceptionTest() throws Exception {
        Produto produto = criarProduto();
        produto.setNome(null);

        String jsonString = objectMapper.writeValueAsString(produto);

        MvcResult resultPost = mockMvc.perform(post("/produtos")
                        .contentType("application/json").content(jsonString))
                .andExpect(status().is4xxClientError()).andReturn();

        MvcResult resultPut = mockMvc.perform(put("/produtos")
                        .contentType("application/json").content(jsonString))
                .andExpect(status().is4xxClientError()).andReturn();

        ExceptionResponse exceptionResponsePost = objectMapper.readValue(resultPost
                .getResponse().getContentAsString(), ExceptionResponse.class);

        ExceptionResponse exceptionResponsePut = objectMapper.readValue(resultPut
                .getResponse().getContentAsString(), ExceptionResponse.class);


        Assert.assertEquals(MethodArgumentNotValidException.class, resultPost.getResolvedException().getClass());
        Assert.assertEquals(MethodArgumentNotValidException.class, resultPut.getResolvedException().getClass());

        Assert.assertEquals(exceptionResponsePost.getMessage(), "insira um nome");
        Assert.assertEquals(exceptionResponsePut.getMessage(), "insira um nome");

    }

    @Test
    public void inserirPrecoExceptionTest() throws Exception {
        Produto produto = criarProduto();
        produto.setPreco(null);

        String jsonString = objectMapper.writeValueAsString(produto);

        MvcResult resultPost = mockMvc.perform(post("/produtos")
                        .contentType("application/json").content(jsonString))
                .andExpect(status().is4xxClientError()).andReturn();

        MvcResult resultPut = mockMvc.perform(put("/produtos")
                        .contentType("application/json").content(jsonString))
                .andExpect(status().is4xxClientError()).andReturn();

        ExceptionResponse exceptionResponsePost = objectMapper.readValue(resultPost
                .getResponse().getContentAsString(), ExceptionResponse.class);

        ExceptionResponse exceptionResponsePut = objectMapper.readValue(resultPut
                .getResponse().getContentAsString(), ExceptionResponse.class);


        Assert.assertEquals(MethodArgumentNotValidException.class, resultPost.getResolvedException().getClass());
        Assert.assertEquals(MethodArgumentNotValidException.class, resultPut.getResolvedException().getClass());

        Assert.assertEquals(exceptionResponsePost.getMessage(), "valor necessario");
        Assert.assertEquals(exceptionResponsePut.getMessage(), "valor necessario");

    }
    public Produto criarProduto() {
        Produto produto = new Produto();
        produto.setId(UUID.randomUUID());
        produto.setCodigo(12);
        produto.setCategoria(ProdutoCategoriaEnum.BemDeConsumo);
        produto.setSituacao(ProdutoSituacaoEnum.Ativado);
        produto.setNome("teste");
        produto.setPreco(BigDecimal.ONE);
        return produto;
    }

    @Test
    public void buscar() throws Exception {
        mockMvc.perform(get("/produtos"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/produtos")
                        .param("page", "1")
                        .param("size", "10"))
                .andExpect(status().isOk());
    }

    @Test
    public void deletar() throws Exception {
        mockMvc.perform(delete("/produtos/1"))
                .andExpect(status().is4xxClientError());
    }
}
