package com.SocialLift.SocialLift.Controllers;

import com.SocialLift.SocialLift.Models.Usuario;
import com.SocialLift.SocialLift.Services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/public/usuario")
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
        return usuarioService.GetUserByNombreUsuario(nombreUsuario);
    }

    @GetMapping(value = "/startWith/{nombreUsuario}", headers = "Accept=application/json")
    public List<Usuario> GetByNombreUsuarioStartingWith(@PathVariable String nombreUsuario){
        return usuarioService.findByNombreUsuarioStartingWith(nombreUsuario);
    }

    @GetMapping(value = "/{id}/seguidores", headers = "Accept=application/json")
    public List<Usuario> GetSeguidoresById(@PathVariable Long id){
        return usuarioService.GetSeguidoresById(id);
    }

    @GetMapping(value = "/{id}/siguiendo", headers = "Accept=application/json")
    public List<Usuario> GetSeguiendoById(@PathVariable Long id){
        return usuarioService.GetSeguiendoById(id);
    }

    @PostMapping(value = "/{idUsuarioLoggeado}/seguir/{idUsuarioASeguir}", headers = "Accept=application/json")
    public void PostSeguirUsuario(@PathVariable Long idUsuarioLoggeado, @PathVariable Long idUsuarioASeguir) throws Exception {
        usuarioService.SeguirUsuarioById(idUsuarioLoggeado, idUsuarioASeguir);
    }
    @PutMapping(headers = "Accept=application/json")
    public void UpdateUsuario(Usuario usuario){
        usuarioService.UpdateUsuario(usuario);
    }

    @DeleteMapping(value = "/{id}", headers = "Accept=application/json")
    public void DeleteUsuarioById(@PathVariable Long id){
        usuarioService.DeleteUsuario(id);
    }

    @DeleteMapping(value = "/{idUsuarioLoggeado}/desseguir/{idUsuarioADesSeguir}", headers = "Accept=application/json")
    public void DeleteSeguimientoById(@PathVariable Long idUsuarioLoggeado, @PathVariable Long idUsuarioADesSeguir) throws Exception{
        usuarioService.DeleteSeguimientoById(idUsuarioLoggeado, idUsuarioADesSeguir);
    }


}
