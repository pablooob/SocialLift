package com.SocialLift.SocialLift.Controllers;

import com.SocialLift.SocialLift.Models.MedidasCorporales;
import com.SocialLift.SocialLift.Services.MedidasCorporalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/MedidasCorporales")
public class MedidasCorporalesController {
    private MedidasCorporalesService medidasCorporalesService;

    @Autowired
    public MedidasCorporalesController(MedidasCorporalesService medidasCorporalesService) {
        this.medidasCorporalesService = medidasCorporalesService;
    }

    @PostMapping(headers = "Accept=application/json")
    public MedidasCorporales NewMedidasCorporales(@RequestBody MedidasCorporales medidasCorporales) {
        return this.medidasCorporalesService.NewMedidasCorporales(medidasCorporales);
    }

    @GetMapping(headers = "Accept=application/json")
    public List<MedidasCorporales> GetMedidasCorporales() {
        return medidasCorporalesService.GetMedidasCorporales();
    }

    @GetMapping(value = "/{id}", headers = "Accept=application/json")
    public Optional<MedidasCorporales> GetById(@PathVariable Long id) {
        return medidasCorporalesService.GetMedidasCorporalesById(id);
    }

    @PutMapping(headers = "Accept=application/json")
    public void UpdateMedidasCorporales(MedidasCorporales medidasCorporales) {
        medidasCorporalesService.UpdateMedidasCorporales(medidasCorporales);
    }

    @DeleteMapping(value = "/{id}", headers = "Accept=application/json")
    public void DeleteMedidasCorporalesById(@PathVariable Long id) {
        medidasCorporalesService.DeleteMedidasCorporales(id);
    }
}

