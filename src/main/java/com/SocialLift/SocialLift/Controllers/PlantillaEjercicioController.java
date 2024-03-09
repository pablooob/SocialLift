package com.SocialLift.SocialLift.Controllers;

import com.SocialLift.SocialLift.Models.PlantillaEjercicio;
import com.SocialLift.SocialLift.Services.PlantillaEjercicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/plantillaEjercicio")
public class PlantillaEjercicioController {
    private PlantillaEjercicioService plantillaEjercicioService;

    @Autowired
    public PlantillaEjercicioController(PlantillaEjercicioService plantillaEjercicioService){
        this.plantillaEjercicioService = plantillaEjercicioService;
    }

    @PostMapping("/new")
    public PlantillaEjercicio crearNuevoPlantillaEjercicio(@RequestBody PlantillaEjercicio plantillaEjercicio) {
        return plantillaEjercicioService.NewPlantillaEjercicio(plantillaEjercicio);
    }
    @GetMapping("/all")
    public List<PlantillaEjercicio> GetPlantillaEjercicios() {
        return plantillaEjercicioService.GetPlantillaEjercicios();
    }

    @GetMapping("/{id}")
    public PlantillaEjercicio GetPlantillaEjercicioPorId(@PathVariable Long id) {
        Optional<PlantillaEjercicio> ejercicio = plantillaEjercicioService.GetPlantillaEjercicioById(id);
        return ejercicio.get();
    }

    @PutMapping("/{id}")
    public String actualizarPlantillaEjercicio(@PathVariable Long id, @RequestBody PlantillaEjercicio plantillaEjercicio) {
        plantillaEjercicio.setId(id); // Asegurarse de que el ID del ejercicio sea el mismo que se proporciona en la URL
        plantillaEjercicioService.UpdatePlantillaEjercicio(plantillaEjercicio);
        return "Ejercicio actualizado exitosamente";
    }

    @DeleteMapping("/{id}")
    public String eliminarPlantillaEjercicio(@PathVariable Long id) {
        plantillaEjercicioService.DeletePlantillaEjercicio(id);
        return "Ejercicio eliminado exitosamente";
    }
}
