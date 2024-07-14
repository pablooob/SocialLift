package com.SocialLift.SocialLift.Services;

import com.SocialLift.SocialLift.Models.Ejercicio;
import com.SocialLift.SocialLift.Repositories.EjercicioRepository;
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

public class EjercicioServiceTest {

    @InjectMocks
    private EjercicioService ejercicioService;

    @Mock
    private EjercicioRepository ejercicioRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testNewEjercicio() {
        Ejercicio ejercicio = new Ejercicio();
        when(ejercicioRepository.save(ejercicio)).thenReturn(ejercicio);

        Ejercicio result = ejercicioService.NewEjercicio(ejercicio);

        assertEquals(ejercicio, result);
        verify(ejercicioRepository, times(1)).save(ejercicio);
    }

    @Test
    public void testGetEjercicios() {
        List<Ejercicio> ejercicios = new ArrayList<>();
        when(ejercicioRepository.findAll()).thenReturn(ejercicios);

        List<Ejercicio> result = ejercicioService.GetEjercicios();

        assertEquals(ejercicios, result);
        verify(ejercicioRepository, times(1)).findAll();
    }

    @Test
    public void testGetEjercicioById() {
        Long id = 1L;
        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setId(id);
        Optional<Ejercicio> optionalEjercicio = Optional.of(ejercicio);
        when(ejercicioRepository.findById(id)).thenReturn(optionalEjercicio);

        Optional<Ejercicio> result = ejercicioService.GetEjercicioById(id);

        assertEquals(optionalEjercicio, result);
        verify(ejercicioRepository, times(1)).findById(id);
    }

    @Test
    public void testUpdateEjercicio() {
        Ejercicio ejercicio = new Ejercicio();

        ejercicioService.UpdateEjercicio(ejercicio);

        verify(ejercicioRepository, times(1)).save(ejercicio);
    }

    @Test
    public void testDeleteEjercicio() {
        Long id = 1L;

        ejercicioService.DeleteEjercicio(id);

        verify(ejercicioRepository, times(1)).deleteById(id);
    }
}
