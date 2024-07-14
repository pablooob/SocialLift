package com.SocialLift.SocialLift.Integration;

import com.SocialLift.SocialLift.Models.Rutina;
import com.SocialLift.SocialLift.Models.Usuario;
import com.SocialLift.SocialLift.Repositories.UsuarioRepository;
import com.SocialLift.SocialLift.Services.RutinaService;
import static org.junit.jupiter.api.Assertions.*;
import com.SocialLift.SocialLift.Services.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class RutinaControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RutinaService rutinaService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Test
    public void testCrearNuevaRutina() throws Exception {
        Rutina rutina = new Rutina();
        rutina.setNombre("Rutina 1");
        rutina.setDescripcion("Descripción de la rutina 1");
        rutina.setFecha(new Date());

        mockMvc.perform(post("/api/rutina/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(rutina)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idRutina", notNullValue()))
                .andExpect(jsonPath("$.nombre", is("Rutina 1")))
                .andExpect(jsonPath("$.descripcion", is("Descripción de la rutina 1")));
    }

    /*
    @Test
    public void testGetRutinas() throws Exception {
        Rutina rutina = new Rutina();
        rutina.setNombre("Rutina 2");
        rutina.setDescripcion("Descripción de la rutina 2");
        rutina.setFecha(new Date());
        rutinaService.NewRutina(rutina);

        mockMvc.perform(get("/api/rutina/all")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(1))))
                .andExpect(jsonPath("$[0].nombre", is("Rutina 2")));
    }

     */

    @Test
    public void testGetRutinaById() throws Exception {
        Rutina rutina = new Rutina();
        rutina.setNombre("Rutina 3");
        rutina.setDescripcion("Descripción de la rutina 3");
        rutina.setFecha(new Date());
        rutina = rutinaService.NewRutina(rutina);

        mockMvc.perform(get("/api/rutina/{id}", rutina.getIdRutina())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.idRutina", is(rutina.getIdRutina().intValue())))
                .andExpect(jsonPath("$.nombre", is("Rutina 3")))
                .andExpect(jsonPath("$.descripcion", is("Descripción de la rutina 3")));
    }

    @Test
    public void testActualizarRutina() throws Exception {
        Rutina rutina = new Rutina();
        rutina.setNombre("Rutina 4");
        rutina.setDescripcion("Descripción de la rutina 4");
        rutina.setFecha(new Date());
        rutina = rutinaService.NewRutina(rutina);

        rutina.setDescripcion("Nueva descripción de la rutina 4");

        mockMvc.perform(put("/api/rutina/{id}", rutina.getIdRutina())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(rutina)))
                .andExpect(status().isOk());

        Rutina updatedRutina = rutinaService.GetRutinaById(rutina.getIdRutina()).orElse(null);
        assert updatedRutina != null;
        assertEquals(rutina.getDescripcion(), updatedRutina.getDescripcion());
    }

    @Test
    public void testEliminarRutina() throws Exception {
        Rutina rutina = new Rutina();
        rutina.setNombre("Rutina 5");
        rutina.setDescripcion("Descripción de la rutina 5");
        rutina.setFecha(new Date());
        rutina = rutinaService.NewRutina(rutina);

        mockMvc.perform(delete("/api/rutina/{id}", rutina.getIdRutina()))
                .andExpect(status().isOk());

        Rutina deletedRutina = rutinaService.GetRutinaById(rutina.getIdRutina()).orElse(null);
        assertNull(deletedRutina);
    }

    @Test
    public void testGetRutinasPorIdUsuario() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setNombre("Juan");
        usuario.setApellidos("Pérez");
        usuario.setCorreo("juan@example.com");
        usuario.setNombreUsuario("juanperez");
        usuario.setContrasenya("password");
        usuario = usuarioRepository.save(usuario);

        Rutina rutina1 = new Rutina();
        rutina1.setNombre("Rutina Usuario 1");
        rutina1.setDescripcion("Descripción de la rutina usuario 1");
        rutina1.setFecha(new Date());
        rutina1.setUsuario(usuario);
        rutinaService.NewRutina(rutina1);

        Rutina rutina2 = new Rutina();
        rutina2.setNombre("Rutina Usuario 2");
        rutina2.setDescripcion("Descripción de la rutina usuario 2");
        rutina2.setFecha(new Date());
        rutina2.setUsuario(usuario);
        rutinaService.NewRutina(rutina2);

        mockMvc.perform(get("/api/rutina/byUserID/{id}", usuario.getIdUsuario())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].nombre", is("Rutina Usuario 1")))
                .andExpect(jsonPath("$[1].nombre", is("Rutina Usuario 2")));
    }
    @Test
    public void testGetRutinasSeguidosByUserId() throws Exception {
        // Crear usuario 1 y usuario 2
        Usuario usuario1 = new Usuario();
        usuario1.setNombre("Usuario 1");
        usuario1.setApellidos("Apellido 1");
        usuario1.setCorreo("usuario1@example.com");
        usuario1.setNombreUsuario("usuario1");
        usuario1.setContrasenya("password1");
        usuario1.setSeguidos(new ArrayList<>());
        usuario1.setSeguidores(new ArrayList<>());
        usuario1 = usuarioRepository.save(usuario1);

        Usuario usuario2 = new Usuario();
        usuario2.setNombre("Usuario 2");
        usuario2.setApellidos("Apellido 2");
        usuario2.setCorreo("usuario2@example.com");
        usuario2.setNombreUsuario("usuario2");
        usuario2.setContrasenya("password2");
        usuario2.setSeguidos(new ArrayList<>());
        usuario2.setSeguidores(new ArrayList<>());
        usuario2 = usuarioRepository.save(usuario2);

        // Establecer relación de seguimiento
        usuarioService.SeguirUsuarioById(usuario1.getIdUsuario(), usuario2.getIdUsuario());

        // Verificar relaciones
        Usuario usuario1Actualizado = usuarioRepository.findById(usuario1.getIdUsuario()).orElseThrow(() -> new Exception("Usuario 1 no encontrado"));
        Usuario usuario2Actualizado = usuarioRepository.findById(usuario2.getIdUsuario()).orElseThrow(() -> new Exception("Usuario 2 no encontrado"));

        assertTrue(usuario1Actualizado.getSeguidos().contains(usuario2Actualizado));
        assertTrue(usuario2Actualizado.getSeguidores().contains(usuario1Actualizado));

        // Crear y guardar rutina para usuario 2
        Rutina rutinaUsuario2 = new Rutina();
        rutinaUsuario2.setNombre("Rutina Seguida 1");
        rutinaUsuario2.setDescripcion("Descripción de la rutina seguida 1");
        rutinaUsuario2.setFecha(new Date());
        rutinaUsuario2.setUsuario(usuario2Actualizado);  // Usar el usuario actualizado
        rutinaUsuario2.setIsPublic(true);  // Asegurarse de que la rutina es pública
        rutinaService.NewRutina(rutinaUsuario2);

        // Verificar que la rutina se ha guardado correctamente
        List<Rutina> rutinasGuardadas = rutinaService.GetRutinas();
        assertTrue(rutinasGuardadas.stream().anyMatch(r -> r.getNombre().equals("Rutina Seguida 1") && r.getUsuario().getIdUsuario().equals(usuario2Actualizado.getIdUsuario())));

        // Realizar la solicitud GET y verificar la respuesta
        mockMvc.perform(get("/api/rutina/{idUsuario}/rutinasSeguidos", usuario1.getIdUsuario())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].nombre", is("Rutina Seguida 1")));
    }



}
