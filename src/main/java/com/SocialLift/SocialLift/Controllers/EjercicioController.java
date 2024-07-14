package com.SocialLift.SocialLift.Controllers;

import com.SocialLift.SocialLift.Models.Ejercicio;
import com.SocialLift.SocialLift.Services.EjercicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin/ejercicio")
public class EjercicioController {

    private EjercicioService ejercicioService;

    @Autowired
    public EjercicioController(EjercicioService ejercicioService) {
        this.ejercicioService = ejercicioService;
    }

    @PostMapping("/new")
    public Ejercicio crearNuevoEjercicio(@RequestBody Ejercicio ejercicio,
                                         @RequestHeader(value = "Authorization", required = true) String authorization) {
        // Aquí puedes validar el token si es necesario
        return ejercicioService.NewEjercicio(ejercicio);
    }

    @GetMapping("/all")
    public List<Ejercicio> GetEjercicios(@RequestHeader(value = "authorization", required = true) String authorization) {
        return ejercicioService.GetEjercicios();
    }

    @GetMapping("/{id}")
    public Ejercicio GetEjercicioPorId(@PathVariable Long id,
                                       @RequestHeader(value = "authorization", required = true) String authorization) {
        // Aquí puedes validar el token si es necesario
        Optional<Ejercicio> ejercicio = ejercicioService.GetEjercicioById(id);
        return ejercicio.orElse(null);
    }

    @PutMapping("/{id}")
    public String actualizarEjercicio(@PathVariable Long id,
                                      @RequestBody Ejercicio ejercicio,
                                      @RequestHeader(value = "authorization", required = true) String authorization) {
        // Aquí puedes validar el token si es necesario
        ejercicio.setId(id);
        ejercicioService.UpdateEjercicio(ejercicio);
        return "Ejercicio actualizado exitosamente";
    }

    @DeleteMapping("/{id}")
    public String eliminarEjercicio(@PathVariable Long id,
                                    @RequestHeader(value = "Authorization", required = true) String authorization) {
        // Aquí puedes validar el token si es necesario
        ejercicioService.DeleteEjercicio(id);
        return "Ejercicio eliminado exitosamente";
    }
}
