package com.SocialLift.SocialLift.Integration;

import com.SocialLift.SocialLift.Models.IMC;
import com.SocialLift.SocialLift.Services.IMCService;
import static org.junit.jupiter.api.Assertions.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional // Asegura que las transacciones sean rollback después de cada prueba
public class IMCControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper; // ObjectMapper para convertir objetos a JSON y viceversa

    @Autowired
    private IMCService imcService;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testNewIMC() throws Exception {
        IMC imc = new IMC();
        imc.setValue(25.0);
        imc.setRegistro(new Date());

        mockMvc.perform(post("/api/IMC")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(imc)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.value").value(25.0));
    }

    /*
    @Test
    public void testGetIMCs() throws Exception {
        mockMvc.perform(get("/api/IMC")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }

     */

    @Test
    public void testGetById() throws Exception {
        IMC imc = new IMC();
        imc.setValue(30.0);
        imc.setRegistro(new Date());
        IMC savedImc = imcService.NewIMC(imc);

        mockMvc.perform(get("/api/IMC/{id}", savedImc.getIdIMC())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.idIMC").value(savedImc.getIdIMC().intValue()))
                .andExpect(jsonPath("$.value").value(30.0));
    }

    /*
    @Test
    public void testUpdateIMC() throws Exception {
        IMC imc = new IMC();
        imc.setValue(35.0);
        Date specificDate = new Date(1230768000000L);
        imc.setRegistro(specificDate);
        imc = imcService.NewIMC(imc);

        imc.setValue(40.0);

        mockMvc.perform(put("/api/IMC")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(imc)))
                .andExpect(status().isOk());

    }

     */


    @Test
    public void testDeleteIMCById() throws Exception {
        IMC imc = new IMC();
        imc.setValue(45.0);
        imc.setRegistro(new Date());
        IMC savedImc = imcService.NewIMC(imc);

        mockMvc.perform(delete("/api/IMC/{id}", savedImc.getIdIMC()))
                .andExpect(status().isOk());

        Optional<IMC> deletedImc = imcService.GetIMCById(savedImc.getIdIMC());
        assertFalse(deletedImc.isPresent());
    }
}
