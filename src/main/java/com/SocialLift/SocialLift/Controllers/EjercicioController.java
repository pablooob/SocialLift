package com.SocialLift.SocialLift.Controllers;

import com.SocialLift.SocialLift.Models.Ejercicio;
import com.SocialLift.SocialLift.Services.EjercicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ejercicio")
public class EjercicioController {
    private EjercicioService ejercicioService;

    @Autowired
    public EjercicioController(EjercicioService ejercicioService){
        this.ejercicioService = ejercicioService;
    }

    @PostMapping("/new")
    public String crearNuevoEjercicio(@RequestBody Ejercicio ejercicio) {
        ejercicioService.NewEjercicio(ejercicio);
        return "Ejercicio creado exitosamente";
    }
    @GetMapping("/all")
    public List<Ejercicio> GetEjercicios() {
        return ejercicioService.GetEjercicios();
    }

    @GetMapping("/{id}")
    public Ejercicio GetEjercicioPorId(@PathVariable Long id) {
        Optional<Ejercicio> ejercicio = ejercicioService.GetEjercicioById(id);
        return ejercicio.get();
    }

    @PutMapping("/{id}")
    public String actualizarEjercicio(@PathVariable Long id, @RequestBody Ejercicio ejercicio) {
        ejercicio.setId(id); // Asegurarse de que el ID del ejercicio sea el mismo que se proporciona en la URL
        ejercicioService.UpdateEjercicio(ejercicio);
        return "Ejercicio actualizado exitosamente";
    }

    @DeleteMapping("/{id}")
    public String eliminarEjercicio(@PathVariable Long id) {
        ejercicioService.DeleteEjercicio(id);
        return "Ejercicio eliminado exitosamente";
    }
}
