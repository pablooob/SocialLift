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
@RequestMapping("/api/usuario")
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

    @GetMapping(value = "/nombreUsuario/{nombreUsuario}", headers = "Accept=application/json")
    public Usuario GetByNombreUsuario(@PathVariable String nombreUsuario){
        Usuario user = usuarioService.GetUserByNombreUsuario(nombreUsuario);
        if(user != null) return user;
        throw new NullPointerException();
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
