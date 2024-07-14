package com.SocialLift.SocialLift.Models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;

import java.util.Date;

public class MedidasCorporalesTest {

    private MedidasCorporales medidasCorporales;
    private Usuario usuario;

    @BeforeEach
    public void setUp() {
        medidasCorporales = new MedidasCorporales();
        usuario = Mockito.mock(Usuario.class);
    }

    @Test
    public void testSetAndGetIdMedidasCorporales() {
        Long id = 1L;
        medidasCorporales.setIdMedidasCorporales(id);
        assertEquals(id, medidasCorporales.getIdMedidasCorporales());
    }

    @Test
    public void testSetAndGetRegistro() {
        Date date = new Date();
        medidasCorporales.setRegistro(date);
        assertEquals(date, medidasCorporales.getRegistro());
    }

    @Test
    public void testSetAndGetHombros() {
        Double hombros = 40.0;
        medidasCorporales.setHombros(hombros);
        assertEquals(hombros, medidasCorporales.getHombros());
    }

    @Test
    public void testSetAndGetEspalda() {
        Double espalda = 50.0;
        medidasCorporales.setEspalda(espalda);
        assertEquals(espalda, medidasCorporales.getEspalda());
    }

    @Test
    public void testSetAndGetCintura() {
        Double cintura = 30.0;
        medidasCorporales.setCintura(cintura);
        assertEquals(cintura, medidasCorporales.getCintura());
    }

    @Test
    public void testSetAndGetGemelo() {
        Double gemelo = 20.0;
        medidasCorporales.setGemelo(gemelo);
        assertEquals(gemelo, medidasCorporales.getGemelo());
    }

    @Test
    public void testSetAndGetBiceps() {
        Double biceps = 15.0;
        medidasCorporales.setBiceps(biceps);
        assertEquals(biceps, medidasCorporales.getBiceps());
    }

    @Test
    public void testSetAndGetMuslo() {
        Double muslo = 25.0;
        medidasCorporales.setMuslo(muslo);
        assertEquals(muslo, medidasCorporales.getMuslo());
    }

    @Test
    public void testSetAndGetPecho() {
        Double pecho = 35.0;
        medidasCorporales.setPecho(pecho);
        assertEquals(pecho, medidasCorporales.getPecho());
    }

    @Test
    public void testSetAndGetUsuario() {
        medidasCorporales.setUsuario(usuario);
        assertEquals(usuario, medidasCorporales.getUsuario());
    }

    @Test
    public void testNoArgsConstructor() {
        MedidasCorporales newMedidasCorporales = new MedidasCorporales();
        assertNotNull(newMedidasCorporales);
        assertNull(newMedidasCorporales.getIdMedidasCorporales());
        assertNull(newMedidasCorporales.getRegistro());
        assertNull(newMedidasCorporales.getHombros());
        assertNull(newMedidasCorporales.getEspalda());
        assertNull(newMedidasCorporales.getCintura());
        assertNull(newMedidasCorporales.getGemelo());
        assertNull(newMedidasCorporales.getBiceps());
        assertNull(newMedidasCorporales.getMuslo());
        assertNull(newMedidasCorporales.getPecho());
        assertNull(newMedidasCorporales.getUsuario());
    }

    @Test
    public void testAllArgsConstructor() {
        Long id = 1L;
        Date date = new Date();
        Double hombros = 40.0;
        Double espalda = 50.0;
        Double cintura = 30.0;
        Double gemelo = 20.0;
        Double biceps = 15.0;
        Double muslo = 25.0;
        Double pecho = 35.0;
        MedidasCorporales newMedidasCorporales = new MedidasCorporales(id, date, hombros, espalda, cintura, gemelo, biceps, muslo, pecho, usuario);

        assertEquals(id, newMedidasCorporales.getIdMedidasCorporales());
        assertEquals(date, newMedidasCorporales.getRegistro());
        assertEquals(hombros, newMedidasCorporales.getHombros());
        assertEquals(espalda, newMedidasCorporales.getEspalda());
        assertEquals(cintura, newMedidasCorporales.getCintura());
        assertEquals(gemelo, newMedidasCorporales.getGemelo());
        assertEquals(biceps, newMedidasCorporales.getBiceps());
        assertEquals(muslo, newMedidasCorporales.getMuslo());
        assertEquals(pecho, newMedidasCorporales.getPecho());
        assertEquals(usuario, newMedidasCorporales.getUsuario());
    }
}
