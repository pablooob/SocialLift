package com.SocialLift.SocialLift.Models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;

public class SerieTest {

    private Serie serie;
    private Ejercicio ejercicio;

    @BeforeEach
    public void setUp() {
        serie = new Serie();
        ejercicio = Mockito.mock(Ejercicio.class);
    }

    @Test
    public void testSetAndGetIdSerie() {
        Long id = 1L;
        serie.setIdSerie(id);
        assertEquals(id, serie.getIdSerie());
    }

    @Test
    public void testSetAndGetNumeroSerie() {
        int numeroSerie = 3;
        serie.setNumeroSerie(numeroSerie);
        assertEquals(numeroSerie, serie.getNumeroSerie());
    }

    @Test
    public void testSetAndGetPeso() {
        double peso = 20.5;
        serie.setPeso(peso);
        assertEquals(peso, serie.getPeso());
    }

    @Test
    public void testSetAndGetTipoPeso() {
        String tipoPeso = "kg";
        serie.setTipoPeso(tipoPeso);
        assertEquals(tipoPeso, serie.getTipoPeso());
    }

    @Test
    public void testSetAndGetNumeroRepeticiones() {
        int numeroRepeticiones = 10;
        serie.setNumeroRepeticiones(numeroRepeticiones);
        assertEquals(numeroRepeticiones, serie.getNumeroRepeticiones());
    }

    @Test
    public void testSetAndGetEjercicio() {
        serie.setEjercicio(ejercicio);
        assertEquals(ejercicio, serie.getEjercicio());
    }

    @Test
    public void testSetAndGetVideo() {
        String video = "video_url";
        serie.setVideo(video);
        assertEquals(video, serie.getVideo());
    }

    @Test
    public void testNoArgsConstructor() {
        Serie newSerie = new Serie();
        assertNotNull(newSerie);
        assertEquals(0, newSerie.getNumeroSerie());
        assertEquals(0.0, newSerie.getPeso());
        assertNull(newSerie.getTipoPeso());
        assertEquals(0, newSerie.getNumeroRepeticiones());
        assertNull(newSerie.getEjercicio());
        assertNull(newSerie.getVideo());
    }

    @Test
    public void testAllArgsConstructor() {
        Long id = 1L;
        int numeroSerie = 3;
        double peso = 20.5;
        String tipoPeso = "kg";
        int numeroRepeticiones = 10;
        String video = "video_url";
        Serie newSerie = new Serie(id, numeroSerie, peso, tipoPeso, numeroRepeticiones, ejercicio, video);

        assertEquals(id, newSerie.getIdSerie());
        assertEquals(numeroSerie, newSerie.getNumeroSerie());
        assertEquals(peso, newSerie.getPeso());
        assertEquals(tipoPeso, newSerie.getTipoPeso());
        assertEquals(numeroRepeticiones, newSerie.getNumeroRepeticiones());
        assertEquals(ejercicio, newSerie.getEjercicio());
        assertEquals(video, newSerie.getVideo());
    }
}
