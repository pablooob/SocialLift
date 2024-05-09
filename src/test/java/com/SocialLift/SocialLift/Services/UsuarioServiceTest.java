package com.SocialLift.SocialLift.Services;

import com.SocialLift.SocialLift.Models.Usuario;
import com.SocialLift.SocialLift.Repositories.UsuarioRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    public void testNewUsuario() {
        Usuario usuario = new Usuario();
        usuarioService.NewUsuario(usuario);
        verify(usuarioRepository, times(1)).save(usuario);
    }

    @Test
    public void testGetUsuario() {
        List<Usuario> usuarios = new ArrayList<>();
        when(usuarioRepository.findAll()).thenReturn(usuarios);
        List<Usuario> result = usuarioService.GetUsuario();
        verify(usuarioRepository, times(1)).findAll();
        assertEquals(usuarios, result);
    }

    @Test
    public void testGetUsuarioById() {
        Long userId = 1L;
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(userId);
        Usuario seguidor = new Usuario();
        usuario.setSeguidores(new ArrayList<>());
        usuario.setSeguidos(new ArrayList<>());
        usuario.getSeguidores().add(seguidor);
        usuario.getSeguidos().add(seguidor);

        Optional<Usuario> optionalUsuario = Optional.of(usuario);
        when(usuarioRepository.findById(userId)).thenReturn(optionalUsuario);
        Optional<Usuario> result = usuarioService.GetUsuarioById(userId);
        verify(usuarioRepository, times(1)).findById(userId);
        assertEquals(optionalUsuario, result);
        assertEquals(usuario.getSeguidores(), null);
        assertEquals(usuario.getSeguidos(), null);
    }

    @Test
    public void testUpdateUsuario() {
        Usuario usuario = new Usuario();
        usuarioService.UpdateUsuario(usuario);
        verify(usuarioRepository, times(1)).save(usuario);
    }

    @Test
    public void testGetUserByNombreUsuario() {
        String nombreUsuario = "usuario1";
        Usuario seguidor = new Usuario();
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario(nombreUsuario);
        usuario.setSeguidores(new ArrayList<>());
        usuario.setSeguidos(new ArrayList<>());
        usuario.getSeguidores().add(seguidor);
        usuario.getSeguidos().add(seguidor);

        when(usuarioRepository.findByNombreUsuario(nombreUsuario)).thenReturn(usuario);
        Usuario result = usuarioService.GetUserByNombreUsuario(nombreUsuario);
        verify(usuarioRepository, times(1)).findByNombreUsuario(nombreUsuario);
        assertEquals(usuario, result);
        assertEquals(usuario.getSeguidores(), null);
        assertEquals(usuario.getSeguidos(), null);
    }

    @Test
    public void testFindByNombreUsuarioStartingWith() {
        String nombreUsuario = "usuario";
        List<Usuario> usuarios = new ArrayList<>();
        when(usuarioRepository.findByNombreUsuarioStartingWith(nombreUsuario)).thenReturn(usuarios);
        List<Usuario> result = usuarioService.findByNombreUsuarioStartingWith(nombreUsuario);
        verify(usuarioRepository, times(1)).findByNombreUsuarioStartingWith(nombreUsuario);
        assertEquals(usuarios, result);
    }

    @Test
    public void testGetSeguidoresById() {
        Long userId = 1L;
        Usuario usuario = new Usuario();
        List<Usuario> seguidores = new ArrayList<>();
        usuario.setSeguidores(seguidores);
        Optional<Usuario> optionalUsuario = Optional.of(usuario);
        when(usuarioRepository.findById(userId)).thenReturn(optionalUsuario);
        List<Usuario> result = usuarioService.GetSeguidoresById(userId);
        verify(usuarioRepository, times(1)).findById(userId);
        assertEquals(seguidores, result);
    }

    @Test
    public void testGetSeguiendoById() {
        Long userId = 1L;
        Usuario usuario = new Usuario();
        List<Usuario> seguidos = new ArrayList<>();
        usuario.setSeguidos(seguidos);
        Optional<Usuario> optionalUsuario = Optional.of(usuario);
        when(usuarioRepository.findById(userId)).thenReturn(optionalUsuario);
        List<Usuario> result = usuarioService.GetSeguiendoById(userId);
        verify(usuarioRepository, times(1)).findById(userId);
        assertEquals(seguidos, result);
    }

    @Test
    public void testSeguirUsuarioById() throws Exception {
        Long idUsuarioLoggeado = 1L;
        Long idUsuarioASeguir = 2L;
        Usuario usuarioLoggeado = new Usuario();
        usuarioLoggeado.setIdUsuario(idUsuarioLoggeado);
        usuarioLoggeado.setSeguidos(new ArrayList<>());
        Usuario usuarioASeguir = new Usuario();
        usuarioASeguir.setIdUsuario(idUsuarioASeguir);
        usuarioASeguir.setSeguidores(new ArrayList<>());
        Optional<Usuario> optionalUsuarioLoggeado = Optional.of(usuarioLoggeado);
        Optional<Usuario> optionalUsuarioASeguir = Optional.of(usuarioASeguir);

        when(usuarioRepository.findById(idUsuarioLoggeado)).thenReturn(optionalUsuarioLoggeado);
        when(usuarioRepository.findById(idUsuarioASeguir)).thenReturn(optionalUsuarioASeguir);

        usuarioService.SeguirUsuarioById(idUsuarioLoggeado, idUsuarioASeguir);

        verify(usuarioRepository, times(1)).findById(idUsuarioLoggeado);
        verify(usuarioRepository, times(1)).findById(idUsuarioASeguir);

        assertTrue(usuarioLoggeado.getSeguidos().contains(usuarioASeguir));
        assertTrue(usuarioASeguir.getSeguidores().contains(usuarioLoggeado));

        verify(usuarioRepository, times(1)).save(usuarioLoggeado);
    }

    @Test(expected = Exception.class)
    public void testSeguirUsuarioById_UsuarioNoEncontrado() throws Exception {
        Long idUsuarioLoggeado = 1L;
        Long idUsuarioASeguir = 2L;
        Optional<Usuario> optionalUsuarioLoggeado = Optional.empty();
        Optional<Usuario> optionalUsuarioASeguir = Optional.empty();

        when(usuarioRepository.findById(idUsuarioLoggeado)).thenReturn(optionalUsuarioLoggeado);
        when(usuarioRepository.findById(idUsuarioASeguir)).thenReturn(optionalUsuarioASeguir);

        usuarioService.SeguirUsuarioById(idUsuarioLoggeado, idUsuarioASeguir);
    }

    @Test
    public void testDeleteSeguimientoById() throws Exception {
        Long idUsuarioLoggeado = 1L;
        Long idUsuarioADesSeguir = 2L;
        Usuario usuarioLoggeado = new Usuario();
        usuarioLoggeado.setIdUsuario(idUsuarioLoggeado);
        usuarioLoggeado.setSeguidos(new ArrayList<>());
        Usuario usuarioADesSeguir = new Usuario();
        usuarioADesSeguir.setIdUsuario(idUsuarioADesSeguir);
        usuarioADesSeguir.setSeguidores(new ArrayList<>());
        Optional<Usuario> optionalUsuarioLoggeado = Optional.of(usuarioLoggeado);
        Optional<Usuario> optionalUsuarioADesSeguir = Optional.of(usuarioADesSeguir);

        when(usuarioRepository.findById(idUsuarioLoggeado)).thenReturn(optionalUsuarioLoggeado);
        when(usuarioRepository.findById(idUsuarioADesSeguir)).thenReturn(optionalUsuarioADesSeguir);

        usuarioService.DeleteSeguimientoById(idUsuarioLoggeado, idUsuarioADesSeguir);

        verify(usuarioRepository, times(1)).findById(idUsuarioLoggeado);
        verify(usuarioRepository, times(1)).findById(idUsuarioADesSeguir);

        assertFalse(usuarioLoggeado.getSeguidos().contains(usuarioADesSeguir));
        assertFalse(usuarioADesSeguir.getSeguidores().contains(usuarioLoggeado));

        verify(usuarioRepository, times(1)).save(usuarioLoggeado);
    }

    @Test(expected = Exception.class)
    public void testDeleteSeguimientoById_UsuarioNoEncontrado() throws Exception {
        Long idUsuarioLoggeado = 1L;
        Long idUsuarioADesSeguir = 2L;
        Optional<Usuario> optionalUsuarioLoggeado = Optional.empty();
        Optional<Usuario> optionalUsuarioADesSeguir = Optional.empty();

        when(usuarioRepository.findById(idUsuarioLoggeado)).thenReturn(optionalUsuarioLoggeado);
        when(usuarioRepository.findById(idUsuarioADesSeguir)).thenReturn(optionalUsuarioADesSeguir);
        usuarioService.DeleteSeguimientoById(idUsuarioLoggeado, idUsuarioADesSeguir);
    }

    @Test
    public void testDeleteUsuario() {
        Long idUsuario = 1L;
        usuarioService.DeleteUsuario(idUsuario);
        verify(usuarioRepository, times(1)).deleteById(idUsuario);
    }
}
