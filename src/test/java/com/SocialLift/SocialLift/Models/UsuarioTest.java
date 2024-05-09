package com.SocialLift.SocialLift.Models;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class UsuarioTest {

    @Test
    public void testConstructorAndGetters() {
        // Arrange
        Long idUsuario = 1L;
        String imagen = "imagen.jpg";
        Double alturaActual = 170.0;
        Double pesoActual = 70.0;
        Double hombroActual = 40.0;
        Double espaldaActual = 90.0;
        Double cinturaActual = 75.0;
        Double gemeloActual = 35.0;
        Double bicepsActual = 30.0;
        Double musloActual = 60.0;
        Double pechoActual = 95.0;
        String nombre = "John";
        String apellidos = "Doe";
        String correo = "john@example.com";
        String nombreUsuario = "john";
        String contrasenya = "password";

        // Act
        Usuario usuario = Usuario.builder()
                .idUsuario(idUsuario)
                .imagen(imagen)
                .alturaActual(alturaActual)
                .pesoActual(pesoActual)
                .hombroActual(hombroActual)
                .espaldaActual(espaldaActual)
                .cinturaActual(cinturaActual)
                .gemeloActual(gemeloActual)
                .bicepsActual(bicepsActual)
                .musloActual(musloActual)
                .pechoActual(pechoActual)
                .nombre(nombre)
                .apellidos(apellidos)
                .correo(correo)
                .nombreUsuario(nombreUsuario)
                .contrasenya(contrasenya)
                .build();

        // Assert
        assertNotNull(usuario);
        assertEquals(idUsuario, usuario.getIdUsuario());
        assertEquals(imagen, usuario.getImagen());
        assertEquals(alturaActual, usuario.getAlturaActual());
        assertEquals(pesoActual, usuario.getPesoActual());
        assertEquals(hombroActual, usuario.getHombroActual());
        assertEquals(espaldaActual, usuario.getEspaldaActual());
        assertEquals(cinturaActual, usuario.getCinturaActual());
        assertEquals(gemeloActual, usuario.getGemeloActual());
        assertEquals(bicepsActual, usuario.getBicepsActual());
        assertEquals(musloActual, usuario.getMusloActual());
        assertEquals(pechoActual, usuario.getPechoActual());
        assertEquals(nombre, usuario.getNombre());
        assertEquals(apellidos, usuario.getApellidos());
        assertEquals(correo, usuario.getCorreo());
        assertEquals(nombreUsuario, usuario.getNombreUsuario());
        assertEquals(contrasenya, usuario.getContrasenya());
    }

    @Test
    public void testGettersAndSetters() {
        // Arrange
        Usuario usuario = new Usuario();
        Long idUsuario = 1L;
        String imagen = "imagen.jpg";
        Double alturaActual = 170.0;
        Double pesoActual = 70.0;
        Double hombroActual = 40.0;
        Double espaldaActual = 90.0;
        Double cinturaActual = 75.0;
        Double gemeloActual = 35.0;
        Double bicepsActual = 30.0;
        Double musloActual = 60.0;
        Double pechoActual = 95.0;
        String nombre = "John";
        String apellidos = "Doe";
        String correo = "john@example.com";
        String nombreUsuario = "john";
        String contrasenya = "password";

        // Act
        usuario.setIdUsuario(idUsuario);
        usuario.setImagen(imagen);
        usuario.setAlturaActual(alturaActual);
        usuario.setPesoActual(pesoActual);
        usuario.setHombroActual(hombroActual);
        usuario.setEspaldaActual(espaldaActual);
        usuario.setCinturaActual(cinturaActual);
        usuario.setGemeloActual(gemeloActual);
        usuario.setBicepsActual(bicepsActual);
        usuario.setMusloActual(musloActual);
        usuario.setPechoActual(pechoActual);
        usuario.setNombre(nombre);
        usuario.setApellidos(apellidos);
        usuario.setCorreo(correo);
        usuario.setNombreUsuario(nombreUsuario);
        usuario.setContrasenya(contrasenya);

        // Assert
        assertEquals(idUsuario, usuario.getIdUsuario());
        assertEquals(imagen, usuario.getImagen());
        assertEquals(alturaActual, usuario.getAlturaActual());
        assertEquals(pesoActual, usuario.getPesoActual());
        assertEquals(hombroActual, usuario.getHombroActual());
        assertEquals(espaldaActual, usuario.getEspaldaActual());
        assertEquals(cinturaActual, usuario.getCinturaActual());
        assertEquals(gemeloActual, usuario.getGemeloActual());
        assertEquals(bicepsActual, usuario.getBicepsActual());
        assertEquals(musloActual, usuario.getMusloActual());
        assertEquals(pechoActual, usuario.getPechoActual());
        assertEquals(nombre, usuario.getNombre());
        assertEquals(apellidos, usuario.getApellidos());
        assertEquals(correo, usuario.getCorreo());
        assertEquals(nombreUsuario, usuario.getNombreUsuario());
        assertEquals(contrasenya, usuario.getContrasenya());
    }

    @Test
    public void testAgregarYEliminarElementos() {
        Usuario usuario = new Usuario();

        usuario.setPlantillaRutinas(new ArrayList<>());
        usuario.setRutinas(new ArrayList<>());
        usuario.setPlantillaEjercicios(new ArrayList<>());
        usuario.setIMCs(new ArrayList<>());
        usuario.setPesos(new ArrayList<>());
        usuario.setMedidasCorporales(new ArrayList<>());
        usuario.setPlantillaRutinas(new ArrayList<>());
        usuario.setSeguidos(new ArrayList<>());
        usuario.setSeguidores(new ArrayList<>());
        usuario.setPlantillaRutinaGuardadas(new ArrayList<>());

        PlantillaRutina plantillaRutina = new PlantillaRutina();
        Rutina rutina = new Rutina();
        PlantillaEjercicio plantillaEjercicio = new PlantillaEjercicio();
        IMC imc = new IMC();
        Peso peso = new Peso();
        MedidasCorporales medidasCorporales = new MedidasCorporales();
        PlantillaRutina plantillaRutinaGuardada = new PlantillaRutina();
        Usuario seguidor = new Usuario();

        usuario.getPlantillaRutinas().add(plantillaRutina);
        usuario.getRutinas().add(rutina);
        usuario.getPlantillaEjercicios().add(plantillaEjercicio);
        usuario.getIMCs().add(imc);
        usuario.getPesos().add(peso);
        usuario.getMedidasCorporales().add(medidasCorporales);
        usuario.getPlantillaRutinaGuardadas().add(plantillaRutinaGuardada);
        usuario.getSeguidores().add(seguidor);
        usuario.getSeguidos().add(seguidor);

        assertTrue(usuario.getPlantillaRutinas().contains(plantillaRutina));
        assertTrue(usuario.getRutinas().contains(rutina));
        assertTrue(usuario.getPlantillaEjercicios().contains(plantillaEjercicio));
        assertTrue(usuario.getIMCs().contains(imc));
        assertTrue(usuario.getPesos().contains(peso));
        assertTrue(usuario.getMedidasCorporales().contains(medidasCorporales));
        assertTrue(usuario.getPlantillaRutinaGuardadas().contains(plantillaRutinaGuardada));
        assertTrue(usuario.getSeguidores().contains(seguidor));
        assertTrue(usuario.getSeguidos().contains(seguidor));

        usuario.getPlantillaRutinas().remove(plantillaRutina);
        usuario.getRutinas().remove(rutina);
        usuario.getPlantillaEjercicios().remove(plantillaEjercicio);
        usuario.getIMCs().remove(imc);
        usuario.getPesos().remove(peso);
        usuario.getMedidasCorporales().remove(medidasCorporales);
        usuario.getPlantillaRutinaGuardadas().remove(plantillaRutinaGuardada);
        usuario.getSeguidores().remove(seguidor);
        usuario.getSeguidos().remove(seguidor);

        assertFalse(usuario.getPlantillaRutinas().contains(plantillaRutina));
        assertFalse(usuario.getRutinas().contains(rutina));
        assertFalse(usuario.getPlantillaEjercicios().contains(plantillaEjercicio));
        assertFalse(usuario.getIMCs().contains(imc));
        assertFalse(usuario.getPesos().contains(peso));
        assertFalse(usuario.getMedidasCorporales().contains(medidasCorporales));
        assertFalse(usuario.getPlantillaRutinaGuardadas().contains(plantillaRutinaGuardada));
        assertFalse(usuario.getSeguidores().contains(seguidor));
        assertFalse(usuario.getSeguidos().contains(seguidor));
    }
}
