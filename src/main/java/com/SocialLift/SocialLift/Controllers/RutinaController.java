package com.SocialLift.SocialLift.Controllers;

import com.SocialLift.SocialLift.Models.Ejercicio;
import com.SocialLift.SocialLift.Models.Rutina;
import com.SocialLift.SocialLift.Services.RutinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rutina")
public class RutinaController {
    private RutinaService rutinaService;

    @Autowired
    public RutinaController(RutinaService rutinaService){
        this.rutinaService=rutinaService;
    }

    @PostMapping("/new")
    public String crearNuevaRutina(@RequestBody Rutina rutina){
        rutinaService.NewRutina(rutina);
        return "Rutina creada exitosamente";
    }

    @GetMapping("/all")
    public List<Rutina> GetRutinas() {
        return rutinaService.GetRutinas();
    }

    @GetMapping("/{id}")
    public Rutina GetRutinaById(@PathVariable Long id)
    {
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
}
