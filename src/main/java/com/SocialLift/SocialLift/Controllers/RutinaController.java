package com.SocialLift.SocialLift.Controllers;

import com.SocialLift.SocialLift.Models.*;
import com.SocialLift.SocialLift.Services.RutinaService;
import com.SocialLift.SocialLift.Services.UsuarioService;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/rutina")
public class RutinaController {
    private RutinaService rutinaService;
    private UsuarioService usuarioService;

    @Autowired
    public RutinaController(RutinaService rutinaService, UsuarioService usuarioService) {
        this.rutinaService = rutinaService;
        this.usuarioService = usuarioService;
    }

    @PostMapping("/new")
    public Rutina crearNuevaRutina(@RequestBody Rutina rutina) {
        return rutinaService.NewRutina(rutina);
    }

    @GetMapping("/all")
    public List<Rutina> GetRutinas() {
        return rutinaService.GetRutinas();
    }

    @GetMapping("/byUserID/{id}")
    public List<Rutina> GetRutinasPorIdUsuario(@PathVariable Long id) {
        return rutinaService.getRutinaByUsuarioId(id);
    }
    @GetMapping("/stats/byUserID/{id}")
    public Map<String, Map<Date, List<Serie>>> GetRutinasStatsPorIdUsuario(@PathVariable Long id) {
        Map<String, Map<Date, List<Serie>>> estadisticas = new HashMap<>();
        List<Rutina> rutinas = GetRutinasPorIdUsuario(id);

        for (Rutina rutina : rutinas) {
            Date fecha = rutina.getFecha();
            for (Ejercicio ejercicio : rutina.getEjercicios()) {
                String plantillaEjercicio = ejercicio.getPlantillaEjercicio().getNombre();
                List<Serie> series = ejercicio.getSeries();

                // Verificar si la clave ya existe en el mapa
                if (!estadisticas.containsKey(plantillaEjercicio)) {
                    estadisticas.put(plantillaEjercicio, new HashMap<>());
                }

                // Obtener el mapa interior correspondiente a la plantilla de ejercicio
                Map<Date, List<Serie>> seriesPorFecha = estadisticas.get(plantillaEjercicio);
                seriesPorFecha.put(fecha, series);
            }
        }
        return estadisticas;
    }


    @GetMapping("/{id}")
    public Rutina GetRutinaById(@PathVariable Long id) {
        Optional<Rutina> rutina = rutinaService.GetRutinaById(id);
        return rutina.get();
    }

    @PutMapping("/{id}")
    public String actualizarEjercicio(@PathVariable Long id, @RequestBody Rutina rutina) {
        rutina.setId(id); // Asegurarse de que el ID del ejercicio sea el mismo que se proporciona en la URL
        rutinaService.UpdateRutina(rutina);
        return "Rutina actualizada exitosamente";
    }

    @DeleteMapping("/{id}")
    public String eliminarEjercicio(@PathVariable Long id) {
        rutinaService.DeleteRutina(id);
        return "Rutina eliminada exitosamente";
    }

    @GetMapping("/{idUsuario}/rutinasSeguidos")
    public List<Rutina> getRutinasSeguidosByUserId(@PathVariable Long idUsuario) {
        List<Usuario> seguidos = usuarioService.GetSeguiendoById(idUsuario);
        List<Rutina> rutinasSeguidos = rutinaService.getRutinasByUsuarios(seguidos);
        return rutinasSeguidos;
    }
}
