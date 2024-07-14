package com.SocialLift.SocialLift.Services;

import com.SocialLift.SocialLift.Models.IMC;
import com.SocialLift.SocialLift.Repositories.IMCRepository;
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

public class IMCServiceTest {

    @InjectMocks
    private IMCService imcService;

    @Mock
    private IMCRepository imcRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testNewIMC() {
        IMC imc = new IMC();
        when(imcRepository.save(imc)).thenReturn(imc);

        IMC result = imcService.NewIMC(imc);

        assertEquals(imc, result);
        verify(imcRepository, times(1)).save(imc);
    }

    @Test
    public void testGetIMCs() {
        List<IMC> imcs = new ArrayList<>();
        when(imcRepository.findAll()).thenReturn(imcs);

        List<IMC> result = imcService.GetIMCs();

        assertEquals(imcs, result);
        verify(imcRepository, times(1)).findAll();
    }

    @Test
    public void testGetIMCById() {
        Long id = 1L;
        IMC imc = new IMC();
        imc.setIdIMC(id);
        Optional<IMC> optionalIMC = Optional.of(imc);
        when(imcRepository.findById(id)).thenReturn(optionalIMC);

        Optional<IMC> result = imcService.GetIMCById(id);

        assertEquals(optionalIMC, result);
        verify(imcRepository, times(1)).findById(id);
    }

    @Test
    public void testUpdateIMC() {
        IMC imc = new IMC();

        imcService.UpdateIMC(imc);

        verify(imcRepository, times(1)).save(imc);
    }

    @Test
    public void testDeleteIMC() {
        Long id = 1L;

        imcService.DeleteIMC(id);

        verify(imcRepository, times(1)).deleteById(id);
    }
}
