package com.SocialLift.SocialLift.Controllers;

import com.SocialLift.SocialLift.Models.PlantillaRutina;
import com.SocialLift.SocialLift.Models.Rutina;
import com.SocialLift.SocialLift.Models.Usuario;
import com.SocialLift.SocialLift.Services.PlantillaRutinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/plantillaRutina")
public class PlantillaRutinaController {

    private PlantillaRutinaService plantillaRutinaService;

    @Autowired
    public PlantillaRutinaController(PlantillaRutinaService plantillaRutinaService) {
        this.plantillaRutinaService = plantillaRutinaService;
    }

    @PostMapping(headers = "Accept=application/json")
    public PlantillaRutina NewPlantillaRutina(@RequestBody PlantillaRutina plantillaRutina) {
        return this.plantillaRutinaService.NewPlantillaRutina(plantillaRutina);
    }

    @GetMapping("/byUserID/{id}")
    public List<PlantillaRutina> GetPlantillaRutinasPorIdUsuario(@PathVariable Long id) {
        return plantillaRutinaService.getPlantillaRutinasByUsuarioId(id);
    }

    @GetMapping("/byUsuarioGuardadoID/{id}")
    public List<PlantillaRutina> GetRutinasPorUsuarioGuardados(@PathVariable Long id) {
        return plantillaRutinaService.obtenerRutinasPorUsuarioGuardados(id);
    }


    @GetMapping(headers = "Accept=application/json")
    public List<PlantillaRutina> GetPlantillaRutinas() {
        return plantillaRutinaService.GetPlantillaRutinas();
    }

    @GetMapping(value = "/{id}", headers = "Accept=application/json")
    public Optional<PlantillaRutina> GetById(@PathVariable Long id) {
        return plantillaRutinaService.GetPlantillaRutinaById(id);
    }

    @GetMapping(value = "/startWith/{nombre}", headers = "Accept=application/json")
    public List<PlantillaRutina> GetByNombreContaining(@PathVariable String nombre) {
        return plantillaRutinaService.findByNombreContaining(nombre);
    }

    @PutMapping(headers = "Accept=application/json")
    public void UpdatePlantillaRutina(@RequestBody PlantillaRutina plantillaRutina) {
        plantillaRutinaService.UpdatePlantillaRutina(plantillaRutina);
    }

    @PutMapping(value = "/AñadirGuardarRutina/{idUsuario}/{idRutina}", headers = "Accept=application/json")
    public void UpdateAñadirGuardarRutina(@PathVariable Long idUsuario, @PathVariable Long idRutina) {
        plantillaRutinaService.AñadirRutinaGuardadaById(idUsuario,idRutina);
    }

    @PutMapping(value = "/EliminarGuardarRutina/{idUsuario}/{idRutina}", headers = "Accept=application/json")
    public void UpdateEliminarGuardarRutina(@PathVariable Long idUsuario, @PathVariable Long idRutina) {
        plantillaRutinaService.EliminarRutinaGuardadaById(idUsuario,idRutina);
    }

    @DeleteMapping(value = "/{id}", headers = "Accept=application/json")
    public void DeleteUserById(@PathVariable Long id) {
        plantillaRutinaService.DeletePlantillaRutina(id);
    }





}
