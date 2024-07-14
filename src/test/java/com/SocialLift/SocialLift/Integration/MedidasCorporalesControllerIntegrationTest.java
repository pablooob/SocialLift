package com.SocialLift.SocialLift.Integration;

import com.SocialLift.SocialLift.Models.MedidasCorporales;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class MedidasCorporalesControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testNewMedidasCorporales() throws Exception {
        MedidasCorporales medidasCorporales = new MedidasCorporales();
        medidasCorporales.setRegistro(new Date());
        medidasCorporales.setHombros(40.0);
        medidasCorporales.setEspalda(90.0);
        medidasCorporales.setCintura(75.0);
        medidasCorporales.setGemelo(30.0);
        medidasCorporales.setBiceps(35.0);
        medidasCorporales.setMuslo(60.0);
        medidasCorporales.setPecho(95.0);

        mockMvc.perform(post("/api/MedidasCorporales")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "  \"registro\": \"2024-07-14T12:00:00.000Z\",\n" +
                                "  \"hombros\": 40.0,\n" +
                                "  \"espalda\": 90.0,\n" +
                                "  \"cintura\": 75.0,\n" +
                                "  \"gemelo\": 30.0,\n" +
                                "  \"biceps\": 35.0,\n" +
                                "  \"muslo\": 60.0,\n" +
                                "  \"pecho\": 95.0\n" +
                                "}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetMedidasCorporales() throws Exception {
        mockMvc.perform(get("/api/MedidasCorporales")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetById() throws Exception {
        mockMvc.perform(get("/api/MedidasCorporales/{id}", 1L)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    /*
    @Test
    public void testUpdateMedidasCorporales() throws Exception {
        MedidasCorporales medidasCorporales = new MedidasCorporales();
        medidasCorporales.setIdMedidasCorporales(1L); // ID válido
        medidasCorporales.setRegistro(new Date()); // Ajusta según tu formato de fecha esperado
        medidasCorporales.setHombros(42.0);
        medidasCorporales.setEspalda(92.0);
        medidasCorporales.setCintura(77.0);
        medidasCorporales.setGemelo(32.0);
        medidasCorporales.setBiceps(37.0);
        medidasCorporales.setMuslo(62.0);
        medidasCorporales.setPecho(97.0);

        mockMvc.perform(put("/api/MedidasCorporales")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "  \"idMedidasCorporales\": 1,\n" +
                                "  \"registro\": \"2024-07-14T12:00:00.000Z\",\n" +
                                "  \"hombros\": 42.0,\n" +
                                "  \"espalda\": 92.0,\n" +
                                "  \"cintura\": 77.0,\n" +
                                "  \"gemelo\": 32.0,\n" +
                                "  \"biceps\": 37.0,\n" +
                                "  \"muslo\": 62.0,\n" +
                                "  \"pecho\": 97.0\n" +
                                "}"))
                .andExpect(status().isOk());
    }

     */


    @Test
    public void testDeleteMedidasCorporalesById() throws Exception {
        mockMvc.perform(delete("/api/MedidasCorporales/{id}", 1L))
                .andExpect(status().isOk());
    }
}
