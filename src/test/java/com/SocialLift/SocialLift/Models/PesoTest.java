package com.SocialLift.SocialLift.Models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;

import java.util.Date;

public class PesoTest {

    private Peso peso;
    private Usuario usuario;

    @BeforeEach
    public void setUp() {
        peso = new Peso();
        usuario = Mockito.mock(Usuario.class);
    }

    @Test
    public void testSetAndGetIdPeso() {
        Long id = 1L;
        peso.setIdPeso(id);
        assertEquals(id, peso.getIdPeso());
    }

    @Test
    public void testSetAndGetRegistro() {
        Date date = new Date();
        peso.setRegistro(date);
        assertEquals(date, peso.getRegistro());
    }

    @Test
    public void testSetAndGetValue() {
        Double value = 70.0;
        peso.setValue(value);
        assertEquals(value, peso.getValue());
    }

    @Test
    public void testSetAndGetUsuario() {
        peso.setUsuario(usuario);
        assertEquals(usuario, peso.getUsuario());
    }

    @Test
    public void testNoArgsConstructor() {
        Peso newPeso = new Peso();
        assertNotNull(newPeso);
        assertNull(newPeso.getIdPeso());
        assertNull(newPeso.getRegistro());
        assertNull(newPeso.getValue());
        assertNull(newPeso.getUsuario());
    }

    @Test
    public void testAllArgsConstructor() {
        Long id = 1L;
        Date date = new Date();
        Double value = 70.0;
        Peso newPeso = new Peso(id, date, value, usuario);

        assertEquals(id, newPeso.getIdPeso());
        assertEquals(date, newPeso.getRegistro());
        assertEquals(value, newPeso.getValue());
        assertEquals(usuario, newPeso.getUsuario());
    }
}
