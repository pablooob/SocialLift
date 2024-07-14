package com.SocialLift.SocialLift.Integration;

import com.SocialLift.SocialLift.Repositories.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.SocialLift.SocialLift.Models.PlantillaRutina;
import com.SocialLift.SocialLift.Models.Usuario;

import static org.junit.jupiter.api.Assertions.*;
import com.SocialLift.SocialLift.Services.PlantillaRutinaService;
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
public class PlantillaRutinaControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PlantillaRutinaService plantillaRutinaService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    public void testNewPlantillaRutina() throws Exception {

        PlantillaRutina plantillaRutina = new PlantillaRutina();
        plantillaRutina.setNombre("Plantilla Rutina 1");
        plantillaRutina.setDescripcion("Descripción de la plantilla rutina 1");
        plantillaRutina.setTipo("Tipo 1");

        mockMvc.perform(post("/api/plantillaRutina")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(plantillaRutina)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idPlantillaRutina", notNullValue()))
                .andExpect(jsonPath("$.nombre", is("Plantilla Rutina 1")))
                .andExpect(jsonPath("$.descripcion", is("Descripción de la plantilla rutina 1")))
                .andExpect(jsonPath("$.tipo", is("Tipo 1")));
    }

    @Test
    public void testGetPlantillaRutinasPorIdUsuario() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setNombre("Juan");
        usuario.setApellidos("Pérez");
        usuario.setCorreo("juan@example.com");
        usuario.setNombreUsuario("juanperez");
        usuario.setContrasenya("password");
        usuario = usuarioRepository.save(usuario);

        PlantillaRutina plantillaRutina1 = new PlantillaRutina();
        plantillaRutina1.setNombre("Plantilla Rutina Usuario 1");
        plantillaRutina1.setDescripcion("Descripción de la plantilla rutina usuario 1");
        plantillaRutina1.setTipo("Tipo Usuario");
        plantillaRutina1.setUsuario(usuario);
        plantillaRutinaService.NewPlantillaRutina(plantillaRutina1);

        PlantillaRutina plantillaRutina2 = new PlantillaRutina();
        plantillaRutina2.setNombre("Plantilla Rutina Usuario 2");
        plantillaRutina2.setDescripcion("Descripción de la plantilla rutina usuario 2");
        plantillaRutina2.setTipo("Tipo Usuario");
        plantillaRutina2.setUsuario(usuario);
        plantillaRutinaService.NewPlantillaRutina(plantillaRutina2);

