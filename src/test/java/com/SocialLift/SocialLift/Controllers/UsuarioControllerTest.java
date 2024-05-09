package com.SocialLift.SocialLift.Controllers;

import com.SocialLift.SocialLift.Models.Usuario;
import com.SocialLift.SocialLift.Services.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    @Test
    public void testNewUsuario() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setNombre("Nombre de prueba");
        usuario.setApellidos("Apellidos de prueba");
        usuario.setCorreo("correo@example.com");
        usuario.setNombreUsuario("usuario_prueba");
        usuario.setContrasenya("contraseña_de_prueba");

        mockMvc.perform(post("/api/usuario")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(usuario)))
                .andExpect(status().isOk());

        verify(usuarioService, times(1)).NewUsuario(any(Usuario.class));
    }

    @Test
    public void testGetUsuario() throws Exception {
        Usuario usuarioPrueba1 = Usuario.builder()
                .nombre("Juan")
                .apellidos("Pérez")
                .correo("juan@example.com")
                .nombreUsuario("usuario_prueba1")
                .contrasenya("password1")
                .build();

        Usuario usuarioPrueba2 = Usuario.builder()
                .nombre("María")
                .apellidos("González")
                .correo("maria@example.com")
                .nombreUsuario("usuario_prueba2")
                .contrasenya("password2")
                .build();

        List<Usuario> usuarios = Arrays.asList(usuarioPrueba1, usuarioPrueba2);

        when(usuarioService.GetUsuario()).thenReturn(usuarios);

        mockMvc.perform(get("/api/usuario")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].nombre").value("Juan"))
                .andExpect(jsonPath("$[1].nombre").value("María"));
    }


    @Test
    public void testGetById() throws Exception {
        Usuario usuario = Usuario.builder()
                .nombre("Nombre de prueba")
                .apellidos("Apellidos de prueba")
                .correo("correo@example.com")
                .nombreUsuario("usuario_prueba")
                .contrasenya("contraseña_de_prueba")
                .idUsuario(1L)
                .build();

        Optional<Usuario> optionalUsuario = Optional.of(usuario);

        when(usuarioService.GetUsuarioById(1L)).thenReturn(optionalUsuario);

        mockMvc.perform(get("/api/usuario/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Nombre de prueba"))
                .andExpect(jsonPath("$.apellidos").value("Apellidos de prueba"))
                .andExpect(jsonPath("$.correo").value("correo@example.com"))
                .andExpect(jsonPath("$.nombreUsuario").value("usuario_prueba"))
                .andExpect(jsonPath("$.contrasenya").value("contraseña_de_prueba"));
    }

    @Test
    public void testGetByNombreUsuario() throws Exception {
        Usuario usuario = Usuario.builder()
                .nombre("Juan")
                .apellidos("Pérez")
                .correo("juan@example.com")
                .nombreUsuario("juanperez")
                .contrasenya("juan123")
                .build();
        when(usuarioService.GetUserByNombreUsuario("juanperez")).thenReturn(usuario);

        mockMvc.perform(get("/api/usuario/nombreUsuario/juanperez")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Juan"))
                .andExpect(jsonPath("$.apellidos").value("Pérez"))
                .andExpect(jsonPath("$.correo").value("juan@example.com"))
                .andExpect(jsonPath("$.nombreUsuario").value("juanperez"))
                .andExpect(jsonPath("$.contrasenya").value("juan123"));
    }

    @Test
    public void testGetByNombreUsuarioStartingWith() throws Exception {

        Usuario usuarioPrueba1 = Usuario.builder()
                .nombre("Juan")
                .apellidos("Pérez")
                .correo("juan@example.com")
                .nombreUsuario("usuario_prueba1")
                .contrasenya("password1")
                .build();

        Usuario usuarioPrueba2 = Usuario.builder()
                .nombre("María")
                .apellidos("González")
                .correo("maria@example.com")
                .nombreUsuario("usuario_prueba2")
                .contrasenya("password2")
                .build();

        List<Usuario> usuarios = Arrays.asList(usuarioPrueba1, usuarioPrueba2);

        when(usuarioService.findByNombreUsuarioStartingWith("usuario")).thenReturn(usuarios);

        mockMvc.perform(get("/api/usuario/startWith/usuario")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].nombre").value("Juan"))
                .andExpect(jsonPath("$[1].nombre").value("María"));
    }


    @Test
    public void testGetSeguidoresById() throws Exception {
        Usuario usuario = Usuario.builder()
                .nombre("Nombre de prueba")
                .apellidos("Apellidos de prueba")
                .correo("correo@example.com")
                .nombreUsuario("usuario_prueba")
                .contrasenya("contraseña_de_prueba")
                .build();
        usuario.setIdUsuario(1L);

        Usuario usuarioPrueba = Usuario.builder()
                .nombre("Nombre de usuarioPrueba")
                .apellidos("Apellidos de usuarioPrueba")
                .correo("correo@example.com")
                .nombreUsuario("usuario_prueba")
                .contrasenya("contraseña_de_prueba")
                .build();
        Usuario usuarioPrueba2 = Usuario.builder()
                .nombre("Nombre de prueba2")
                .apellidos("Apellidos de prueba2")
                .correo("correo2@example.com")
                .nombreUsuario("usuario_prueba2")
                .contrasenya("contraseña_de_prueba2")
                .build();
        List<Usuario> seguidores = Arrays.asList(usuarioPrueba, usuarioPrueba2);

        usuario.setSeguidores(seguidores);

        when(usuarioService.GetSeguidoresById(1L)).thenReturn(seguidores);

        mockMvc.perform(get("/api/usuario/1/seguidores")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].nombre").value("Nombre de usuarioPrueba"))
                .andExpect(jsonPath("$[1].nombre").value("Nombre de prueba2"));
    }

    @Test
    public void testGetSeguiendoById() throws Exception {
        Usuario usuario = Usuario.builder()
                .nombre("Nombre de usuario")
                .apellidos("Apellidos de usuario")
                .correo("correo1@example.com")
                .nombreUsuario("usuario_prueba")
                .contrasenya("contraseña_de_prueba")
                .build();
        usuario.setIdUsuario(1L);

        Usuario usuarioPrueba1 = Usuario.builder()
                .nombre("Nombre de usuarioPrueba1")
                .apellidos("Apellidos de usuarioPrueba1")
                .correo("correo2@example.com")
                .nombreUsuario("usuario_prueba1")
                .contrasenya("contraseña_de_prueba1")
                .build();

        Usuario usuarioPrueba2 = Usuario.builder()
                .nombre("Nombre de usuarioPrueba2")
                .apellidos("Apellidos de usuarioPrueba2")
                .correo("correo3@example.com")
                .nombreUsuario("usuario_prueba2")
                .contrasenya("contraseña_de_prueba2")
                .build();

        List<Usuario> seguidos = Arrays.asList(usuarioPrueba1, usuarioPrueba2);
        usuario.setSeguidos(seguidos);

        when(usuarioService.GetSeguiendoById(1L)).thenReturn(seguidos);

        mockMvc.perform(get("/api/usuario/1/siguiendo")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].nombre").value("Nombre de usuarioPrueba1"))
                .andExpect(jsonPath("$[1].nombre").value("Nombre de usuarioPrueba2"));

    }


    @Test
    public void testPostSeguirUsuario() throws Exception {
        mockMvc.perform(post("/api/usuario/1/seguir/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(usuarioService, times(1)).SeguirUsuarioById(1L, 2L);
    }

    @Test
    public void testUpdateUsuario() throws Exception {
        Usuario usuario = Usuario.builder()
                .nombre("Nombre de prueba")
                .apellidos("Apellidos de prueba")
                .correo("correo@example.com")
                .nombreUsuario("usuario_prueba")
                .contrasenya("contraseña_de_prueba")
                .build();

        mockMvc.perform(put("/api/usuario")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(usuario)))
                .andExpect(status().isOk());

        verify(usuarioService, times(1)).UpdateUsuario(any(Usuario.class));
    }

    @Test
    public void testDeleteUsuarioById() throws Exception {
        mockMvc.perform(delete("/api/usuario/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(usuarioService, times(1)).DeleteUsuario(1L);
    }

    @Test
    public void testDeleteSeguimientoById() throws Exception {
        mockMvc.perform(delete("/api/usuario/1/desseguir/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(usuarioService, times(1)).DeleteSeguimientoById(1L, 2L);
    }

}
