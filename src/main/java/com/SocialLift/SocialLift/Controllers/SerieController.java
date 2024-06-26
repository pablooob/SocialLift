package com.SocialLift.SocialLift.Controllers;

import com.SocialLift.SocialLift.Models.PlantillaRutina;
import com.SocialLift.SocialLift.Models.Serie;
import com.SocialLift.SocialLift.Services.PlantillaRutinaService;
import com.SocialLift.SocialLift.Services.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/serie")
public class SerieController {
    private SerieService serieService;
    @Autowired
    public SerieController(SerieService serieService) {
        this.serieService = serieService;
    }

    @PostMapping(headers = "Accept=application/json")
    public Serie NewSerie(@RequestBody Serie serie){
        return this.serieService.NewSerie(serie);
    }

    @GetMapping(headers = "Accept=application/json")
    public List<Serie> GetSeries(){
        return serieService.GetSeries();
    }

    @GetMapping(value = "/{id}", headers = "Accept=application/json")
    public Optional<Serie> GetById(@PathVariable Long id){
        return serieService.GetSerieById(id);
    }

    @PutMapping(headers = "Accept=application/json")
    public void UpdateSerie(@RequestBody Serie serie){
        serieService.UpdateSerie(serie);
    }

    @DeleteMapping(value = "/{id}", headers = "Accept=application/json")
    public void DeleteSerieById(@PathVariable Long id){
        serieService.DeleteSerie(id);
    }
}
