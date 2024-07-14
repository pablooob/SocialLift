package com.SocialLift.SocialLift.Repositories;

import com.SocialLift.SocialLift.Models.IMC;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class IMCRepositoryTest {

    @Autowired
    private IMCRepository imcRepository;

    private IMC imcPrueba;

    @Before
    public void setUp() {
        // Crear una instancia de IMC para la prueba
        imcPrueba = new IMC();
        imcPrueba.setValue(22.5); // Ejemplo de valor de IMC
        imcPrueba.setRegistro(new Date()); // Asegúrate de inicializar la propiedad 'registro'

        // Guardar el IMC en la base de datos
        imcRepository.save(imcPrueba);
    }


    @Test
    public void testFindByIdIMC() {
        // Buscar el IMC por su ID
        Optional<IMC> imcOptional = imcRepository.findById(imcPrueba.getIdIMC());

        // Verificar que el IMC fue encontrado
        assertTrue(imcOptional.isPresent());
        assertEquals(imcPrueba.getValue(), imcOptional.get().getValue(), 0.001); // Comparar valores con tolerancia
    }

    @Test
    public void testUpdateIMC() {
        // Modificar el valor de IMC
        imcPrueba.setValue(23.0); // Nuevo valor de IMC

        // Guardar el IMC modificado en la base de datos
        imcRepository.save(imcPrueba);

        // Buscar el IMC nuevamente por su ID
        Optional<IMC> imcOptional = imcRepository.findById(imcPrueba.getIdIMC());

        // Verificar que el IMC fue encontrado y que el valor se actualizó correctamente
        assertTrue(imcOptional.isPresent());
        assertEquals(23.0, imcOptional.get().getValue(), 0.001); // Comparar valores con tolerancia
    }

    @Test
    public void testDeleteIMC() {
        // Eliminar el IMC de la base de datos
        imcRepository.delete(imcPrueba);

        // Intentar buscar el IMC por su ID después de eliminarlo
        Optional<IMC> imcOptional = imcRepository.findById(imcPrueba.getIdIMC());

        // Verificar que el IMC no fue encontrado después de eliminarlo
        assertFalse(imcOptional.isPresent());
    }

    @After
    public void tearDown() {
        // No se requiere limpieza adicional, el rollback automático de la transacción se encarga de revertir los cambios
    }
}
