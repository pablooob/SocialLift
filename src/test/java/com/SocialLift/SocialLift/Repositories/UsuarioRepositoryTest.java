package com.SocialLift.SocialLift.Repositories;

import com.SocialLift.SocialLift.Models.Usuario;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private Usuario usuarioPrueba;

    @Before
    public void setUp() {
        usuarioPrueba = new Usuario();
        usuarioPrueba.setNombre("Nombre de prueba");
        usuarioPrueba.setApellidos("Apellidos de prueba");
        usuarioPrueba.setCorreo("correo@example.com");
        usuarioPrueba.setNombreUsuario("usuario_prueba");
        usuarioPrueba.setContrasenya("contraseña_de_prueba");

        usuarioRepository.save(usuarioPrueba);
    }


    @Test
    public void testSaveAndFindByIdUsuario() {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(usuarioPrueba.getIdUsuario());

        assertTrue(usuarioOptional.isPresent());
        assertEquals(usuarioPrueba.getIdUsuario(), usuarioOptional.get().getIdUsuario());
    }

    @Test
    public void testUpdateUsuario() {
        usuarioPrueba.setNombreUsuario("nuevo_nombre_usuario");
        usuarioRepository.save(usuarioPrueba);

        Optional<Usuario> usuarioOptional = usuarioRepository.findById(usuarioPrueba.getIdUsuario());
        assertTrue(usuarioOptional.isPresent());
        assertEquals("nuevo_nombre_usuario", usuarioOptional.get().getNombreUsuario());
    }

    @Test
    public void testDeleteUsuario() {
        usuarioRepository.delete(usuarioPrueba);

        Optional<Usuario> usuarioOptional = usuarioRepository.findById(usuarioPrueba.getIdUsuario());
        assertFalse(usuarioOptional.isPresent());
    }

    @Test
    public void testFindByNombreUsuario() {
        String nombreUsuario = "usuario_prueba";

        Optional<Usuario> resultado = Optional.ofNullable(usuarioRepository.findByNombreUsuario(nombreUsuario));

        assertTrue(resultado.isPresent());
        assertEquals("usuario_prueba", resultado.get().getNombreUsuario());
    }

    @Test
    public void testFindByNombreUsuarioStartingWith() {
        String nombreUsuario = "usuario_prueba";

        Iterable<Usuario> resultados = usuarioRepository.findByNombreUsuarioStartingWith(nombreUsuario);

        assertTrue(resultados.iterator().hasNext());
    }


    @After
    public void tearDown() {
        usuarioRepository.delete(usuarioPrueba);
    }
}
