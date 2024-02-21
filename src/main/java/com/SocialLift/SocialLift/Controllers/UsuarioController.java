package com.SocialLift.SocialLift.Controllers;

import com.SocialLift.SocialLift.Models.Serie;
import com.SocialLift.SocialLift.Models.Usuario;
import com.SocialLift.SocialLift.Repositories.UsuarioRepository;
import com.SocialLift.SocialLift.Services.SerieService;
import com.SocialLift.SocialLift.Services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/PlantillaRutina")
public class UsuarioController {

    private UsuarioService usuarioService;
    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping(headers = "Accept=application/json")
    public void NewUsuario(@RequestBody Usuario usuario){
        this.usuarioService.NewUsuario(usuario);
    }

    @GetMapping(headers = "Accept=application/json")
    public List<Usuario> GetUsuario(){
        return usuarioService.GetUsuario();
    }

    @GetMapping(value = "/{id}", headers = "Accept=application/json")
    public Optional<Usuario> GetById(@PathVariable Long id){
        return usuarioService.GetUsuarioById(id);
    }

    @PutMapping(headers = "Accept=application/json")
    public void UpdateUsuario(Usuario usuario){
        usuarioService.UpdateUsuario(usuario);
    }

    @DeleteMapping(value = "/{id}", headers = "Accept=application/json")
    public void DeleteUsuarioById(@PathVariable Long id){
        usuarioService.DeleteUsuario(id);
    }
}
