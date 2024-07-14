package com.SocialLift.SocialLift.Services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.SocialLift.SocialLift.Models.Serie;
import com.SocialLift.SocialLift.Repositories.SerieRepository;
import com.SocialLift.SocialLift.Services.SerieService;

public class SerieServiceTest {

    @Mock
    private SerieRepository serieRepository;

    @InjectMocks
    private SerieService serieService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testNewSerie() {
        // Mock Serie
        Serie serie = new Serie();
        serie.setIdSerie(1L);

        // Mock SerieRepository behavior
        when(serieRepository.save(serie)).thenReturn(serie);

        // Call the service method
        Serie result = serieService.NewSerie(serie);

        // Verification
        assertNotNull(result);
        assertEquals(1L, result.getIdSerie());
        verify(serieRepository, times(1)).save(serie);
    }

    @Test
    public void testGetSeries() {
        // Mock list of Series
        List<Serie> series = new ArrayList<>();
        series.add(new Serie());
        series.add(new Serie());

        // Mock SerieRepository behavior
        when(serieRepository.findAll()).thenReturn(series);

        // Call the service method
        List<Serie> result = serieService.GetSeries();

        // Verification
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(serieRepository, times(1)).findAll();
    }

    @Test
    public void testGetSerieById() {
        Long id = 1L;
        // Mock Serie
        Serie serie = new Serie();
        serie.setIdSerie(id);

        // Mock SerieRepository behavior
        when(serieRepository.findById(id)).thenReturn(Optional.of(serie));

        // Call the service method
        Optional<Serie> result = serieService.GetSerieById(id);

        // Verification
        assertTrue(result.isPresent());
        assertEquals(id, result.get().getIdSerie());
        verify(serieRepository, times(1)).findById(id);
    }

    @Test
    public void testUpdateSerie() {
        // Mock Serie
        Serie serie = new Serie();
        serie.setIdSerie(1L);

        // Call the service method
        serieService.UpdateSerie(serie);

        // Verification
        verify(serieRepository, times(1)).save(serie);
    }

    @Test
    public void testDeleteSerie() {
        Long id = 1L;
        // Call the service method
        serieService.DeleteSerie(id);

        // Verification
        verify(serieRepository, times(1)).deleteById(id);
    }
}
