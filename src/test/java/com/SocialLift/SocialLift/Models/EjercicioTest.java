package com.SocialLift.SocialLift.Models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class EjercicioTest {

    private Ejercicio ejercicio;
    private Rutina rutina;
    private List<Serie> series;
    private PlantillaEjercicio plantillaEjercicio;

    @BeforeEach
    public void setUp() {
        ejercicio = new Ejercicio();
        rutina = Mockito.mock(Rutina.class);
        series = new ArrayList<>();
        plantillaEjercicio = Mockito.mock(PlantillaEjercicio.class);
    }

    @Test
    public void testSetAndGetIdEjercicio() {
        Long id = 1L;
        ejercicio.setId(id);
        assertEquals(id, ejercicio.getIdEjercicio());
    }

    @Test
    public void testSetAndGetRutina() {
        ejercicio.setRutina(rutina);
        assertEquals(rutina, ejercicio.getRutina());
    }

    @Test
    public void testSetAndGetSeries() {
        Serie serie1 = Mockito.mock(Serie.class);
        Serie serie2 = Mockito.mock(Serie.class);
        series.add(serie1);
        series.add(serie2);
        ejercicio.setSeries(series);
        assertEquals(2, ejercicio.getSeries().size());
        assertTrue(ejercicio.getSeries().contains(serie1));
        assertTrue(ejercicio.getSeries().contains(serie2));
    }

    @Test
    public void testSetAndGetPlantillaEjercicio() {
        ejercicio.setPlantillaEjercicio(plantillaEjercicio);
        assertEquals(plantillaEjercicio, ejercicio.getPlantillaEjercicio());
    }

    @Test
    public void testNoArgsConstructor() {
        Ejercicio newEjercicio = new Ejercicio();
        assertNotNull(newEjercicio);
        assertNull(newEjercicio.getIdEjercicio());
        assertNull(newEjercicio.getRutina());
        assertNull(newEjercicio.getSeries());
        assertNull(newEjercicio.getPlantillaEjercicio());
    }

    @Test
    public void testAllArgsConstructor() {
        Long id = 1L;
        Serie serie1 = Mockito.mock(Serie.class);
        Serie serie2 = Mockito.mock(Serie.class);
        List<Serie> series = new ArrayList<>();
        series.add(serie1);
        series.add(serie2);
        Ejercicio newEjercicio = new Ejercicio(id, rutina, series, plantillaEjercicio);

        assertEquals(id, newEjercicio.getIdEjercicio());
        assertEquals(rutina, newEjercicio.getRutina());
        assertEquals(2, newEjercicio.getSeries().size());
        assertTrue(newEjercicio.getSeries().contains(serie1));
        assertTrue(newEjercicio.getSeries().contains(serie2));
        assertEquals(plantillaEjercicio, newEjercicio.getPlantillaEjercicio());
    }
}
