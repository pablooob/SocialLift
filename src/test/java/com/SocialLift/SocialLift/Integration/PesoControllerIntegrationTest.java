package com.SocialLift.SocialLift.Integration;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.SocialLift.SocialLift.Models.Peso;
import com.SocialLift.SocialLift.Services.PesoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class PesoControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PesoService pesoService;

    @Test
    public void testNewPeso() throws Exception {
        Peso peso = new Peso();
        peso.setRegistro(new Date());
        peso.setValue(70.5);

        mockMvc.perform(post("/api/Peso")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(peso)))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetPesoById() throws Exception {
        Peso peso = new Peso();
        peso.setRegistro(new Date());
        peso.setValue(75.2);
        peso = pesoService.NewPeso(peso);

        mockMvc.perform(get("/api/Peso/{id}", peso.getIdPeso())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.idPeso").value(peso.getIdPeso().intValue()))
                .andExpect(jsonPath("$.value").value(peso.getValue()));
    }

    /*
    @Test
    public void testUpdatePeso() throws Exception {
        Peso peso = new Peso();
        peso.setRegistro(new Date());
        peso.setValue(80.0);
        peso = pesoService.NewPeso(peso);

        peso.setValue(85.0);

        mockMvc.perform(put("/api/Peso")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(peso)))
                .andExpect(status().isOk());

        Peso updatedPeso = pesoService.GetPesoById(peso.getIdPeso()).orElse(null);
        assert updatedPeso != null;
        assertEquals(peso.getValue(), updatedPeso.getValue());
    }
     */

    @Test
    public void testDeletePesoById() throws Exception {
        Peso peso = new Peso();
        peso.setRegistro(new Date());
        peso.setValue(90.0);
        peso = pesoService.NewPeso(peso);

        mockMvc.perform(delete("/api/Peso/{id}", peso.getIdPeso()))
                .andExpect(status().isOk());

        Peso deletedPeso = pesoService.GetPesoById(peso.getIdPeso()).orElse(null);
        assertNull(deletedPeso);
    }
}
