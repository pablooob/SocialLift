package com.SocialLift.SocialLift.Integration;

import com.SocialLift.SocialLift.Repositories.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.SocialLift.SocialLift.Models.PlantillaEjercicio;
import com.SocialLift.SocialLift.Models.Usuario;

import static org.junit.jupiter.api.Assertions.*;
import com.SocialLift.SocialLift.Services.PlantillaEjercicioService;
import com.SocialLift.SocialLift.Services.UsuarioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class PlantillaEjercicioControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PlantillaEjercicioService plantillaEjercicioService;

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    public void testCrearNuevoPlantillaEjercicio() throws Exception {

        PlantillaEjercicio plantillaEjercicio = new PlantillaEjercicio();
        plantillaEjercicio.setNombre("Plantilla 1");
        plantillaEjercicio.setDescripcion("Descripción de la plantilla 1");
        plantillaEjercicio.setTipo("Tipo 1");

        mockMvc.perform(post("/api/plantillaEjercicio/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(plantillaEjercicio)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idPlantillaEjercicio", notNullValue()))
                .andExpect(jsonPath("$.nombre", is("Plantilla 1")))
                .andExpect(jsonPath("$.descripcion", is("Descripción de la plantilla 1")))
                .andExpect(jsonPath("$.tipo", is("Tipo 1")));
    }

    @Test
    public void testGetPlantillaEjercicioPorId() throws Exception {
        PlantillaEjercicio plantillaEjercicio = new PlantillaEjercicio();
        plantillaEjercicio.setNombre("Plantilla 2");
        plantillaEjercicio.setDescripcion("Descripción de la plantilla 2");
        plantillaEjercicio.setTipo("Tipo 2");
        plantillaEjercicio = plantillaEjercicioService.NewPlantillaEjercicio(plantillaEjercicio);

        mockMvc.perform(get("/api/plantillaEjercicio/{id}", plantillaEjercicio.getIdPlantillaEjercicio())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.idPlantillaEjercicio", is(plantillaEjercicio.getIdPlantillaEjercicio().intValue())))
                .andExpect(jsonPath("$.nombre", is("Plantilla 2")))
                .andExpect(jsonPath("$.descripcion", is("Descripción de la plantilla 2")))
                .andExpect(jsonPath("$.tipo", is("Tipo 2")));
    }

    @Test
    public void testActualizarPlantillaEjercicio() throws Exception {
        PlantillaEjercicio plantillaEjercicio = new PlantillaEjercicio();
        plantillaEjercicio.setNombre("Plantilla 3");
        plantillaEjercicio.setDescripcion("Descripción de la plantilla 3");
        plantillaEjercicio.setTipo("Tipo 3");
        plantillaEjercicio = plantillaEjercicioService.NewPlantillaEjercicio(plantillaEjercicio);

        plantillaEjercicio.setDescripcion("Nueva descripción de la plantilla 3");

        mockMvc.perform(put("/api/plantillaEjercicio/{id}", plantillaEjercicio.getIdPlantillaEjercicio())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(plantillaEjercicio)))
                .andExpect(status().isOk())
                .andExpect(content().string("Ejercicio actualizado exitosamente"));

        PlantillaEjercicio updatedPlantillaEjercicio = plantillaEjercicioService.GetPlantillaEjercicioById(plantillaEjercicio.getIdPlantillaEjercicio()).orElse(null);
        assert updatedPlantillaEjercicio != null;
        assertEquals(plantillaEjercicio.getDescripcion(), updatedPlantillaEjercicio.getDescripcion());
    }

    @Test
    public void testEliminarPlantillaEjercicio() throws Exception {
        PlantillaEjercicio plantillaEjercicio = new PlantillaEjercicio();
        plantillaEjercicio.setNombre("Plantilla 4");
        plantillaEjercicio.setDescripcion("Descripción de la plantilla 4");
        plantillaEjercicio.setTipo("Tipo 4");
        plantillaEjercicio = plantillaEjercicioService.NewPlantillaEjercicio(plantillaEjercicio);

        mockMvc.perform(delete("/api/plantillaEjercicio/{id}", plantillaEjercicio.getIdPlantillaEjercicio()))
                .andExpect(status().isOk())
                .andExpect(content().string("Ejercicio eliminado exitosamente"));

        PlantillaEjercicio deletedPlantillaEjercicio = plantillaEjercicioService.GetPlantillaEjercicioById(plantillaEjercicio.getIdPlantillaEjercicio()).orElse(null);
        assertNull(deletedPlantillaEjercicio);
    }

    @Test
    public void testGetPlantillaEjerciciosPorIdUsuario() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setNombre("Juan");
        usuario.setApellidos("Pérez");
        usuario.setCorreo("juan@example.com");
        usuario.setNombreUsuario("juanperez");
        usuario.setContrasenya("password");
        usuario = usuarioRepository.save(usuario);

        PlantillaEjercicio plantillaEjercicio1 = new PlantillaEjercicio();
        plantillaEjercicio1.setNombre("Plantilla Usuario 1");
        plantillaEjercicio1.setDescripcion("Descripción de la plantilla usuario 1");
        plantillaEjercicio1.setTipo("Tipo Usuario");
        plantillaEjercicio1.setUsuario(usuario);
        plantillaEjercicioService.NewPlantillaEjercicio(plantillaEjercicio1);

        PlantillaEjercicio plantillaEjercicio2 = new PlantillaEjercicio();
        plantillaEjercicio2.setNombre("Plantilla Usuario 2");
        plantillaEjercicio2.setDescripcion("Descripción de la plantilla usuario 2");
        plantillaEjercicio2.setTipo("Tipo Usuario");
        plantillaEjercicio2.setUsuario(usuario);
        plantillaEjercicioService.NewPlantillaEjercicio(plantillaEjercicio2);

        mockMvc.perform(get("/api/plantillaEjercicio/byUserID/{id}", usuario.getIdUsuario())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].nombre", is("Plantilla Usuario 1")))
                .andExpect(jsonPath("$[1].nombre", is("Plantilla Usuario 2")));
    }
}
