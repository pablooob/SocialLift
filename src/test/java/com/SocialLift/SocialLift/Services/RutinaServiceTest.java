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

import com.SocialLift.SocialLift.Models.Rutina;
import com.SocialLift.SocialLift.Models.Usuario;
import com.SocialLift.SocialLift.Repositories.RutinaRepository;
import com.SocialLift.SocialLift.Services.RutinaService;

public class RutinaServiceTest {

    @Mock
    private RutinaRepository rutinaRepository;

    @InjectMocks
    private RutinaService rutinaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testNewRutina() {
        // Mock Rutina
        Rutina rutina = new Rutina();
        rutina.setId(1L);

        // Mock RutinaRepository behavior
        when(rutinaRepository.save(rutina)).thenReturn(rutina);

        // Call the service method
        Rutina result = rutinaService.NewRutina(rutina);

        // Verification
        assertNotNull(result);
        assertEquals(1L, result.getIdRutina());
        verify(rutinaRepository, times(1)).save(rutina);
    }

    @Test
    public void testGetRutinas() {
        // Mock list of Rutinas
        List<Rutina> rutinas = new ArrayList<>();
        rutinas.add(new Rutina());
        rutinas.add(new Rutina());

        // Mock RutinaRepository behavior
        when(rutinaRepository.findAll()).thenReturn(rutinas);

        // Call the service method
        List<Rutina> result = rutinaService.GetRutinas();

        // Verification
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(rutinaRepository, times(1)).findAll();
    }

    @Test
    public void testGetRutinaById() {
        Long id = 1L;
        // Mock Rutina
        Rutina rutina = new Rutina();
        rutina.setId(id);

        // Mock RutinaRepository behavior
        when(rutinaRepository.findById(id)).thenReturn(Optional.of(rutina));

        // Call the service method
        Optional<Rutina> result = rutinaService.GetRutinaById(id);

        // Verification
        assertTrue(result.isPresent());
        assertEquals(id, result.get().getIdRutina());
        verify(rutinaRepository, times(1)).findById(id);
    }

    @Test
    public void testGetRutinaByUsuarioId() {
        Long idUsuario = 1L;
        // Mock list of Rutinas
        List<Rutina> rutinas = new ArrayList<>();
        rutinas.add(new Rutina());
        rutinas.add(new Rutina());

        // Mock RutinaRepository behavior
        when(rutinaRepository.findByUsuarioIdUsuario(idUsuario)).thenReturn(rutinas);

        // Call the service method
        List<Rutina> result = rutinaService.getRutinaByUsuarioId(idUsuario);

        // Verification
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(rutinaRepository, times(1)).findByUsuarioIdUsuario(idUsuario);
    }

    @Test
    public void testGetRutinasByUsuarios() {
        // Mock list of Usuarios
        List<Usuario> usuarios = new ArrayList<>();
        Usuario usuario1 = new Usuario();
        usuario1.setIdUsuario(1L);
        Usuario usuario2 = new Usuario();
        usuario2.setIdUsuario(2L);
        usuarios.add(usuario1);
        usuarios.add(usuario2);

        // Mock list of Rutinas
        List<Rutina> rutinas = new ArrayList<>();
        rutinas.add(new Rutina());
        rutinas.add(new Rutina());

        // Mock RutinaRepository behavior
        when(rutinaRepository.findByUsuarioInAndIsPublicIsTrue(usuarios)).thenReturn(rutinas);

        // Call the service method
        List<Rutina> result = rutinaService.getRutinasByUsuarios(usuarios);

        // Verification
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(rutinaRepository, times(1)).findByUsuarioInAndIsPublicIsTrue(usuarios);
    }

    @Test
    public void testUpdateRutina() {
        // Mock Rutina
        Rutina rutina = new Rutina();
        rutina.setId(1L);

        // Call the service method
        rutinaService.UpdateRutina(rutina);

        // Verification
        verify(rutinaRepository, times(1)).save(rutina);
    }

    @Test
    public void testDeleteRutina() {
        Long id = 1L;
        // Call the service method
        rutinaService.DeleteRutina(id);

        // Verification
        verify(rutinaRepository, times(1)).deleteById(id);
    }
}

