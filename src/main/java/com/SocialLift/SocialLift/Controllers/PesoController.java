package com.SocialLift.SocialLift.Controllers;

import com.SocialLift.SocialLift.Models.MedidasCorporales;
import com.SocialLift.SocialLift.Models.Peso;
import com.SocialLift.SocialLift.Services.MedidasCorporalesService;
import com.SocialLift.SocialLift.Services.PesoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Peso")
public class PesoController {
    private PesoService pesoService;

    @Autowired
    public PesoController(PesoService pesoService) {
        this.pesoService = pesoService;
    }

    @PostMapping(headers = "Accept=application/json")
    public Peso NewPeso(@RequestBody Peso peso) {
        return this.pesoService.NewPeso(peso);
    }

    @GetMapping(headers = "Accept=application/json")
    public List<Peso> GetPeso() {
        return pesoService.GetPesos();
    }

    @GetMapping(value = "/{id}", headers = "Accept=application/json")
    public Optional<Peso> GetById(@PathVariable Long id) {
        return pesoService.GetPesoById(id);
    }

    @PutMapping(headers = "Accept=application/json")
    public void UpdatePeso(Peso peso) {
        pesoService.UpdatePeso(peso);
    }

    @DeleteMapping(value = "/{id}", headers = "Accept=application/json")
    public void DeletePesoById(@PathVariable Long id) {
        pesoService.DeletePeso(id);
    }
}

