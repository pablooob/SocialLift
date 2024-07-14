package com.SocialLift.SocialLift.Repositories;

import com.SocialLift.SocialLift.Models.Ejercicio;
import com.SocialLift.SocialLift.Models.Serie;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class SerieRepositoryTest {

    @Autowired
    private SerieRepository serieRepository;

    @Autowired
    private EjercicioRepository ejercicioRepository;

    private Serie seriePrueba;
    private Ejercicio ejercicioPrueba;

    @Before
    public void setUp() {
        // Crear un ejercicio de prueba
        ejercicioPrueba = new Ejercicio();
        ejercicioPrueba = ejercicioRepository.save(ejercicioPrueba);

        // Crear una serie de prueba asociada al ejercicio
        seriePrueba = new Serie();
        seriePrueba.setNumeroSerie(1);
        seriePrueba.setPeso(50.0);
        seriePrueba.setTipoPeso("kilogramos");
        seriePrueba.setNumeroRepeticiones(10);
        seriePrueba.setEjercicio(ejercicioPrueba);
        seriePrueba.setVideo("https://ejemplo.com/video");

        // Guardar la serie de prueba en la base de datos
        seriePrueba = serieRepository.save(seriePrueba);
    }

    @Test
    public void testGuardarSerie() {
        // Verificar que la serie se ha guardado correctamente
        assertNotNull(seriePrueba.getIdSerie());
    }

    @Test
    public void testBuscarSeriePorId() {
        // Buscar la serie por ID
        Optional<Serie> serieRecuperada = serieRepository.findById(seriePrueba.getIdSerie());

        // Verificar que la serie recuperada no sea nula y tenga los mismos datos que la serie de prueba
        assertTrue(serieRecuperada.isPresent());
        assertEquals(seriePrueba.getNumeroSerie(), serieRecuperada.get().getNumeroSerie());
        assertEquals(seriePrueba.getPeso(), serieRecuperada.get().getPeso(), 0.001); // tolerancia de comparación para doubles
        assertEquals(seriePrueba.getTipoPeso(), serieRecuperada.get().getTipoPeso());
        assertEquals(seriePrueba.getNumeroRepeticiones(), serieRecuperada.get().getNumeroRepeticiones());
        assertEquals(seriePrueba.getEjercicio().getIdEjercicio(), serieRecuperada.get().getEjercicio().getIdEjercicio());
        assertEquals(seriePrueba.getVideo(), serieRecuperada.get().getVideo());
    }

    @Test
    public void testActualizarSerie() {
        // Modificar la serie de prueba
        seriePrueba.setNumeroRepeticiones(15);
        seriePrueba.setPeso(60.0);

        // Actualizar la serie en la base de datos
        serieRepository.save(seriePrueba);

        // Buscar la serie actualizada por ID
        Optional<Serie> serieActualizada = serieRepository.findById(seriePrueba.getIdSerie());

        // Verificar que la serie actualizada no sea nula y tenga los datos actualizados
        assertTrue(serieActualizada.isPresent());
        assertEquals(seriePrueba.getNumeroRepeticiones(), serieActualizada.get().getNumeroRepeticiones());
        assertEquals(seriePrueba.getPeso(), serieActualizada.get().getPeso(), 0.001); // tolerancia de comparación para doubles
    }

    @Test
    public void testEliminarSerie() {
        // Eliminar la serie de prueba de la base de datos
        serieRepository.delete(seriePrueba);

        // Verificar que la serie ha sido eliminada buscándola por ID
        Optional<Serie> serieEliminada = serieRepository.findById(seriePrueba.getIdSerie());
        assertFalse(serieEliminada.isPresent());
    }

    @After
    public void tearDown() {
        // Aquí puedes añadir lógica para limpiar la base de datos o realizar rollback si es necesario
        // No necesitas implementar un método tearDown específico en este caso si no hay limpieza adicional que hacer
    }
}
