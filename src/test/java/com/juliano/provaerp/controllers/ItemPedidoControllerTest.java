package com.juliano.provaerp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.juliano.provaerp.dto.ItemPedidoDTO;
import com.juliano.provaerp.entity.ItemPedido;
import com.juliano.provaerp.entity.Produto;
import com.juliano.provaerp.repository.ItemPedidoRepository;
import com.juliano.provaerp.services.ItemPedidoService;
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
    public void buscar() throws Exception {
        mockMvc.perform(get("/itens"))
                .andExpect(status().isOk());
        mockMvc.perform(get("/itens/1"))
                .andExpect(status().isOk());
        mockMvc.perform(get("/itens/paginado").param("page","1").param("size","10"))
                .andExpect(status().isOk());

    }

    @Test
    public void inserir() throws Exception {
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
    public void atualizar() throws Exception {

        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setId(UUID.randomUUID());
        itemPedido.setCodigo(1);
        itemPedido.setQuantidade(1);
        itemPedido.setProduto(new Produto());

        String jsonString = objectMapper.writeValueAsString(itemPedido);

        mockMvc.perform(put("/itens")
                        .contentType("application/json").content(jsonString))
                .andExpect(status().isOk());
    }
    @Test
    public void deletar() throws Exception {
        mockMvc.perform(delete("/itens/1"))
                .andExpect(status().isOk());
    }
}
