package com.SocialLift.SocialLift.Services;

import com.SocialLift.SocialLift.Models.PlantillaRutina;
import com.SocialLift.SocialLift.Models.Usuario;
import com.SocialLift.SocialLift.Repositories.PlantillaRutinaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PlantillaRutinaServiceTest {

    @InjectMocks
    private PlantillaRutinaService plantillaRutinaService;

    @Mock
    private PlantillaRutinaRepository plantillaRutinaRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testNewPlantillaRutina() {
        PlantillaRutina plantillaRutina = new PlantillaRutina();
        when(plantillaRutinaRepository.save(plantillaRutina)).thenReturn(plantillaRutina);

        PlantillaRutina result = plantillaRutinaService.NewPlantillaRutina(plantillaRutina);

        assertEquals(plantillaRutina, result);
        verify(plantillaRutinaRepository, times(1)).save(plantillaRutina);
    }

    @Test
    public void testGetPlantillaRutinas() {
        List<PlantillaRutina> plantillaRutinas = new ArrayList<>();
        when(plantillaRutinaRepository.findAll()).thenReturn(plantillaRutinas);

        List<PlantillaRutina> result = plantillaRutinaService.GetPlantillaRutinas();

        assertEquals(plantillaRutinas, result);
        verify(plantillaRutinaRepository, times(1)).findAll();
    }

    @Test
    public void testGetPlantillaRutinaById() {
        Long id = 1L;
        PlantillaRutina plantillaRutina = new PlantillaRutina();
        plantillaRutina.setIdPlantillaRutina(id);
        Optional<PlantillaRutina> optionalPlantillaRutina = Optional.of(plantillaRutina);
        when(plantillaRutinaRepository.findById(id)).thenReturn(optionalPlantillaRutina);

        Optional<PlantillaRutina> result = plantillaRutinaService.GetPlantillaRutinaById(id);

        assertEquals(optionalPlantillaRutina, result);
        verify(plantillaRutinaRepository, times(1)).findById(id);
    }

    @Test
    public void testGetPlantillaRutinasByUsuarioId() {
        Long idUsuario = 1L;
        List<PlantillaRutina> plantillaRutinas = new ArrayList<>();
        when(plantillaRutinaRepository.findByUsuarioIdUsuario(idUsuario)).thenReturn(plantillaRutinas);

        List<PlantillaRutina> result = plantillaRutinaService.getPlantillaRutinasByUsuarioId(idUsuario);

        assertEquals(plantillaRutinas, result);
        verify(plantillaRutinaRepository, times(1)).findByUsuarioIdUsuario(idUsuario);
    }

    @Test
    public void testFindByNombreContaining() {
        String nombre = "Rutina";
        List<PlantillaRutina> plantillaRutinas = new ArrayList<>();
        when(plantillaRutinaRepository.findByNombreContaining(nombre)).thenReturn(plantillaRutinas);

        List<PlantillaRutina> result = plantillaRutinaService.findByNombreContaining(nombre);

        assertEquals(plantillaRutinas, result);
        verify(plantillaRutinaRepository, times(1)).findByNombreContaining(nombre);
    }

    @Test
    public void testObtenerRutinasPorUsuarioGuardados() {
        Long idUsuario = 1L;
        List<PlantillaRutina> plantillaRutinas = new ArrayList<>();
        when(plantillaRutinaRepository.findByUsuarioGuardadosIdUsuario(idUsuario)).thenReturn(plantillaRutinas);

        List<PlantillaRutina> result = plantillaRutinaService.obtenerRutinasPorUsuarioGuardados(idUsuario);

        assertEquals(plantillaRutinas, result);
        verify(plantillaRutinaRepository, times(1)).findByUsuarioGuardadosIdUsuario(idUsuario);
    }

    @Test
    public void testUpdatePlantillaRutina() {
        PlantillaRutina plantillaRutina = new PlantillaRutina();

        plantillaRutinaService.UpdatePlantillaRutina(plantillaRutina);

        verify(plantillaRutinaRepository, times(1)).save(plantillaRutina);
    }

    @Test
    public void testDeletePlantillaRutina() {
        Long id = 1L;

        plantillaRutinaService.DeletePlantillaRutina(id);

        verify(plantillaRutinaRepository, times(1)).deleteById(id);
    }

    @Test
    public void testAñadirRutinaGuardadaById() {
        Long idUsuario = 1L;
        Long idRutina = 1L;
        PlantillaRutina plantillaRutina = new PlantillaRutina();
        plantillaRutina.setIdPlantillaRutina(idRutina);
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(idUsuario);
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(usuario);
        plantillaRutina.setUsuarioGuardados(usuarios);

        when(plantillaRutinaRepository.findById(idRutina)).thenReturn(Optional.of(plantillaRutina));

        plantillaRutinaService.AñadirRutinaGuardadaById(idUsuario, idRutina);

        assertEquals(1, plantillaRutina.getUsuarioGuardados().size());
    }

    @Test
    public void testEliminarRutinaGuardadaById() {
        Long idUsuario = 1L;
        Long idRutina = 1L;
        PlantillaRutina plantillaRutina = new PlantillaRutina();
        plantillaRutina.setIdPlantillaRutina(idRutina);
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(idUsuario);
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(usuario);
        plantillaRutina.setUsuarioGuardados(usuarios);

        when(plantillaRutinaRepository.findById(idRutina)).thenReturn(Optional.of(plantillaRutina));

        plantillaRutinaService.EliminarRutinaGuardadaById(idUsuario, idRutina);

        assertEquals(0, plantillaRutina.getUsuarioGuardados().size());
        verify(plantillaRutinaRepository, times(1)).save(plantillaRutina);
    }
}
