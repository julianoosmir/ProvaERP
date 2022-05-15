package com.juliano.provaerp.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.juliano.provaerp.Enum.ProdutoCategoriaEnum;
import com.juliano.provaerp.Enum.ProdutoSituacaoEnum;
import com.juliano.provaerp.entity.Produto;
import com.juliano.provaerp.repository.ProdutoRepository;
import com.juliano.provaerp.services.ProdutoService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;

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
        Produto produto = new Produto();
        produto.setId(UUID.randomUUID());
        produto.setCodigo(1);
        produto.setCategoria(ProdutoCategoriaEnum.BemDeConsumo);
        produto.setSituacao(ProdutoSituacaoEnum.Ativado);
        produto.setNome("teste");
        produto.setPreco(BigDecimal.ONE);

        String jsonString = objectMapper.writeValueAsString(produto);

        mockMvc.perform(post("/produtos")
                        .contentType("application/json").content(jsonString))
                .andExpect(status().isOk());

        mockMvc.perform(put("/produtos")
                        .contentType("application/json").content(jsonString))
                .andExpect(status().isOk());
    }

    @Test
    public void buscar() throws Exception {
        mockMvc.perform(get("/produtos"))
                .andExpect(status().isOk());
        mockMvc.perform(get("/produtos/paginado").param("page","1").param("size","10"))
                .andExpect(status().isOk());
    }
    @Test
    public void deletar() throws Exception {
        mockMvc.perform(delete("/produtos/1"))
                .andExpect(status().is4xxClientError());
    }
}
