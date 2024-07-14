package com.SocialLift.SocialLift.Repositories;

import com.SocialLift.SocialLift.Models.PlantillaEjercicio;
import com.SocialLift.SocialLift.Models.Usuario;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PlantillaEjercicioRepositoryTest {

    @Autowired
    private PlantillaEjercicioRepository plantillaEjercicioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private PlantillaEjercicio plantillaEjercicioPrueba;
    private Usuario usuarioPrueba;

    @Before
    public void setUp() {
        // Crear un usuario de prueba
        usuarioPrueba = new Usuario();
        usuarioPrueba.setNombre("Nombre de prueba");
        usuarioPrueba.setApellidos("Apellidos de prueba");
        usuarioPrueba.setCorreo("correo@example.com");
        usuarioPrueba.setNombreUsuario("usuario_prueba");
        usuarioPrueba.setContrasenya("contraseña_de_prueba");

        usuarioPrueba = usuarioRepository.save(usuarioPrueba);

        // Crear una plantilla de ejercicio de prueba asociada al usuario
        plantillaEjercicioPrueba = new PlantillaEjercicio();
        plantillaEjercicioPrueba.setNombre("Plantilla de Ejercicio Prueba");
        plantillaEjercicioPrueba.setDescripcion("Descripción de la plantilla de ejercicio");
        plantillaEjercicioPrueba.setTipo("Tipo de ejercicio");
        plantillaEjercicioPrueba.setUsuario(usuarioPrueba);

        // Guardar la plantilla de ejercicio en la base de datos
        plantillaEjercicioRepository.save(plantillaEjercicioPrueba);
    }

    @Test
    public void testFindByUsuarioIdUsuario() {
        // Ejecutar el método de consulta para encontrar plantillas por ID de usuario
        List<PlantillaEjercicio> plantillasEncontradas = plantillaEjercicioRepository.findByUsuarioIdUsuario(usuarioPrueba.getIdUsuario());

        // Verificar que se encontró al menos una plantilla
        assertNotNull(plantillasEncontradas);
        assertEquals(1, plantillasEncontradas.size());

        // Obtener la plantilla encontrada y compararla con la plantilla de prueba
        PlantillaEjercicio plantillaEncontrada = plantillasEncontradas.get(0);
        assertEquals(plantillaEjercicioPrueba.getIdPlantillaEjercicio(), plantillaEncontrada.getIdPlantillaEjercicio());
        assertEquals(plantillaEjercicioPrueba.getNombre(), plantillaEncontrada.getNombre());
        assertEquals(plantillaEjercicioPrueba.getDescripcion(), plantillaEncontrada.getDescripcion());
        assertEquals(plantillaEjercicioPrueba.getTipo(), plantillaEncontrada.getTipo());
        assertEquals(plantillaEjercicioPrueba.getUsuario().getIdUsuario(), plantillaEncontrada.getUsuario().getIdUsuario());
    }

    @Test
    public void testGuardarPlantillaEjercicio() {
        // Crear una nueva plantilla de ejercicio
        PlantillaEjercicio nuevaPlantilla = new PlantillaEjercicio();
        nuevaPlantilla.setNombre("Plantilla Nueva");
        nuevaPlantilla.setDescripcion("Descripción de la plantilla nueva");
        nuevaPlantilla.setTipo("Tipo de ejercicio nuevo");
        nuevaPlantilla.setUsuario(usuarioPrueba);

        // Guardar la nueva plantilla en la base de datos
        plantillaEjercicioRepository.save(nuevaPlantilla);

        // Verificar que la nueva plantilla se ha guardado correctamente
        assertNotNull(nuevaPlantilla.getIdPlantillaEjercicio());

        // Buscar la plantilla recién guardada en la base de datos
        Optional<PlantillaEjercicio> plantillaRecuperada = plantillaEjercicioRepository.findById(nuevaPlantilla.getIdPlantillaEjercicio());

        // Verificar que la plantilla recuperada no sea nula y tenga los mismos datos que la plantilla nueva
        assertTrue(plantillaRecuperada.isPresent());
        assertEquals(nuevaPlantilla.getNombre(), plantillaRecuperada.get().getNombre());
        assertEquals(nuevaPlantilla.getDescripcion(), plantillaRecuperada.get().getDescripcion());
        assertEquals(nuevaPlantilla.getTipo(), plantillaRecuperada.get().getTipo());
        assertEquals(nuevaPlantilla.getUsuario().getIdUsuario(), plantillaRecuperada.get().getUsuario().getIdUsuario());
    }

    @Test
    public void testActualizarPlantillaEjercicio() {
        // Modificar la plantilla de ejercicio de prueba
        plantillaEjercicioPrueba.setNombre("Nuevo Nombre de Plantilla");
        plantillaEjercicioPrueba.setDescripcion("Nueva descripción de la plantilla");
        plantillaEjercicioPrueba.setTipo("Nuevo tipo de ejercicio");

        // Actualizar la plantilla en la base de datos
        plantillaEjercicioRepository.save(plantillaEjercicioPrueba);

        // Buscar la plantilla actualizada en la base de datos
        Optional<PlantillaEjercicio> plantillaActualizada = plantillaEjercicioRepository.findById(plantillaEjercicioPrueba.getIdPlantillaEjercicio());

        // Verificar que la plantilla actualizada no sea nula y tenga los datos actualizados
        assertTrue(plantillaActualizada.isPresent());
        assertEquals(plantillaEjercicioPrueba.getNombre(), plantillaActualizada.get().getNombre());
        assertEquals(plantillaEjercicioPrueba.getDescripcion(), plantillaActualizada.get().getDescripcion());
        assertEquals(plantillaEjercicioPrueba.getTipo(), plantillaActualizada.get().getTipo());
        assertEquals(plantillaEjercicioPrueba.getUsuario().getIdUsuario(), plantillaActualizada.get().getUsuario().getIdUsuario());
    }

    @Test
    public void testEliminarPlantillaEjercicio() {
        // Eliminar la plantilla de ejercicio de prueba de la base de datos
        plantillaEjercicioRepository.delete(plantillaEjercicioPrueba);

        // Verificar que la plantilla ha sido eliminada buscándola por ID
        Optional<PlantillaEjercicio> plantillaEliminada = plantillaEjercicioRepository.findById(plantillaEjercicioPrueba.getIdPlantillaEjercicio());
        assertFalse(plantillaEliminada.isPresent());
    }

    @Test
    public void testGetPlantillaEjercicioById() {
        // Obtener la plantilla de ejercicio de prueba por su ID
        Optional<PlantillaEjercicio> plantillaRecuperada = plantillaEjercicioRepository.findById(plantillaEjercicioPrueba.getIdPlantillaEjercicio());

        // Verificar que la plantilla recuperada no sea nula y tenga los mismos datos que la plantilla de prueba
        assertTrue(plantillaRecuperada.isPresent());
        assertEquals(plantillaEjercicioPrueba.getIdPlantillaEjercicio(), plantillaRecuperada.get().getIdPlantillaEjercicio());
        assertEquals(plantillaEjercicioPrueba.getNombre(), plantillaRecuperada.get().getNombre());
        assertEquals(plantillaEjercicioPrueba.getDescripcion(), plantillaRecuperada.get().getDescripcion());
        assertEquals(plantillaEjercicioPrueba.getTipo(), plantillaRecuperada.get().getTipo());
        assertEquals(plantillaEjercicioPrueba.getUsuario().getIdUsuario(), plantillaRecuperada.get().getUsuario().getIdUsuario());
    }

    @After
    public void tearDown() {
         }
}
