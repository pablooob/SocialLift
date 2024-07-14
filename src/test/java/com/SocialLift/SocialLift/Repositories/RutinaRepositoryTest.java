package com.SocialLift.SocialLift.Repositories;

import com.SocialLift.SocialLift.Models.Rutina;
import com.SocialLift.SocialLift.Models.Usuario;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class RutinaRepositoryTest {

    @Autowired
    private RutinaRepository rutinaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    private Rutina rutinaPrueba;
    private Usuario usuarioPrueba;

    @Before
    public void setUp() {
        usuarioPrueba = new Usuario();
        usuarioPrueba.setNombre("Nombre de prueba");
        usuarioPrueba.setApellidos("Apellidos de prueba");
        usuarioPrueba.setCorreo("correo@example.com");
        usuarioPrueba.setNombreUsuario("usuario_prueba");
        usuarioPrueba.setContrasenya("contraseña_de_prueba");

        // Guardar el usuario de prueba en la base de datos
        usuarioPrueba = usuarioRepository.save(usuarioPrueba);

        // Crear una rutina de prueba asociada al usuario
        rutinaPrueba = new Rutina();
        rutinaPrueba.setFecha(new Date());
        rutinaPrueba.setNombre("Rutina de Prueba");
        rutinaPrueba.setDescripcion("Descripción de la rutina de prueba");
        rutinaPrueba.setIsPublic(true);
        rutinaPrueba.setUsuario(usuarioPrueba);

        // Guardar la rutina de prueba en la base de datos
        rutinaRepository.save(rutinaPrueba);
    }

    @Test
    public void testFindByUsuarioIdUsuario() {
        // Ejecutar el método de consulta para encontrar rutinas por ID de usuario
        List<Rutina> rutinasEncontradas = rutinaRepository.findByUsuarioIdUsuario(usuarioPrueba.getIdUsuario());

        // Verificar que se encontró al menos una rutina
        assertNotNull(rutinasEncontradas);
        assertEquals(1, rutinasEncontradas.size());

        // Obtener la rutina encontrada y compararla con la rutina de prueba
        Rutina rutinaEncontrada = rutinasEncontradas.get(0);
        assertEquals(rutinaPrueba.getIdRutina(), rutinaEncontrada.getIdRutina());
        assertEquals(rutinaPrueba.getNombre(), rutinaEncontrada.getNombre());
        assertEquals(rutinaPrueba.getDescripcion(), rutinaEncontrada.getDescripcion());
        assertEquals(rutinaPrueba.getIsPublic(), rutinaEncontrada.getIsPublic());
        assertEquals(rutinaPrueba.getUsuario().getIdUsuario(), rutinaEncontrada.getUsuario().getIdUsuario());
    }

    @Test
    public void testFindByUsuarioInAndIsPublicIsTrue() {
        // Crear una lista con el usuario de prueba
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(usuarioPrueba);

        // Ejecutar el método de consulta para encontrar rutinas públicas de los usuarios en la lista
        List<Rutina> rutinasEncontradas = rutinaRepository.findByUsuarioInAndIsPublicIsTrue(usuarios);

        // Verificar que se encontró al menos una rutina
        assertNotNull(rutinasEncontradas);
        assertEquals(1, rutinasEncontradas.size());

        // Obtener la rutina encontrada y compararla con la rutina de prueba
        Rutina rutinaEncontrada = rutinasEncontradas.get(0);
        assertEquals(rutinaPrueba.getIdRutina(), rutinaEncontrada.getIdRutina());
        assertEquals(rutinaPrueba.getNombre(), rutinaEncontrada.getNombre());
        assertEquals(rutinaPrueba.getDescripcion(), rutinaEncontrada.getDescripcion());
        assertEquals(rutinaPrueba.getIsPublic(), rutinaEncontrada.getIsPublic());
        assertEquals(rutinaPrueba.getUsuario().getIdUsuario(), rutinaEncontrada.getUsuario().getIdUsuario());
    }

    @Test
    public void testGuardarRutina() {
        // Crear una nueva rutina
        Rutina nuevaRutina = new Rutina();
        nuevaRutina.setFecha(new Date());
        nuevaRutina.setNombre("Nueva Rutina prueba");
        nuevaRutina.setDescripcion("Descripción de la nueva rutina");
        nuevaRutina.setIsPublic(false);
        nuevaRutina.setUsuario(usuarioPrueba);

        // Guardar la nueva rutina en la base de datos
        rutinaRepository.save(nuevaRutina);

        // Verificar que la nueva rutina se ha guardado correctamente
        assertNotNull(nuevaRutina.getIdRutina());

        // Buscar la rutina recién guardada en la base de datos
        Optional<Rutina> rutinaRecuperada = rutinaRepository.findById(nuevaRutina.getIdRutina());

        // Verificar que la rutina recuperada no sea nula y tenga los mismos datos que la rutina nueva
        assertTrue(rutinaRecuperada.isPresent());
        assertEquals(nuevaRutina.getNombre(), rutinaRecuperada.get().getNombre());
        assertEquals(nuevaRutina.getDescripcion(), rutinaRecuperada.get().getDescripcion());
        assertEquals(nuevaRutina.getIsPublic(), rutinaRecuperada.get().getIsPublic());
        assertEquals(nuevaRutina.getUsuario().getIdUsuario(), rutinaRecuperada.get().getUsuario().getIdUsuario());
    }

    @Test
    public void testActualizarRutina() {
        // Modificar la rutina de prueba
        rutinaPrueba.setNombre("Nueva Rutina de Prueba");
        rutinaPrueba.setDescripcion("Nueva descripción de la rutina de prueba");
        rutinaPrueba.setIsPublic(false);

        // Actualizar la rutina en la base de datos
        rutinaRepository.save(rutinaPrueba);

        // Buscar la rutina actualizada en la base de datos
        Optional<Rutina> rutinaActualizada = rutinaRepository.findById(rutinaPrueba.getIdRutina());

        // Verificar que la rutina actualizada no sea nula y tenga los datos actualizados
        assertTrue(rutinaActualizada.isPresent());
        assertEquals(rutinaPrueba.getNombre(), rutinaActualizada.get().getNombre());
        assertEquals(rutinaPrueba.getDescripcion(), rutinaActualizada.get().getDescripcion());
        assertEquals(rutinaPrueba.getIsPublic(), rutinaActualizada.get().getIsPublic());
        assertEquals(rutinaPrueba.getUsuario().getIdUsuario(), rutinaActualizada.get().getUsuario().getIdUsuario());
    }

    @Test
    public void testEliminarRutina() {
        // Eliminar la rutina de prueba de la base de datos
        rutinaRepository.delete(rutinaPrueba);

        // Verificar que la rutina ha sido eliminada buscándola por ID
        Optional<Rutina> rutinaEliminada = rutinaRepository.findById(rutinaPrueba.getIdRutina());
        assertFalse(rutinaEliminada.isPresent());
    }

    @After
    public void tearDown() {
        // Aquí puedes añadir lógica para limpiar la base de datos o realizar rollback si es necesario
        // No necesitas implementar un método tearDown específico en este caso si no hay limpieza adicional que hacer
    }
}
