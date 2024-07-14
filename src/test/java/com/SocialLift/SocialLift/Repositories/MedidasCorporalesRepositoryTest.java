package com.SocialLift.SocialLift.Repositories;

import com.SocialLift.SocialLift.Models.MedidasCorporales;
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
public class MedidasCorporalesRepositoryTest {

    @Autowired
    private MedidasCorporalesRepository medidasCorporalesRepository;

    private MedidasCorporales medidasCorporalesPrueba;

    @Before
    public void setUp() {
        // Crear una instancia de MedidasCorporales para la prueba
        medidasCorporalesPrueba = new MedidasCorporales();
        medidasCorporalesPrueba.setHombros(40.0);
        medidasCorporalesPrueba.setEspalda(90.0);
        medidasCorporalesPrueba.setCintura(75.0);
        medidasCorporalesPrueba.setGemelo(35.0);
        medidasCorporalesPrueba.setBiceps(30.0);
        medidasCorporalesPrueba.setMuslo(55.0);
        medidasCorporalesPrueba.setPecho(95.0);

        // Asignar una fecha válida a la propiedad registro
        medidasCorporalesPrueba.setRegistro(new Date());

        // Guardar las medidas corporales en la base de datos
        medidasCorporalesRepository.save(medidasCorporalesPrueba);
    }

    @Test
    public void testFindByIdMedidasCorporales() {
        Optional<MedidasCorporales> medidasOptional = medidasCorporalesRepository.findById(medidasCorporalesPrueba.getIdMedidasCorporales());

        assertTrue(medidasOptional.isPresent());
        MedidasCorporales medidasEncontradas = medidasOptional.get();
        assertEquals(40.0, medidasEncontradas.getHombros(), 0.01);
        assertEquals(90.0, medidasEncontradas.getEspalda(), 0.01);
        assertEquals(75.0, medidasEncontradas.getCintura(), 0.01);
        assertEquals(35.0, medidasEncontradas.getGemelo(), 0.01);
        assertEquals(30.0, medidasEncontradas.getBiceps(), 0.01);
        assertEquals(55.0, medidasEncontradas.getMuslo(), 0.01);
        assertEquals(95.0, medidasEncontradas.getPecho(), 0.01);
    }

    @Test
    public void testUpdateMedidasCorporales() {
        medidasCorporalesPrueba.setHombros(45.0);
        medidasCorporalesPrueba.setEspalda(95.0);
        medidasCorporalesPrueba.setCintura(80.0);
        medidasCorporalesRepository.save(medidasCorporalesPrueba);

        Optional<MedidasCorporales> medidasOptional = medidasCorporalesRepository.findById(medidasCorporalesPrueba.getIdMedidasCorporales());
        assertTrue(medidasOptional.isPresent());
        assertEquals(45.0, medidasOptional.get().getHombros(), 0.01);
        assertEquals(95.0, medidasOptional.get().getEspalda(), 0.01);
        assertEquals(80.0, medidasOptional.get().getCintura(), 0.01);
    }

    @Test
    public void testDeleteMedidasCorporales() {
        medidasCorporalesRepository.delete(medidasCorporalesPrueba);

        Optional<MedidasCorporales> medidasOptional = medidasCorporalesRepository.findById(medidasCorporalesPrueba.getIdMedidasCorporales());
        assertFalse(medidasOptional.isPresent());
    }

    @After
    public void tearDown() {
        // Limpiar datos después de cada prueba si es necesario
    }
}