        mockMvc.perform(get("/api/plantillaRutina/byUserID/{id}", usuario.getIdUsuario())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].nombre", is("Plantilla Rutina Usuario 1")))
                .andExpect(jsonPath("$[1].nombre", is("Plantilla Rutina Usuario 2")));
    }

    @Test
    public void testGetRutinasPorUsuarioGuardados() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setNombre("Juan");
        usuario.setApellidos("Pérez");
        usuario.setCorreo("juan@example.com");
        usuario.setNombreUsuario("juanperez");
        usuario.setContrasenya("password");
        usuario = usuarioRepository.save(usuario);

        PlantillaRutina plantillaRutina1 = new PlantillaRutina();
        plantillaRutina1.setNombre("Plantilla Rutina Guardada 1");
        plantillaRutina1.setDescripcion("Descripción de la plantilla rutina guardada 1");
        plantillaRutina1.setTipo("Tipo Guardado");
        plantillaRutina1.setUsuario(usuario);
        plantillaRutina1.setUsuarioGuardados(new ArrayList<Usuario>());
        plantillaRutinaService.NewPlantillaRutina(plantillaRutina1);

        plantillaRutinaService.AñadirRutinaGuardadaById(usuario.getIdUsuario(), plantillaRutina1.getIdPlantillaRutina());

        mockMvc.perform(get("/api/plantillaRutina/byUsuarioGuardadoID/{id}", usuario.getIdUsuario())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].nombre", is("Plantilla Rutina Guardada 1")));
    }

    /*
    @Test
    public void testGetPlantillaRutinas() throws Exception {
        PlantillaRutina plantillaRutina = new PlantillaRutina();
        plantillaRutina.setNombre("Plantilla Rutina 2");
        plantillaRutina.setDescripcion("Descripción de la plantilla rutina 2");
        plantillaRutina.setTipo("Tipo 2");
        plantillaRutinaService.NewPlantillaRutina(plantillaRutina);

        mockMvc.perform(get("/api/plantillaRutina")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(1))))
                .andExpect(jsonPath("$[0].nombre", is("Plantilla Rutina 2")));
    }

     */

    @Test
    public void testGetPlantillaRutinaById() throws Exception {
        PlantillaRutina plantillaRutina = new PlantillaRutina();
        plantillaRutina.setNombre("Plantilla Rutina 3");
        plantillaRutina.setDescripcion("Descripción de la plantilla rutina 3");
        plantillaRutina.setTipo("Tipo 3");
        plantillaRutina = plantillaRutinaService.NewPlantillaRutina(plantillaRutina);

        mockMvc.perform(get("/api/plantillaRutina/{id}", plantillaRutina.getIdPlantillaRutina())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.idPlantillaRutina", is(plantillaRutina.getIdPlantillaRutina().intValue())))
                .andExpect(jsonPath("$.nombre", is("Plantilla Rutina 3")))
                .andExpect(jsonPath("$.descripcion", is("Descripción de la plantilla rutina 3")))
                .andExpect(jsonPath("$.tipo", is("Tipo 3")));
    }

    @Test
    public void testGetByNombreContaining() throws Exception {
        PlantillaRutina plantillaRutina1 = new PlantillaRutina();
        plantillaRutina1.setNombre("Plantilla prueba integracion Rutina 4");
        plantillaRutina1.setDescripcion("Descripción de la plantilla rutina 4");
        plantillaRutina1.setTipo("Tipo 4");
        plantillaRutinaService.NewPlantillaRutina(plantillaRutina1);

        PlantillaRutina plantillaRutina2 = new PlantillaRutina();
        plantillaRutina2.setNombre("Plantilla prueba integracion Rutina 5");
        plantillaRutina2.setDescripcion("Descripción de la plantilla rutina 5");
        plantillaRutina2.setTipo("Tipo 5");
        plantillaRutinaService.NewPlantillaRutina(plantillaRutina2);

        mockMvc.perform(get("/api/plantillaRutina/startWith/Plantilla prueba integracion")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].nombre", is("Plantilla prueba integracion Rutina 4")))
                .andExpect(jsonPath("$[1].nombre", is("Plantilla prueba integracion Rutina 5")));
    }

    @Test
    public void testUpdatePlantillaRutina() throws Exception {
        PlantillaRutina plantillaRutina = new PlantillaRutina();
        plantillaRutina.setNombre("Plantilla Rutina 6");
        plantillaRutina.setDescripcion("Descripción de la plantilla rutina 6");
        plantillaRutina.setTipo("Tipo 6");
        plantillaRutina = plantillaRutinaService.NewPlantillaRutina(plantillaRutina);

        plantillaRutina.setDescripcion("Nueva descripción de la plantilla rutina 6");

        mockMvc.perform(put("/api/plantillaRutina")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(plantillaRutina)))
                .andExpect(status().isOk());

        PlantillaRutina updatedPlantillaRutina = plantillaRutinaService.GetPlantillaRutinaById(plantillaRutina.getIdPlantillaRutina()).orElse(null);
        assert updatedPlantillaRutina != null;
        assertEquals(plantillaRutina.getDescripcion(), updatedPlantillaRutina.getDescripcion());
    }

    @Test
    public void testUpdateAñadirGuardarRutina() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setNombre("Juan");
        usuario.setApellidos("Pérez");
        usuario.setCorreo("juan@example.com");
        usuario.setNombreUsuario("juanperez");
        usuario.setContrasenya("password");
        usuario = usuarioRepository.save(usuario);

        PlantillaRutina plantillaRutina = new PlantillaRutina();
        plantillaRutina.setNombre("Plantilla Rutina 7");
        plantillaRutina.setDescripcion("Descripción de la plantilla rutina 7");
        plantillaRutina.setTipo("Tipo 7");
        plantillaRutina.setUsuario(usuario);
        plantillaRutina.setUsuarioGuardados(new ArrayList<Usuario>());
        plantillaRutina = plantillaRutinaService.NewPlantillaRutina(plantillaRutina);

        mockMvc.perform(put("/api/plantillaRutina/AñadirGuardarRutina/{idUsuario}/{idRutina}", usuario.getIdUsuario(), plantillaRutina.getIdPlantillaRutina()))
                .andExpect(status().isOk());

        List<PlantillaRutina> plantillaRutinasGuardadas = plantillaRutinaService.obtenerRutinasPorUsuarioGuardados(usuario.getIdUsuario());
        assertEquals(1, plantillaRutinasGuardadas.size());
        assertEquals(plantillaRutina.getIdPlantillaRutina(), plantillaRutinasGuardadas.get(0).getIdPlantillaRutina());
    }

    @Test
    public void testUpdateEliminarGuardarRutina() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setNombre("Juan");
        usuario.setApellidos("Pérez");
        usuario.setCorreo("juan@example.com");
        usuario.setNombreUsuario("juanperez");
        usuario.setContrasenya("password");
        usuario = usuarioRepository.save(usuario);

        PlantillaRutina plantillaRutina = new PlantillaRutina();
        plantillaRutina.setNombre("Plantilla Rutina 8");
        plantillaRutina.setDescripcion("Descripción de la plantilla rutina 8");
        plantillaRutina.setTipo("Tipo 8");
        plantillaRutina.setUsuario(usuario);
        plantillaRutina.setUsuarioGuardados(new ArrayList<Usuario>());
        plantillaRutina = plantillaRutinaService.NewPlantillaRutina(plantillaRutina);
        plantillaRutinaService.AñadirRutinaGuardadaById(usuario.getIdUsuario(), plantillaRutina.getIdPlantillaRutina());

        mockMvc.perform(put("/api/plantillaRutina/EliminarGuardarRutina/{idUsuario}/{idRutina}", usuario.getIdUsuario(), plantillaRutina.getIdPlantillaRutina()))
                .andExpect(status().isOk());

        List<PlantillaRutina> plantillaRutinasGuardadas = plantillaRutinaService.obtenerRutinasPorUsuarioGuardados(usuario.getIdUsuario());
        assertEquals(0, plantillaRutinasGuardadas.size());
    }

    @Test
    public void testDeletePlantillaRutina() throws Exception {
        PlantillaRutina plantillaRutina = new PlantillaRutina();
        plantillaRutina.setNombre("Plantilla Rutina 9");
        plantillaRutina.setDescripcion("Descripción de la plantilla rutina 9");
        plantillaRutina.setTipo("Tipo 9");
        plantillaRutina = plantillaRutinaService.NewPlantillaRutina(plantillaRutina);

        mockMvc.perform(delete("/api/plantillaRutina/{id}", plantillaRutina.getIdPlantillaRutina()))
                .andExpect(status().isOk());

        PlantillaRutina deletedPlantillaRutina = plantillaRutinaService.GetPlantillaRutinaById(plantillaRutina.getIdPlantillaRutina()).orElse(null);
        assertNull(deletedPlantillaRutina);
    }
}
