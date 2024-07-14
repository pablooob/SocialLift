package com.SocialLift.SocialLift.Services;

import com.SocialLift.SocialLift.Models.MedidasCorporales;
import com.SocialLift.SocialLift.Repositories.MedidasCorporalesRepository;
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

public class MedidasCorporalesServiceTest {

    @InjectMocks
    private MedidasCorporalesService medidasCorporalesService;

    @Mock
    private MedidasCorporalesRepository medidasCorporalesRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testNewMedidasCorporales() {
        MedidasCorporales medidasCorporales = new MedidasCorporales();
        when(medidasCorporalesRepository.save(medidasCorporales)).thenReturn(medidasCorporales);

        MedidasCorporales result = medidasCorporalesService.NewMedidasCorporales(medidasCorporales);

        assertEquals(medidasCorporales, result);
        verify(medidasCorporalesRepository, times(1)).save(medidasCorporales);
    }

    @Test
    public void testGetMedidasCorporales() {
        List<MedidasCorporales> medidasCorporalesList = new ArrayList<>();
        when(medidasCorporalesRepository.findAll()).thenReturn(medidasCorporalesList);

        List<MedidasCorporales> result = medidasCorporalesService.GetMedidasCorporales();

        assertEquals(medidasCorporalesList, result);
        verify(medidasCorporalesRepository, times(1)).findAll();
    }

    @Test
    public void testGetMedidasCorporalesById() {
        Long id = 1L;
        MedidasCorporales medidasCorporales = new MedidasCorporales();
        medidasCorporales.setIdMedidasCorporales(id);
        Optional<MedidasCorporales> optionalMedidasCorporales = Optional.of(medidasCorporales);
        when(medidasCorporalesRepository.findById(id)).thenReturn(optionalMedidasCorporales);

        Optional<MedidasCorporales> result = medidasCorporalesService.GetMedidasCorporalesById(id);

        assertEquals(optionalMedidasCorporales, result);
        verify(medidasCorporalesRepository, times(1)).findById(id);
    }

    @Test
    public void testUpdateMedidasCorporales() {
        MedidasCorporales medidasCorporales = new MedidasCorporales();

        medidasCorporalesService.UpdateMedidasCorporales(medidasCorporales);

        verify(medidasCorporalesRepository, times(1)).save(medidasCorporales);
    }

    @Test
    public void testDeleteMedidasCorporales() {
        Long id = 1L;

        medidasCorporalesService.DeleteMedidasCorporales(id);

        verify(medidasCorporalesRepository, times(1)).deleteById(id);
    }
}
