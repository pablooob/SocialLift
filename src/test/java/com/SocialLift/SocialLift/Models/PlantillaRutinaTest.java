package com.SocialLift.SocialLift.Models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;

import java.util.List;

public class PlantillaRutinaTest {

    private PlantillaRutina plantillaRutina;
    private Usuario usuario;
    private Rutina rutina;
    private PlantillaEjercicio plantillaEjercicio;
    private ColorRutina color;

    @BeforeEach
    public void setUp() {
        plantillaRutina = new PlantillaRutina();
        usuario = Mockito.mock(Usuario.class);
        rutina = Mockito.mock(Rutina.class);
        plantillaEjercicio = Mockito.mock(PlantillaEjercicio.class);
        color = Mockito.mock(ColorRutina.class);
    }

    @Test
    public void testSetAndGetIdPlantillaRutina() {
        Long id = 1L;
        plantillaRutina.setIdPlantillaRutina(id);
        assertEquals(id, plantillaRutina.getIdPlantillaRutina());
    }

    @Test
    public void testSetAndGetNombre() {
        String nombre = "Rutina 1";
        plantillaRutina.setNombre(nombre);
        assertEquals(nombre, plantillaRutina.getNombre());
    }

    @Test
    public void testSetAndGetImagen() {
        String imagen = "imagen.jpg";
        plantillaRutina.setImagen(imagen);
        assertEquals(imagen, plantillaRutina.getImagen());
    }

    @Test
    public void testSetAndGetDescripcion() {
        String descripcion = "Descripción de la rutina";
        plantillaRutina.setDescripcion(descripcion);
        assertEquals(descripcion, plantillaRutina.getDescripcion());
    }

    @Test
    public void testSetAndGetTiempoRutinaMinutos() {
        Long tiempo = 45L;
        plantillaRutina.setTiempoRutinaMinutos(tiempo);
        assertEquals(tiempo, plantillaRutina.getTiempoRutinaMinutos());
    }

    @Test
    public void testSetAndGetTipo() {
        String tipo = "Cardio";
        plantillaRutina.setTipo(tipo);
        assertEquals(tipo, plantillaRutina.getTipo());
    }

    @Test
    public void testSetAndGetRutinas() {
        List<Rutina> rutinas = List.of(rutina);
        plantillaRutina.setRutinas(rutinas);
        assertEquals(rutinas, plantillaRutina.getRutinas());
    }

    @Test
    public void testSetAndGetPlantillaEjercicios() {
        List<PlantillaEjercicio> ejercicios = List.of(plantillaEjercicio);
        plantillaRutina.setPlantillaEjercicios(ejercicios);
        assertEquals(ejercicios, plantillaRutina.getPlantillaEjercicios());
    }

    @Test
    public void testSetAndGetUsuarioGuardados() {
        List<Usuario> usuarios = List.of(usuario);
        plantillaRutina.setUsuarioGuardados(usuarios);
        assertEquals(usuarios, plantillaRutina.getUsuarioGuardados());
    }

    @Test
    public void testSetAndGetUsuario() {
        plantillaRutina.setUsuario(usuario);
        assertEquals(usuario, plantillaRutina.getUsuario());
    }

    @Test
    public void testSetAndGetColor() {
        plantillaRutina.setColor(color);
        assertEquals(color, plantillaRutina.getColor());
    }

    @Test
    public void testNoArgsConstructor() {
        PlantillaRutina newPlantillaRutina = new PlantillaRutina();
        assertNotNull(newPlantillaRutina);
        assertNull(newPlantillaRutina.getIdPlantillaRutina());
        assertNull(newPlantillaRutina.getNombre());
        assertNull(newPlantillaRutina.getImagen());
        assertNull(newPlantillaRutina.getDescripcion());
        assertNull(newPlantillaRutina.getTiempoRutinaMinutos());
        assertNull(newPlantillaRutina.getTipo());
        assertNull(newPlantillaRutina.getRutinas());
        assertNull(newPlantillaRutina.getPlantillaEjercicios());
        assertNull(newPlantillaRutina.getUsuarioGuardados());
        assertNull(newPlantillaRutina.getUsuario());
        assertNull(newPlantillaRutina.getColor());
    }

    @Test
    public void testAllArgsConstructor() {
        Long id = 1L;
        String nombre = "Rutina 1";
        String imagen = "imagen.jpg";
        String descripcion = "Descripción de la rutina";
        Long tiempo = 45L;
        String tipo = "Cardio";
        List<Rutina> rutinas = List.of(rutina);
        List<PlantillaEjercicio> ejercicios = List.of(plantillaEjercicio);
        List<Usuario> usuarios = List.of(usuario);
        PlantillaRutina newPlantillaRutina = new PlantillaRutina(id, nombre, imagen, descripcion, tiempo, tipo, rutinas, ejercicios, usuarios, usuario, color);

        assertEquals(id, newPlantillaRutina.getIdPlantillaRutina());
        assertEquals(nombre, newPlantillaRutina.getNombre());
        assertEquals(imagen, newPlantillaRutina.getImagen());
        assertEquals(descripcion, newPlantillaRutina.getDescripcion());
        assertEquals(tiempo, newPlantillaRutina.getTiempoRutinaMinutos());
        assertEquals(tipo, newPlantillaRutina.getTipo());
        assertEquals(rutinas, newPlantillaRutina.getRutinas());
        assertEquals(ejercicios, newPlantillaRutina.getPlantillaEjercicios());
        assertEquals(usuarios, newPlantillaRutina.getUsuarioGuardados());
        assertEquals(usuario, newPlantillaRutina.getUsuario());
        assertEquals(color, newPlantillaRutina.getColor());
    }
}
