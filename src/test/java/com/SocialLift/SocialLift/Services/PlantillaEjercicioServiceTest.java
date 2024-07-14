package com.SocialLift.SocialLift.Services;

import com.SocialLift.SocialLift.Models.PlantillaEjercicio;
import com.SocialLift.SocialLift.Repositories.PlantillaEjercicioRepository;
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

public class PlantillaEjercicioServiceTest {

    @InjectMocks
    private PlantillaEjercicioService plantillaEjercicioService;

    @Mock
    private PlantillaEjercicioRepository plantillaEjercicioRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testNewPlantillaEjercicio() {
        PlantillaEjercicio plantillaEjercicio = new PlantillaEjercicio();
        when(plantillaEjercicioRepository.save(plantillaEjercicio)).thenReturn(plantillaEjercicio);

        PlantillaEjercicio result = plantillaEjercicioService.NewPlantillaEjercicio(plantillaEjercicio);

        assertEquals(plantillaEjercicio, result);
        verify(plantillaEjercicioRepository, times(1)).save(plantillaEjercicio);
    }

    @Test
    public void testGetPlantillaEjercicios() {
        List<PlantillaEjercicio> plantillaEjercicios = new ArrayList<>();
        when(plantillaEjercicioRepository.findAll()).thenReturn(plantillaEjercicios);

        List<PlantillaEjercicio> result = plantillaEjercicioService.GetPlantillaEjercicios();

        assertEquals(plantillaEjercicios, result);
        verify(plantillaEjercicioRepository, times(1)).findAll();
    }

    @Test
    public void testGetPlantillaEjercicioById() {
        Long id = 1L;
        PlantillaEjercicio plantillaEjercicio = new PlantillaEjercicio();
        plantillaEjercicio.setId(id);
        Optional<PlantillaEjercicio> optionalPlantillaEjercicio = Optional.of(plantillaEjercicio);
        when(plantillaEjercicioRepository.findById(id)).thenReturn(optionalPlantillaEjercicio);

        Optional<PlantillaEjercicio> result = plantillaEjercicioService.GetPlantillaEjercicioById(id);

        assertEquals(optionalPlantillaEjercicio, result);
        verify(plantillaEjercicioRepository, times(1)).findById(id);
    }

    @Test
    public void testGetPlantillaEjerciciosByUsuarioId() {
        Long idUsuario = 1L;
        List<PlantillaEjercicio> plantillaEjercicios = new ArrayList<>();
        when(plantillaEjercicioRepository.findByUsuarioIdUsuario(idUsuario)).thenReturn(plantillaEjercicios);

        List<PlantillaEjercicio> result = plantillaEjercicioService.getPlantillaEjerciciosByUsuarioId(idUsuario);

        assertEquals(plantillaEjercicios, result);
        verify(plantillaEjercicioRepository, times(1)).findByUsuarioIdUsuario(idUsuario);
    }

    @Test
    public void testUpdatePlantillaEjercicio() {
        PlantillaEjercicio plantillaEjercicio = new PlantillaEjercicio();

        plantillaEjercicioService.UpdatePlantillaEjercicio(plantillaEjercicio);

        verify(plantillaEjercicioRepository, times(1)).save(plantillaEjercicio);
    }

    @Test
    public void testDeletePlantillaEjercicio() {
        Long id = 1L;

        plantillaEjercicioService.DeletePlantillaEjercicio(id);

        verify(plantillaEjercicioRepository, times(1)).deleteById(id);
    }
}
