package com.SocialLift.SocialLift.Controllers;

import com.SocialLift.SocialLift.Models.PlantillaRutina;
import com.SocialLift.SocialLift.Models.Rutina;
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
    public PlantillaRutina NewPlantillaRutina(@RequestBody PlantillaRutina plantillaRutina){
        return this.plantillaRutinaService.NewPlantillaRutina(plantillaRutina);
    }
    @GetMapping("/byUserID/{id}")
    public List<PlantillaRutina> GetPlantillaRutinasPorIdUsuario(@PathVariable Long id) {
        return plantillaRutinaService.getPlantillaRutinasByUsuarioId(id);
    }

    @GetMapping(headers = "Accept=application/json")
    public List<PlantillaRutina> GetPlantillaRutinas(){
        return plantillaRutinaService.GetPlantillaRutinas();
    }

    @GetMapping(value = "/{id}", headers = "Accept=application/json")
    public Optional<PlantillaRutina> GetById(@PathVariable Long id){
        return plantillaRutinaService.GetPlantillaRutinaById(id);
    }

    @PutMapping(headers = "Accept=application/json")
    public void UpdatePlantillaRutina(PlantillaRutina plantillaRutina){
        plantillaRutinaService.UpdatePlantillaRutina(plantillaRutina);
    }

    @DeleteMapping(value = "/{id}", headers = "Accept=application/json")
    public void DeleteUserById(@PathVariable Long id){
        plantillaRutinaService.DeletePlantillaRutina(id);
    }
}
