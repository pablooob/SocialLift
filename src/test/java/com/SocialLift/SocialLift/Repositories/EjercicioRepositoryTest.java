package com.SocialLift.SocialLift.Repositories;

import com.SocialLift.SocialLift.Models.Ejercicio;
import com.SocialLift.SocialLift.Models.PlantillaEjercicio;
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
public class EjercicioRepositoryTest {

    @Autowired
    private EjercicioRepository ejercicioRepository;

    @Autowired
    private PlantillaEjercicioRepository plantillaEjercicioRepository;

    private Ejercicio ejercicioPrueba;

    @Before
    public void setUp() {
        PlantillaEjercicio plantillaEjercicio = new PlantillaEjercicio();
        plantillaEjercicio.setNombre("Plantilla de ejercicio de prueba");
        plantillaEjercicio.setTipo("Tipo de plantilla");

        plantillaEjercicioRepository.save(plantillaEjercicio);

        ejercicioPrueba = new Ejercicio();
        ejercicioPrueba.setPlantillaEjercicio(plantillaEjercicio);

        ejercicioRepository.save(ejercicioPrueba);
    }


    @Test
    @Transactional // Asegura que la prueba se ejecute dentro de una transacción
    public void testFindByIdEjercicio() {
        Optional<Ejercicio> ejercicioOptional = ejercicioRepository.findById(ejercicioPrueba.getIdEjercicio());

        assertTrue(ejercicioOptional.isPresent());
        Ejercicio ejercicioEncontrado = ejercicioOptional.get();
        assertNotNull(ejercicioEncontrado.getPlantillaEjercicio());
        assertEquals("Plantilla de ejercicio de prueba", ejercicioEncontrado.getPlantillaEjercicio().getNombre());
    }

    @Test
    @Transactional // Asegura que la prueba se ejecute dentro de una transacción
    public void testUpdateEjercicio() {
        ejercicioPrueba.getPlantillaEjercicio().setNombre("Nueva plantilla de ejercicio");
        ejercicioRepository.save(ejercicioPrueba);

        Optional<Ejercicio> ejercicioOptional = ejercicioRepository.findById(ejercicioPrueba.getIdEjercicio());
        assertTrue(ejercicioOptional.isPresent());
        assertEquals("Nueva plantilla de ejercicio", ejercicioOptional.get().getPlantillaEjercicio().getNombre());
    }

    @Test
    @Transactional // Asegura que la prueba se ejecute dentro de una transacción
    public void testDeleteEjercicio() {
        ejercicioRepository.delete(ejercicioPrueba);

        Optional<Ejercicio> ejercicioOptional = ejercicioRepository.findById(ejercicioPrueba.getIdEjercicio());
        assertFalse(ejercicioOptional.isPresent());
    }

    @After
    public void tearDown() {
        // No es necesario realizar ninguna operación de limpieza específica
        // debido a que las transacciones y el rollback automático ya revertirán los cambios
    }
}
