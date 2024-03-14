

package com.SocialLift.SocialLift.Controllers;

import com.SocialLift.SocialLift.Models.IMC;
import com.SocialLift.SocialLift.Services.IMCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/IMC")
public class IMCController {
    private IMCService imcService;

    @Autowired
    public IMCController(IMCService imcService) {
        this.imcService = imcService;
    }

    @PostMapping(headers = "Accept=application/json")
    public IMC NewIMC(@RequestBody IMC imc) {
        return this.imcService.NewIMC(imc);
    }

    @GetMapping(headers = "Accept=application/json")
    public List<IMC> GetIMCs() {
        return imcService.GetIMCs();
    }

    @GetMapping(value = "/{id}", headers = "Accept=application/json")
    public Optional<IMC> GetById(@PathVariable Long id) {
        return imcService.GetIMCById(id);
    }

    @PutMapping(headers = "Accept=application/json")
    public void UpdateIMC(IMC imc) {
        imcService.UpdateIMC(imc);
    }

    @DeleteMapping(value = "/{id}", headers = "Accept=application/json")
    public void DeleteIMCById(@PathVariable Long id) {
        imcService.DeleteIMC(id);
    }
}
