package com.SocialLift.SocialLift.Services;

import com.SocialLift.SocialLift.Models.Peso;
import com.SocialLift.SocialLift.Repositories.PesoRepository;
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

public class PesoServiceTest {

    @InjectMocks
    private PesoService pesoService;

    @Mock
    private PesoRepository pesoRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testNewPeso() {
        Peso peso = new Peso();
        when(pesoRepository.save(peso)).thenReturn(peso);

        Peso result = pesoService.NewPeso(peso);

        assertEquals(peso, result);
        verify(pesoRepository, times(1)).save(peso);
    }

    @Test
    public void testGetPesos() {
        List<Peso> pesos = new ArrayList<>();
        when(pesoRepository.findAll()).thenReturn(pesos);

        List<Peso> result = pesoService.GetPesos();

        assertEquals(pesos, result);
        verify(pesoRepository, times(1)).findAll();
    }

    @Test
    public void testGetPesoById() {
        Long id = 1L;
        Peso peso = new Peso();
        peso.setIdPeso(id);
        Optional<Peso> optionalPeso = Optional.of(peso);
        when(pesoRepository.findById(id)).thenReturn(optionalPeso);

        Optional<Peso> result = pesoService.GetPesoById(id);

        assertEquals(optionalPeso, result);
        verify(pesoRepository, times(1)).findById(id);
    }

    @Test
    public void testUpdatePeso() {
        Peso peso = new Peso();

        pesoService.UpdatePeso(peso);

        verify(pesoRepository, times(1)).save(peso);
    }

    @Test
    public void testDeletePeso() {
        Long id = 1L;

        pesoService.DeletePeso(id);

        verify(pesoRepository, times(1)).deleteById(id);
    }
}
