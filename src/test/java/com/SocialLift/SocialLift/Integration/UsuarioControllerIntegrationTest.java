package com.SocialLift.SocialLift.Integration;

import com.SocialLift.SocialLift.Repositories.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.SocialLift.SocialLift.Models.Usuario;
import com.SocialLift.SocialLift.Services.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional // Para asegurar que las transacciones sean rollback después de cada prueba
public class UsuarioControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper; // ObjectMapper para convertir objetos a JSON y viceversa

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @BeforeEach
    public void setUp() {
        // Aquí puedes inicializar datos de prueba si es necesario
    }

    @Test
    public void testNewUsuario() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setNombre("Juan");
        usuario.setApellidos("Pérez");
        usuario.setCorreo("juan@example.com");
        usuario.setNombreUsuario("juanperez");
        usuario.setContrasenya("password");

        mockMvc.perform(post("/api/usuario")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isOk());

        Usuario createdUsuario = usuarioService.GetUserByNombreUsuario(usuario.getNombreUsuario());
        assertEquals(createdUsuario.getNombreUsuario(), usuario.getNombreUsuario());

    }

    @Test
    public void testNewUsuarioName() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setApellidos("Pérez");
        usuario.setCorreo("juan@example.com");
        usuario.setNombreUsuario("juanperez");
        usuario.setContrasenya("password");

        // Envía la solicitud POST para crear el usuario
        mockMvc.perform(post("/api/usuario")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isBadRequest());

        Usuario createdUsuario = usuarioService.GetUserByNombreUsuario(usuario.getNombreUsuario());
        assert createdUsuario == null;
    }
    @Test
    public void testNewUsuarioApellidos() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setNombre("Juan");
        usuario.setCorreo("juan@example.com");
        usuario.setNombreUsuario("juanperez");
        usuario.setContrasenya("password");

        // Envía la solicitud POST para crear el usuario
        mockMvc.perform(post("/api/usuario")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isBadRequest());

        Usuario createdUsuario = usuarioService.GetUserByNombreUsuario(usuario.getNombreUsuario());
        assert createdUsuario == null;
    }

    @Test
    public void testNewUsuarioUserName() throws Exception {
        Usuario usuario = new Usuario();

        usuario.setNombre("Juan");
        usuario.setApellidos("Pérez");
        usuario.setCorreo("juan@example.com");
        usuario.setContrasenya("password");

        // Envía la solicitud POST para crear el usuario
        mockMvc.perform(post("/api/usuario")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isBadRequest());

        Usuario createdUsuario = usuarioService.GetUserByNombreUsuario(usuario.getNombreUsuario());
        assert createdUsuario == null;
    }


    /*
    @Test
    public void testGetUsuario() throws Exception {
        // Crear usuarios con correos electrónicos únicos
        Usuario usuario1 = new Usuario();
        usuario1.setNombre("Juan");
        usuario1.setApellidos("Pérez");
        usuario1.setCorreo("juan@example.com");
        usuario1.setNombreUsuario("juanperez");
        usuario1.setContrasenya("password");

        Usuario usuario2 = new Usuario();
        usuario2.setNombre("Pedro");
        usuario2.setApellidos("García");
        usuario2.setCorreo("pedro@example.com");
        usuario2.setNombreUsuario("pedrogarcia");
        usuario2.setContrasenya("password");

        List<Usuario> usuarios = Arrays.asList(usuario1, usuario2);
        usuarios.forEach(usuarioRepository::save);

        mockMvc.perform(get("/api/usuario")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(usuarios.size()));
    }
     */

    @Test
    public void testGetById() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setNombre("Juan");
        usuario.setApellidos("Pérez");
        usuario.setCorreo("juan@example.com");
        usuario.setNombreUsuario("juanperez");
        usuario.setContrasenya("password");
        usuarioService.NewUsuario(usuario);

        mockMvc.perform(get("/api/usuario/{id}", usuario.getIdUsuario())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.idUsuario").value(usuario.getIdUsuario().intValue()))
                .andExpect(jsonPath("$.nombre").value(usuario.getNombre()))
                .andExpect(jsonPath("$.correo").value(usuario.getCorreo()));
    }

    @Test
    public void testGetByNombreUsuario() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setNombre("Juan");
        usuario.setApellidos("Pérez");
        usuario.setCorreo("juan@example.com");
        usuario.setNombreUsuario("juanperez");
        usuario.setContrasenya("password");
        usuario.setNombreUsuario("usuarioPrueba");
        usuarioService.NewUsuario(usuario);

        mockMvc.perform(get("/api/usuario/nombreUsuario/{nombreUsuario}", usuario.getNombreUsuario())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.idUsuario").value(usuario.getIdUsuario().intValue()))
                .andExpect(jsonPath("$.nombreUsuario").value(usuario.getNombreUsuario()))
                .andExpect(jsonPath("$.correo").value(usuario.getCorreo()));
    }


    @Test
    public void testDeleteUsuarioById() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setNombre("Juan");
        usuario.setApellidos("Pérez");
        usuario.setCorreo("juan@example.com");
        usuario.setNombreUsuario("juanperez");
        usuario.setContrasenya("password");
        usuarioService.NewUsuario(usuario);

        mockMvc.perform(delete("/api/usuario/{id}", usuario.getIdUsuario()))
                .andExpect(status().isOk());

        // Verificar que el usuario fue eliminado correctamente de la base de datos
        Optional<Usuario> deletedUsuario = usuarioService.GetUsuarioById(usuario.getIdUsuario());
        assertFalse(deletedUsuario.isPresent());
    }

    @Test
    public void testPostSeguirUsuario() throws Exception {
        // Crear y guardar usuario1
        Usuario usuario1 = new Usuario();
        usuario1.setNombre("Juan");
        usuario1.setApellidos("Pérez");
        usuario1.setCorreo("juan1@example.com");
        usuario1.setNombreUsuario("juanperez1");
        usuario1.setContrasenya("password");
        usuario1.setSeguidores(new ArrayList<>()); // Inicializar lista
        usuario1.setSeguidos(new ArrayList<>());   // Inicializar lista

        // Crear y guardar usuario2
        Usuario usuario2 = new Usuario();
        usuario2.setNombre("Ana");
        usuario2.setApellidos("López");
        usuario2.setCorreo("ana@example.com");
        usuario2.setNombreUsuario("analopez");
        usuario2.setContrasenya("password");
        usuario2.setSeguidores(new ArrayList<>()); // Inicializar lista
        usuario2.setSeguidos(new ArrayList<>());   // Inicializar lista

        // Guardar usuarios en la base de datos
        Usuario savedUsuario1 = usuarioRepository.save(usuario1);
        Usuario savedUsuario2 = usuarioRepository.save(usuario2);

        // Realizar la solicitud POST para que usuario1 siga a usuario2
        mockMvc.perform(post("/api/usuario/{idUsuarioLoggeado}/seguir/{idUsuarioASeguir}",
                        savedUsuario1.getIdUsuario(), savedUsuario2.getIdUsuario())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Verificar que usuario1 está siguiendo a usuario2
        assertTrue(usuarioRepository.findById(savedUsuario2.getIdUsuario())
                .get().getSeguidores().contains(savedUsuario1));
    }


}

