package com.SocialLift.SocialLift.Integration;
import com.SocialLift.SocialLift.Models.Ejercicio;
import com.SocialLift.SocialLift.Models.PlantillaEjercicio;
import com.SocialLift.SocialLift.Models.Serie;
import com.SocialLift.SocialLift.Services.EjercicioService;
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

@SpringBootTest
@AutoConfigureMockMvc
@Transactional // Asegura que las transacciones sean rollback después de cada prueba
public class EjercicioControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper; // ObjectMapper para convertir objetos a JSON y viceversa

    @Autowired
    private EjercicioService ejercicioService;

    @BeforeEach
    public void setUp() {
        // Aquí puedes inicializar datos de prueba si es necesario
    }

    @Test
    public void testCrearNuevoEjercicio() throws Exception {
        Ejercicio ejercicio = new Ejercicio();

        mockMvc.perform(post("/api/ejercicio/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ejercicio)))
                .andExpect(status().isOk());
    }

    /*
    @Test
    public void testGetEjercicios() throws Exception {
        mockMvc.perform(get("/api/ejercicio/all")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }

     */

    @Test
    public void testGetEjercicioPorId() throws Exception {
        Ejercicio ejercicio = new Ejercicio();
        Ejercicio savedEjercicio = ejercicioService.NewEjercicio(ejercicio);

        mockMvc.perform(get("/api/ejercicio/{id}", savedEjercicio.getIdEjercicio())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.idEjercicio").value(savedEjercicio.getIdEjercicio().intValue()));
    }

    @Test
    public void testActualizarEjercicio() throws Exception {
        Ejercicio ejercicio = new Ejercicio();
        Ejercicio savedEjercicio = ejercicioService.NewEjercicio(ejercicio);
        savedEjercicio.setPlantillaEjercicio(new PlantillaEjercicio());

        mockMvc.perform(put("/api/ejercicio/{id}", savedEjercicio.getIdEjercicio())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(savedEjercicio)))
                .andExpect(status().isOk())
                .andExpect(content().string("Ejercicio actualizado exitosamente"));
    }


    @Test
    public void testEliminarEjercicio() throws Exception {
        Ejercicio ejercicio = new Ejercicio();
        Ejercicio savedEjercicio = ejercicioService.NewEjercicio(ejercicio);

        mockMvc.perform(delete("/api/ejercicio/{id}", savedEjercicio.getIdEjercicio()))
                .andExpect(status().isOk())
                .andExpect(content().string("Ejercicio eliminado exitosamente"));
    }
}
