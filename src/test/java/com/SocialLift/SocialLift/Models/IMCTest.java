package com.SocialLift.SocialLift.Models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;

import java.util.Date;

public class IMCTest {

    private IMC imc;
    private Usuario usuario;

    @BeforeEach
    public void setUp() {
        imc = new IMC();
        usuario = Mockito.mock(Usuario.class);
    }

    @Test
    public void testSetAndGetIdIMC() {
        Long id = 1L;
        imc.setIdIMC(id);
        assertEquals(id, imc.getIdIMC());
    }

    @Test
    public void testSetAndGetRegistro() {
        Date date = new Date();
        imc.setRegistro(date);
        assertEquals(date, imc.getRegistro());
    }

    @Test
    public void testSetAndGetValue() {
        Double value = 25.0;
        imc.setValue(value);
        assertEquals(value, imc.getValue());
    }

    @Test
    public void testSetAndGetUsuario() {
        imc.setUsuario(usuario);
        assertEquals(usuario, imc.getUsuario());
    }

    @Test
    public void testNoArgsConstructor() {
        IMC newIMC = new IMC();
        assertNotNull(newIMC);
        assertNull(newIMC.getIdIMC());
        assertNull(newIMC.getRegistro());
        assertNull(newIMC.getValue());
        assertNull(newIMC.getUsuario());
    }

    @Test
    public void testAllArgsConstructor() {
        Long id = 1L;
        Date date = new Date();
        Double value = 25.0;
        IMC newIMC = new IMC(id, date, value, usuario);

        assertEquals(id, newIMC.getIdIMC());
        assertEquals(date, newIMC.getRegistro());
        assertEquals(value, newIMC.getValue());
        assertEquals(usuario, newIMC.getUsuario());
    }
}
