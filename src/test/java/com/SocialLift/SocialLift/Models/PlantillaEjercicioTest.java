package com.SocialLift.SocialLift.Models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;

import java.util.List;

public class PlantillaEjercicioTest {

    private PlantillaEjercicio plantillaEjercicio;
    private Usuario usuario;
    private PlantillaRutina plantillaRutina;

    @BeforeEach
    public void setUp() {
        plantillaEjercicio = new PlantillaEjercicio();
        usuario = Mockito.mock(Usuario.class);
        plantillaRutina = Mockito.mock(PlantillaRutina.class);
    }

    @Test
    public void testSetAndGetIdPlantillaEjercicio() {
        Long id = 1L;
        plantillaEjercicio.setIdPlantillaEjercicio(id);
        assertEquals(id, plantillaEjercicio.getIdPlantillaEjercicio());
    }

    @Test
    public void testSetAndGetImagen() {
        String imagen = "imagen.jpg";
        plantillaEjercicio.setImagen(imagen);
        assertEquals(imagen, plantillaEjercicio.getImagen());
    }

    @Test
    public void testSetAndGetNombre() {
        String nombre = "Ejercicio 1";
        plantillaEjercicio.setNombre(nombre);
        assertEquals(nombre, plantillaEjercicio.getNombre());
    }

    @Test
    public void testSetAndGetDescripcion() {
        String descripcion = "Descripción del ejercicio";
        plantillaEjercicio.setDescripcion(descripcion);
        assertEquals(descripcion, plantillaEjercicio.getDescripcion());
    }

    @Test
    public void testSetAndGetTipo() {
        String tipo = "Cardio";
        plantillaEjercicio.setTipo(tipo);
        assertEquals(tipo, plantillaEjercicio.getTipo());
    }

    @Test
    public void testSetAndGetPlantillaRutina() {
        List<PlantillaRutina> plantillas = List.of(plantillaRutina);
        plantillaEjercicio.setPlantillaRutina(plantillas);
        assertEquals(plantillas, plantillaEjercicio.getPlantillaRutina());
    }

    @Test
    public void testSetAndGetUsuario() {
        plantillaEjercicio.setUsuario(usuario);
        assertEquals(usuario, plantillaEjercicio.getUsuario());
    }

    @Test
    public void testNoArgsConstructor() {
        PlantillaEjercicio newPlantillaEjercicio = new PlantillaEjercicio();
        assertNotNull(newPlantillaEjercicio);
        assertNull(newPlantillaEjercicio.getIdPlantillaEjercicio());
        assertNull(newPlantillaEjercicio.getImagen());
        assertNull(newPlantillaEjercicio.getNombre());
        assertNull(newPlantillaEjercicio.getDescripcion());
        assertNull(newPlantillaEjercicio.getTipo());
        assertNull(newPlantillaEjercicio.getPlantillaRutina());
        assertNull(newPlantillaEjercicio.getUsuario());
    }

    @Test
    public void testAllArgsConstructor() {
        Long id = 1L;
        String imagen = "imagen.jpg";
        String nombre = "Ejercicio 1";
        String descripcion = "Descripción del ejercicio";
        String tipo = "Cardio";
        List<PlantillaRutina> plantillas = List.of(plantillaRutina);
        PlantillaEjercicio newPlantillaEjercicio = new PlantillaEjercicio(id, imagen, nombre, descripcion, tipo, plantillas, usuario);

        assertEquals(id, newPlantillaEjercicio.getIdPlantillaEjercicio());
        assertEquals(imagen, newPlantillaEjercicio.getImagen());
        assertEquals(nombre, newPlantillaEjercicio.getNombre());
        assertEquals(descripcion, newPlantillaEjercicio.getDescripcion());
        assertEquals(tipo, newPlantillaEjercicio.getTipo());
        assertEquals(plantillas, newPlantillaEjercicio.getPlantillaRutina());
        assertEquals(usuario, newPlantillaEjercicio.getUsuario());
    }
}
