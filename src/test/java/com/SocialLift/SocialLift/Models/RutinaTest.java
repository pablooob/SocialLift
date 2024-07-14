package com.SocialLift.SocialLift.Models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;

import java.util.Date;
import java.util.List;

public class RutinaTest {

    private Rutina rutina;
    private Usuario usuario;
    private ColorRutina color;
    private PlantillaRutina plantillaRutina;
    private Ejercicio ejercicio;

    @BeforeEach
    public void setUp() {
        rutina = new Rutina();
        usuario = Mockito.mock(Usuario.class);
        color = Mockito.mock(ColorRutina.class);
        plantillaRutina = Mockito.mock(PlantillaRutina.class);
        ejercicio = Mockito.mock(Ejercicio.class);
    }

    @Test
    public void testSetAndGetIdRutina() {
        Long id = 1L;
        rutina.setIdRutina(id);
        assertEquals(id, rutina.getIdRutina());
    }

    @Test
    public void testSetAndGetNombre() {
        String nombre = "Rutina 1";
        rutina.setNombre(nombre);
        assertEquals(nombre, rutina.getNombre());
    }

    @Test
    public void testSetAndGetTipo() {
        String tipo = "Cardio";
        rutina.setTipo(tipo);
        assertEquals(tipo, rutina.getTipo());
    }

    @Test
    public void testSetAndGetDescripcion() {
        String descripcion = "Descripción de la rutina";
        rutina.setDescripcion(descripcion);
        assertEquals(descripcion, rutina.getDescripcion());
    }

    @Test
    public void testSetAndGetIsPublic() {
        Boolean isPublic = true;
        rutina.setIsPublic(isPublic);
        assertEquals(isPublic, rutina.getIsPublic());
    }

    @Test
    public void testSetAndGetNombreGimnasio() {
        String nombreGimnasio = "Gimnasio XYZ";
        rutina.setNombreGimnasio(nombreGimnasio);
        assertEquals(nombreGimnasio, rutina.getNombreGimnasio());
    }

    @Test
    public void testSetAndGetTiempoRutinaMinutos() {
        Long tiempo = 45L;
        rutina.setTiempoRutinaMinutos(tiempo);
        assertEquals(tiempo, rutina.getTiempoRutinaMinutos());
    }

    @Test
    public void testSetAndGetFecha() {
        Date fecha = new Date();
        rutina.setFecha(fecha);
        assertEquals(fecha, rutina.getFecha());
    }

    @Test
    public void testSetAndGetColor() {
        rutina.setColor(color);
        assertEquals(color, rutina.getColor());
    }

    @Test
    public void testSetAndGetPlantillaRutina() {
        rutina.setPlantillaRutina(plantillaRutina);
        assertEquals(plantillaRutina, rutina.getPlantillaRutina());
    }

    @Test
    public void testSetAndGetEjercicios() {
        List<Ejercicio> ejercicios = List.of(ejercicio);
        rutina.setEjercicios(ejercicios);
        assertEquals(ejercicios, rutina.getEjercicios());
    }

    @Test
    public void testSetAndGetUsuario() {
        rutina.setUsuario(usuario);
        assertEquals(usuario, rutina.getUsuario());
    }

    @Test
    public void testNoArgsConstructor() {
        Rutina newRutina = new Rutina();
        assertNotNull(newRutina);
        assertNull(newRutina.getIdRutina());
        assertNull(newRutina.getNombre());
        assertNull(newRutina.getTipo());
        assertNull(newRutina.getDescripcion());
        assertNull(newRutina.getIsPublic());
        assertNull(newRutina.getNombreGimnasio());
        assertNull(newRutina.getTiempoRutinaMinutos());
        assertNull(newRutina.getFecha());
        assertNull(newRutina.getColor());
        assertNull(newRutina.getPlantillaRutina());
        assertNull(newRutina.getEjercicios());
        assertNull(newRutina.getUsuario());
    }

    @Test
    public void testAllArgsConstructor() {
        Long id = 1L;
        String nombre = "Rutina 1";
        String tipo = "Cardio";
        String descripcion = "Descripción de la rutina";
        Boolean isPublic = true;
        String nombreGimnasio = "Gimnasio XYZ";
        Long tiempo = 45L;
        Date fecha = new Date();
        List<Ejercicio> ejercicios = List.of(ejercicio);
        Rutina newRutina = new Rutina(id, nombre, tipo, descripcion, isPublic, nombreGimnasio, tiempo, fecha, color, plantillaRutina, ejercicios, usuario);

        assertEquals(id, newRutina.getIdRutina());
        assertEquals(nombre, newRutina.getNombre());
        assertEquals(tipo, newRutina.getTipo());
        assertEquals(descripcion, newRutina.getDescripcion());
        assertEquals(isPublic, newRutina.getIsPublic());
        assertEquals(nombreGimnasio, newRutina.getNombreGimnasio());
        assertEquals(tiempo, newRutina.getTiempoRutinaMinutos());
        assertEquals(fecha, newRutina.getFecha());
        assertEquals(color, newRutina.getColor());
        assertEquals(plantillaRutina, newRutina.getPlantillaRutina());
        assertEquals(ejercicios, newRutina.getEjercicios());
        assertEquals(usuario, newRutina.getUsuario());
    }
}
