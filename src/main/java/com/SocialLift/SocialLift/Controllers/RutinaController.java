package com.SocialLift.SocialLift.Controllers;

import com.SocialLift.SocialLift.Models.PlantillaEjercicio;
import com.SocialLift.SocialLift.Models.Rutina;
import com.SocialLift.SocialLift.Models.Usuario;
import com.SocialLift.SocialLift.Services.RutinaService;
import com.SocialLift.SocialLift.Services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rutina")
public class RutinaController {
    private RutinaService rutinaService;
    private UsuarioService usuarioService;

    @Autowired
    public RutinaController(RutinaService rutinaService, UsuarioService usuarioService) {
        this.rutinaService = rutinaService;
        this.usuarioService = usuarioService;
    }

    @PostMapping("/new")
    public Rutina crearNuevaRutina(@RequestBody Rutina rutina) {
        return rutinaService.NewRutina(rutina);
    }

    @GetMapping("/all")
    public List<Rutina> GetRutinas() {
        return rutinaService.GetRutinas();
    }

    @GetMapping("/byUserID/{id}")
    public List<Rutina> GetRutinasPorIdUsuario(@PathVariable Long id) {
        return rutinaService.getRutinaByUsuarioId(id);
    }

    @GetMapping("/{id}")
    public Rutina GetRutinaById(@PathVariable Long id) {
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

    @GetMapping("/{idUsuario}/rutinasSeguidos")
    public List<Rutina> getRutinasSeguidosByUserId(@PathVariable Long idUsuario) {
        List<Usuario> seguidos = usuarioService.GetSeguiendoById(idUsuario);
        List<Rutina> rutinasSeguidos = rutinaService.getRutinasByUsuarios(seguidos);
        return rutinasSeguidos;
    }
}
