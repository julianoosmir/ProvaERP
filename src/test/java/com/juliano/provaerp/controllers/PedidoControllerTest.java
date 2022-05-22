package com.juliano.provaerp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.juliano.provaerp.Enum.PedidoSituacaoEnum;
import com.juliano.provaerp.dto.PedidoDTO;
import com.juliano.provaerp.exceptions.ExceptionResponse;
import com.juliano.provaerp.repository.PedidoRepository;
import com.juliano.provaerp.services.PedidoService;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PedidoControllerTest {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PedidoService pedidoService;
    @MockBean
    private PedidoRepository pedidoRepository;

    @BeforeEach
    public void setUp(WebApplicationContext context) {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
    @Test
    public void buscar() throws Exception {
        mockMvc.perform(get("/pedidos"))
                .andExpect(status().isOk());
        mockMvc.perform(get("/pedidos/1"))
                .andExpect(status().isOk());
        mockMvc.perform(get("/pedidos/paginado")
                        .param("page","1")
                        .param("size","10"))
                .andExpect(status().isOk());
    }
    @Test
    public void inserirEAtualizar() throws Exception {
        PedidoDTO pedidoDTO = new PedidoDTO();
        pedidoDTO.setCodigoPedido(1);
        pedidoDTO.setDesconto(BigDecimal.TEN);
        pedidoDTO.setCodigoItemPedido(1);
        pedidoDTO.setSituacao(PedidoSituacaoEnum.Aberto);

        String jsonString = objectMapper.writeValueAsString(pedidoDTO);

        mockMvc.perform(post("/pedidos")
                        .contentType("application/json").content(jsonString))
                .andExpect(status().isOk());
        mockMvc.perform(put("/pedidos")
                        .contentType("application/json").content(jsonString))
                .andExpect(status().isOk());
    }

    @Test
    public void inserirEAtualizarComCodigoPedidoNuloTest() throws Exception {
        PedidoDTO pedidoDTO = new PedidoDTO();
        pedidoDTO.setCodigoPedido(null);
        pedidoDTO.setDesconto(BigDecimal.TEN);
        pedidoDTO.setCodigoItemPedido(1);
        pedidoDTO.setSituacao(PedidoSituacaoEnum.Aberto);

        String jsonString = objectMapper.writeValueAsString(pedidoDTO);

        MvcResult resultPost = mockMvc.perform(post("/pedidos")
                        .contentType("application/json").content(jsonString))
                .andExpect(status().is4xxClientError()).andReturn();
        MvcResult resultPut=  mockMvc.perform(put("/pedidos")
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
    public void inserirEAtualizarComCodigoItemPedidoNuloTest() throws Exception {
        PedidoDTO pedidoDTO = new PedidoDTO();
        pedidoDTO.setCodigoPedido(1);
        pedidoDTO.setDesconto(BigDecimal.TEN);
        pedidoDTO.setCodigoItemPedido(null);
        pedidoDTO.setSituacao(PedidoSituacaoEnum.Aberto);

        String jsonString = objectMapper.writeValueAsString(pedidoDTO);

        MvcResult resultPost = mockMvc.perform(post("/pedidos")
                        .contentType("application/json").content(jsonString))
                .andExpect(status().is4xxClientError()).andReturn();
        MvcResult resultPut=  mockMvc.perform(put("/pedidos")
                        .contentType("application/json").content(jsonString))
                .andExpect(status().is4xxClientError()).andReturn();

        ExceptionResponse exceptionResponsePost = objectMapper.readValue(resultPost
                .getResponse().getContentAsString(), ExceptionResponse.class);

        ExceptionResponse exceptionResponsePut = objectMapper.readValue(resultPut
                .getResponse().getContentAsString(), ExceptionResponse.class);


        Assert.assertEquals(MethodArgumentNotValidException.class, resultPost.getResolvedException().getClass());
        Assert.assertEquals(MethodArgumentNotValidException.class, resultPut.getResolvedException().getClass());

        Assert.assertEquals(exceptionResponsePost.getMessage(), "item do pedido necessario");
        Assert.assertEquals(exceptionResponsePut.getMessage(), "item do pedido necessario");
    }
    @Test
    public void inserirEAtualizarSituacaoNulaTest() throws Exception {
        PedidoDTO pedidoDTO = new PedidoDTO();
        pedidoDTO.setCodigoPedido(1);
        pedidoDTO.setDesconto(BigDecimal.TEN);
        pedidoDTO.setCodigoItemPedido(1);
        pedidoDTO.setSituacao(null);

        String jsonString = objectMapper.writeValueAsString(pedidoDTO);

        MvcResult resultPost = mockMvc.perform(post("/pedidos")
                        .contentType("application/json").content(jsonString))
                .andExpect(status().is4xxClientError()).andReturn();
        MvcResult resultPut=  mockMvc.perform(put("/pedidos")
                        .contentType("application/json").content(jsonString))
                .andExpect(status().is4xxClientError()).andReturn();

        ExceptionResponse exceptionResponsePost = objectMapper.readValue(resultPost
                .getResponse().getContentAsString(), ExceptionResponse.class);

        ExceptionResponse exceptionResponsePut = objectMapper.readValue(resultPut
                .getResponse().getContentAsString(), ExceptionResponse.class);


        Assert.assertEquals(MethodArgumentNotValidException.class, resultPost.getResolvedException().getClass());
        Assert.assertEquals(MethodArgumentNotValidException.class, resultPut.getResolvedException().getClass());

        Assert.assertEquals(exceptionResponsePost.getMessage(), "insira o status do pedido");
        Assert.assertEquals(exceptionResponsePut.getMessage(), "insira o status do pedido");
    }


    @Test
    public void deletar() throws Exception {
        mockMvc.perform(delete("/pedidos/1"))
                .andExpect(status().isOk());
    }
}
