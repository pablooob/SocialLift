package com.SocialLift.SocialLift.Repositories;

import com.SocialLift.SocialLift.Models.Peso;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PesoRepositoryTest {

    @Autowired
    private PesoRepository pesoRepository;

    private Peso pesoPrueba;

    @Before
    public void setUp() {
        // Crear una instancia de Peso para la prueba
        pesoPrueba = new Peso();
        pesoPrueba.setValue(70.5);  // Asignar un valor al peso
        pesoPrueba.setRegistro(new Date());  // Asignar una fecha válida a registro

        // Guardar el peso en la base de datos
        pesoRepository.save(pesoPrueba);
    }

    @Test
    public void testFindByIdPeso() {
        Optional<Peso> pesoOptional = pesoRepository.findById(pesoPrueba.getIdPeso());

        assertTrue(pesoOptional.isPresent());
        Peso pesoEncontrado = pesoOptional.get();
        assertEquals(70.5, pesoEncontrado.getValue(), 0.01);  // Verificar el valor del peso
    }

    @Test
    public void testUpdatePeso() {
        pesoPrueba.setValue(72.0);  // Actualizar el valor del peso
        pesoRepository.save(pesoPrueba);

        Optional<Peso> pesoOptional = pesoRepository.findById(pesoPrueba.getIdPeso());
        assertTrue(pesoOptional.isPresent());
        assertEquals(72.0, pesoOptional.get().getValue(), 0.01);  // Verificar el nuevo valor del peso
    }

    @Test
    public void testDeletePeso() {
        pesoRepository.delete(pesoPrueba);

        Optional<Peso> pesoOptional = pesoRepository.findById(pesoPrueba.getIdPeso());
        assertFalse(pesoOptional.isPresent());  // Verificar que el peso fue eliminado correctamente
    }

    @After
    public void tearDown() {
        // Limpiar datos después de cada prueba si es necesario
    }
}
