package com.SocialLift.SocialLift.Integration;

import com.SocialLift.SocialLift.Models.Ejercicio;
import com.SocialLift.SocialLift.Models.Serie;
import com.SocialLift.SocialLift.Services.EjercicioService;
import com.SocialLift.SocialLift.Services.SerieService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class SerieControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SerieService serieService;

    @Autowired
    private EjercicioService ejercicioService;

    @Test
    public void testNewSerie() throws Exception {
        Ejercicio ejercicio = new Ejercicio();
        ejercicio = ejercicioService.NewEjercicio(ejercicio);

        Serie serie = new Serie();
        serie.setNumeroSerie(1);
        serie.setPeso(50.0);
        serie.setTipoPeso("kg");
        serie.setNumeroRepeticiones(10);
        serie.setEjercicio(ejercicio);
        serie.setVideo("http://example.com/video1");

        mockMvc.perform(post("/api/serie")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(serie)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idSerie", notNullValue()))
                .andExpect(jsonPath("$.numeroSerie", is(1)))
                .andExpect(jsonPath("$.peso", is(50.0)))
                .andExpect(jsonPath("$.tipoPeso", is("kg")))
                .andExpect(jsonPath("$.numeroRepeticiones", is(10)))
                .andExpect(jsonPath("$.ejercicio.idEjercicio", is(ejercicio.getIdEjercicio().intValue())))
                .andExpect(jsonPath("$.video", is("http://example.com/video1")));
    }

    /*
    @Test
    public void testGetSeries() throws Exception {
        Ejercicio ejercicio = new Ejercicio();
        ejercicio = ejercicioService.NewEjercicio(ejercicio);

        Serie serie1 = new Serie();
        serie1.setNumeroSerie(1);
        serie1.setPeso(60.0);
        serie1.setTipoPeso("kg");
        serie1.setNumeroRepeticiones(12);
        serie1.setEjercicio(ejercicio);
        serie1.setVideo("http://example.com/video2");
        serieService.NewSerie(serie1);

        Serie serie2 = new Serie();
        serie2.setNumeroSerie(2);
        serie2.setPeso(70.0);
        serie2.setTipoPeso("kg");
        serie2.setNumeroRepeticiones(8);
        serie2.setEjercicio(ejercicio);
        serie2.setVideo("http://example.com/video3");
        serieService.NewSerie(serie2);

        mockMvc.perform(get("/api/serie")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
                .andExpect(jsonPath("$[0].numeroSerie", is(1)))
                .andExpect(jsonPath("$[1].numeroSerie", is(2)));
    }

     */

    @Test
    public void testGetById() throws Exception {
        Ejercicio ejercicio = new Ejercicio();
        ejercicio = ejercicioService.NewEjercicio(ejercicio);

        Serie serie = new Serie();
        serie.setNumeroSerie(3);
        serie.setPeso(55.0);
        serie.setTipoPeso("kg");
        serie.setNumeroRepeticiones(15);
        serie.setEjercicio(ejercicio);
        serie.setVideo("http://example.com/video4");
        serie = serieService.NewSerie(serie);

        mockMvc.perform(get("/api/serie/{id}", serie.getIdSerie())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.idSerie").value(serie.getIdSerie()))
                .andExpect(jsonPath("$.numeroSerie", is(3)))
                .andExpect(jsonPath("$.peso", is(55.0)))
                .andExpect(jsonPath("$.tipoPeso", is("kg")))
                .andExpect(jsonPath("$.numeroRepeticiones", is(15)))
                .andExpect(jsonPath("$.ejercicio.idEjercicio", is(ejercicio.getIdEjercicio().intValue())))
                .andExpect(jsonPath("$.video", is("http://example.com/video4")));
    }

    @Test
    public void testUpdateSerie() throws Exception {
        Ejercicio ejercicio = new Ejercicio();
        ejercicio = ejercicioService.NewEjercicio(ejercicio);

        Serie serie = new Serie();
        serie.setNumeroSerie(4);
        serie.setPeso(65.0);
        serie.setTipoPeso("kg");
        serie.setNumeroRepeticiones(20);
        serie.setEjercicio(ejercicio);
        serie.setVideo("http://example.com/video5");
        serie = serieService.NewSerie(serie);

        serie.setNumeroRepeticiones(25);

        mockMvc.perform(put("/api/serie")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(serie)))
                .andExpect(status().isOk());

        Serie updatedSerie = serieService.GetSerieById(serie.getIdSerie()).orElse(null);
        assert updatedSerie != null;
        assertEquals(serie.getNumeroRepeticiones(), updatedSerie.getNumeroRepeticiones());
    }

    @Test
    public void testDeleteSerieById() throws Exception {
        Ejercicio ejercicio = new Ejercicio();
        ejercicio = ejercicioService.NewEjercicio(ejercicio);

        Serie serie = new Serie();
        serie.setNumeroSerie(5);
        serie.setPeso(75.0);
        serie.setTipoPeso("kg");
        serie.setNumeroRepeticiones(18);
        serie.setEjercicio(ejercicio);
        serie.setVideo("http://example.com/video6");
        serie = serieService.NewSerie(serie);

        mockMvc.perform(delete("/api/serie/{id}", serie.getIdSerie()))
                .andExpect(status().isOk());

        Serie deletedSerie = serieService.GetSerieById(serie.getIdSerie()).orElse(null);
        assertNull(deletedSerie);
    }
}
