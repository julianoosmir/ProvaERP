package com.juliano.provaerp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.juliano.provaerp.Enum.ProdutoCategoriaEnum;
import com.juliano.provaerp.Enum.ProdutoSituacaoEnum;
import com.juliano.provaerp.dto.ItemPedidoDTO;
import com.juliano.provaerp.entity.ItemPedido;
import com.juliano.provaerp.entity.Produto;
import com.juliano.provaerp.exceptions.ExceptionResponse;
import com.juliano.provaerp.repository.ItemPedidoRepository;
import com.juliano.provaerp.services.ItemPedidoService;
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
public class ItemPedidoControllerTest {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemPedidoService itemPedidoService;
    @MockBean
    private ItemPedidoRepository itemPedidoRepository;

    @BeforeEach
    public void setUp(WebApplicationContext context) {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void buscarTest() throws Exception {
        mockMvc.perform(get("/itens"))
                .andExpect(status().isOk());
        mockMvc.perform(get("/itens/1"))
                .andExpect(status().isOk());
        mockMvc.perform(get("/itens/paginado").param("page","1").param("size","10"))
                .andExpect(status().isOk());

    }

    @Test
    public void inserirTest() throws Exception {
        ItemPedidoDTO itemDTO = new ItemPedidoDTO();

        itemDTO.setCodigo(1);
        itemDTO.setProduto(UUID.randomUUID());
        itemDTO.setQuantidade(1);

        String jsonString = objectMapper.writeValueAsString(itemDTO);

        mockMvc.perform(post("/itens")
                        .contentType("application/json").content(jsonString))
                .andExpect(status().isOk());

    }

    @Test
    public void inserirCodigoNuloTest() throws Exception {
        ItemPedidoDTO itemDTO = new ItemPedidoDTO();

        itemDTO.setCodigo(null);
        itemDTO.setProduto(UUID.randomUUID());
        itemDTO.setQuantidade(1);

        String jsonString = objectMapper.writeValueAsString(itemDTO);

        MvcResult resultPost = mockMvc.perform(post("/itens")
                        .contentType("application/json").content(jsonString))
                .andExpect(status().isBadRequest()).andReturn();

        ExceptionResponse exceptionResponsePost = objectMapper.readValue(resultPost
                .getResponse().getContentAsString(), ExceptionResponse.class);

        Assert.assertEquals(MethodArgumentNotValidException.class, resultPost.getResolvedException().getClass());
        Assert.assertEquals(exceptionResponsePost.getMessage(), "codigo necessario");

    }

    @Test
    public void inserirQuantidadeNuloTest() throws Exception     {
        ItemPedidoDTO itemDTO = new ItemPedidoDTO();

        itemDTO.setCodigo(1);
        itemDTO.setProduto(UUID.randomUUID());
        itemDTO.setQuantidade(null);

        String jsonString = objectMapper.writeValueAsString(itemDTO);

        MvcResult resultPost = mockMvc.perform(post("/itens")
                        .contentType("application/json").content(jsonString))
                .andExpect(status().isBadRequest()).andReturn();

        ExceptionResponse exceptionResponsePost = objectMapper.readValue(resultPost
                .getResponse().getContentAsString(), ExceptionResponse.class);

        Assert.assertEquals(MethodArgumentNotValidException.class, resultPost.getResolvedException().getClass());
        Assert.assertEquals(exceptionResponsePost.getMessage(), "quantidade necessaria");

    }
    @Test
    public void inserirProdutoNuloTest() throws Exception     {
        ItemPedidoDTO itemDTO = new ItemPedidoDTO();

        itemDTO.setCodigo(1);
        itemDTO.setProduto(null);
        itemDTO.setQuantidade(1);

        String jsonString = objectMapper.writeValueAsString(itemDTO);

        MvcResult resultPost = mockMvc.perform(post("/itens")
                        .contentType("application/json").content(jsonString))
                .andExpect(status().isBadRequest()).andReturn();

        ExceptionResponse exceptionResponsePost = objectMapper.readValue(resultPost
                .getResponse().getContentAsString(), ExceptionResponse.class);

        Assert.assertEquals(MethodArgumentNotValidException.class, resultPost.getResolvedException().getClass());
        Assert.assertEquals(exceptionResponsePost.getMessage(), "Produto necessario");

    }
    @Test
    public void atualizarTest() throws Exception {

        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setId(UUID.randomUUID());
        itemPedido.setCodigo(1);
        itemPedido.setQuantidade(1);
        itemPedido.setProduto(criarProduto());

        String jsonString = objectMapper.writeValueAsString(itemPedido);

        mockMvc.perform(put("/itens")
                        .contentType("application/json").content(jsonString))
                .andExpect(status().isOk());
    }

    @Test
    public void atualizarComCodigoNuloTest() throws Exception {

        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setId(UUID.randomUUID());
        itemPedido.setCodigo(null);
        itemPedido.setQuantidade(1);
        itemPedido.setProduto(criarProduto());

        String jsonString = objectMapper.writeValueAsString(itemPedido);

        MvcResult resultPut = mockMvc.perform(put("/itens")
                        .contentType("application/json").content(jsonString))
                .andExpect(status().is4xxClientError()).andReturn();
        ExceptionResponse exceptionResponsePost = objectMapper.readValue(resultPut
                .getResponse().getContentAsString(), ExceptionResponse.class);

        Assert.assertEquals(MethodArgumentNotValidException.class,
                resultPut.getResolvedException().getClass());
        Assert.assertEquals(exceptionResponsePost.getMessage(), "codigo necessario");
    }

    @Test
    public void atualizarComQuantidadeNulaTest() throws Exception {

        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setId(UUID.randomUUID());
        itemPedido.setCodigo(1);
        itemPedido.setQuantidade(null);
        itemPedido.setProduto(criarProduto());

        String jsonString = objectMapper.writeValueAsString(itemPedido);

        MvcResult resultPut = mockMvc.perform(put("/itens")
                        .contentType("application/json").content(jsonString))
                .andExpect(status().is4xxClientError()).andReturn();

        ExceptionResponse exceptionResponsePut = objectMapper.readValue(resultPut
                .getResponse().getContentAsString(), ExceptionResponse.class);

        Assert.assertEquals(MethodArgumentNotValidException.class,
                resultPut.getResolvedException().getClass());
        Assert.assertEquals(exceptionResponsePut.getMessage(), "quantidade necessaria");
    }

    @Test
    public void atualizarProdutoNuloTest() throws Exception {

        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setId(UUID.randomUUID());
        itemPedido.setCodigo(1);
        itemPedido.setQuantidade(1);
        itemPedido.setProduto(null);

        String jsonString = objectMapper.writeValueAsString(itemPedido);

        MvcResult resultPut = mockMvc.perform(put("/itens")
                        .contentType("application/json").content(jsonString))
                .andExpect(status().isBadRequest()).andReturn();

        ExceptionResponse exceptionResponsePut = objectMapper.readValue(resultPut
                .getResponse().getContentAsString(), ExceptionResponse.class);

        Assert.assertEquals(MethodArgumentNotValidException.class,
                resultPut.getResolvedException().getClass());
        Assert.assertEquals(exceptionResponsePut.getMessage(), "Produto necessario");
    }
    @Test
    public void deletar() throws Exception {
        mockMvc.perform(delete("/itens/1"))
                .andExpect(status().isOk());
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
}
