package com.SocialLift.SocialLift.Controllers;


import com.SocialLift.SocialLift.Models.ColorRutina;
import com.SocialLift.SocialLift.Models.Ejercicio;
import com.SocialLift.SocialLift.Services.ColorService;
import com.SocialLift.SocialLift.Services.EjercicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/colorRutina")
public class ColorController {
    private ColorService colorService;
    @Autowired
    public ColorController(ColorService colorService){
        this.colorService = colorService;
    }

    @PostMapping("/new")
    public ColorRutina crearNuevoEjercicio(@RequestBody ColorRutina color) {

        return colorService.NewColor(
                color);
    }
    @GetMapping("/all")
    public List<ColorRutina> GetColor() {
        return colorService.GetColor();
    }
}
