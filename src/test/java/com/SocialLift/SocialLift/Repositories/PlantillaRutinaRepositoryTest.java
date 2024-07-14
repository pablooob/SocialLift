package com.SocialLift.SocialLift.Repositories;

import com.SocialLift.SocialLift.Models.PlantillaRutina;
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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PlantillaRutinaRepositoryTest {

    @Autowired
    private PlantillaRutinaRepository plantillaRutinaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    private PlantillaRutina plantillaRutinaPrueba;
    private Usuario usuarioPrueba;

    @Before
    public void setUp() {
        usuarioPrueba = new Usuario();
        usuarioPrueba.setNombre("Nombre de prueba");
        usuarioPrueba.setApellidos("Apellidos de prueba");
        usuarioPrueba.setCorreo("correo@example.com");
        usuarioPrueba.setNombreUsuario("usuario_prueba");
        usuarioPrueba.setContrasenya("contraseña_de_prueba");

        usuarioPrueba = usuarioRepository.save(usuarioPrueba);

        // Crear una plantilla de rutina de prueba asociada al usuario
        plantillaRutinaPrueba = new PlantillaRutina();
        plantillaRutinaPrueba.setNombre("Plantilla de Rutina Prueba");
        plantillaRutinaPrueba.setDescripcion("Descripción de la plantilla de rutina");
        plantillaRutinaPrueba.setTipo("Tipo de rutina");
        plantillaRutinaPrueba.setUsuario(usuarioPrueba);

        // Guardar la plantilla de rutina en la base de datos
        plantillaRutinaRepository.save(plantillaRutinaPrueba);
    }

    @Test
    public void testFindByUsuarioIdUsuario() {
        // Ejecutar el método de consulta para encontrar plantillas por ID de usuario
        List<PlantillaRutina> plantillasEncontradas = plantillaRutinaRepository.findByUsuarioIdUsuario(usuarioPrueba.getIdUsuario());

        // Verificar que se encontró al menos una plantilla
        assertNotNull(plantillasEncontradas);
        assertEquals(1, plantillasEncontradas.size());

        // Obtener la plantilla encontrada y compararla con la plantilla de prueba
        PlantillaRutina plantillaEncontrada = plantillasEncontradas.get(0);
        assertEquals(plantillaRutinaPrueba.getIdPlantillaRutina(), plantillaEncontrada.getIdPlantillaRutina());
        assertEquals(plantillaRutinaPrueba.getNombre(), plantillaEncontrada.getNombre());
        assertEquals(plantillaRutinaPrueba.getDescripcion(), plantillaEncontrada.getDescripcion());
        assertEquals(plantillaRutinaPrueba.getTipo(), plantillaEncontrada.getTipo());
        assertEquals(plantillaRutinaPrueba.getUsuario().getIdUsuario(), plantillaEncontrada.getUsuario().getIdUsuario());
    }

    @Test
    public void testFindByNombreContaining() {
        // Buscar plantillas de rutina cuyo nombre contenga la palabra "Rutina"
        List<PlantillaRutina> plantillasEncontradas = plantillaRutinaRepository.findByNombreContaining("Rutina");

        // Verificar que se encontraron al menos una plantilla
        assertNotNull(plantillasEncontradas);
        assertTrue(plantillasEncontradas.size() > 0);

        // Verificar que todas las plantillas encontradas contienen la palabra "Rutina" en su nombre
        for (PlantillaRutina plantilla : plantillasEncontradas) {
            assertTrue(plantilla.getNombre().toLowerCase().contains("rutina"));
        }
    }

    @Test
    public void testFindByUsuarioGuardadosIdUsuario() {
        // Ejecutar el método de consulta para encontrar plantillas por ID de usuario guardados
        List<PlantillaRutina> plantillasEncontradas = plantillaRutinaRepository.findByUsuarioGuardadosIdUsuario(usuarioPrueba.getIdUsuario());

        // Verificar que no se encontraron plantillas (asumimos que no hay usuarios guardados)
        assertNotNull(plantillasEncontradas);
        assertEquals(0, plantillasEncontradas.size());
    }

    @Test
    public void testGuardarPlantillaRutina() {
        // Crear una nueva plantilla de rutina
        PlantillaRutina nuevaPlantilla = new PlantillaRutina();
        nuevaPlantilla.setNombre("Nueva Plantilla de Rutina");
        nuevaPlantilla.setDescripcion("Descripción de la nueva plantilla de rutina");
        nuevaPlantilla.setTipo("Nuevo tipo de rutina");
        nuevaPlantilla.setUsuario(usuarioPrueba);

        // Guardar la nueva plantilla en la base de datos
        plantillaRutinaRepository.save(nuevaPlantilla);

        // Verificar que la nueva plantilla se ha guardado correctamente
        assertNotNull(nuevaPlantilla.getIdPlantillaRutina());

        // Buscar la plantilla recién guardada en la base de datos
        Optional<PlantillaRutina> plantillaRecuperada = plantillaRutinaRepository.findById(nuevaPlantilla.getIdPlantillaRutina());

        // Verificar que la plantilla recuperada no sea nula y tenga los mismos datos que la plantilla nueva
        assertTrue(plantillaRecuperada.isPresent());
        assertEquals(nuevaPlantilla.getNombre(), plantillaRecuperada.get().getNombre());
        assertEquals(nuevaPlantilla.getDescripcion(), plantillaRecuperada.get().getDescripcion());
        assertEquals(nuevaPlantilla.getTipo(), plantillaRecuperada.get().getTipo());
        assertEquals(nuevaPlantilla.getUsuario().getIdUsuario(), plantillaRecuperada.get().getUsuario().getIdUsuario());
    }

    @Test
    public void testActualizarPlantillaRutina() {
        // Modificar la plantilla de rutina de prueba
        plantillaRutinaPrueba.setNombre("Nuevo Nombre de Plantilla de Rutina");
        plantillaRutinaPrueba.setDescripcion("Nueva descripción de la plantilla de rutina");
        plantillaRutinaPrueba.setTipo("Nuevo tipo de rutina");

        // Actualizar la plantilla en la base de datos
        plantillaRutinaRepository.save(plantillaRutinaPrueba);

        // Buscar la plantilla actualizada en la base de datos
        Optional<PlantillaRutina> plantillaActualizada = plantillaRutinaRepository.findById(plantillaRutinaPrueba.getIdPlantillaRutina());

        // Verificar que la plantilla actualizada no sea nula y tenga los datos actualizados
        assertTrue(plantillaActualizada.isPresent());
        assertEquals(plantillaRutinaPrueba.getNombre(), plantillaActualizada.get().getNombre());
        assertEquals(plantillaRutinaPrueba.getDescripcion(), plantillaActualizada.get().getDescripcion());
        assertEquals(plantillaRutinaPrueba.getTipo(), plantillaActualizada.get().getTipo());
        assertEquals(plantillaRutinaPrueba.getUsuario().getIdUsuario(), plantillaActualizada.get().getUsuario().getIdUsuario());
    }

    @Test
    public void testEliminarPlantillaRutina() {
        // Eliminar la plantilla de rutina de prueba de la base de datos
        plantillaRutinaRepository.delete(plantillaRutinaPrueba);

        // Verificar que la plantilla ha sido eliminada buscándola por ID
        Optional<PlantillaRutina> plantillaEliminada = plantillaRutinaRepository.findById(plantillaRutinaPrueba.getIdPlantillaRutina());
        assertFalse(plantillaEliminada.isPresent());
    }

    @After
    public void tearDown() {
        // Aquí puedes añadir lógica para limpiar la base de datos o realizar rollback si es necesario
        // No necesitas implementar un método tearDown específico en este caso si no hay limpieza adicional que hacer
    }
}
