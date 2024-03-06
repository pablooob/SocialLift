package com.SocialLift.SocialLift.Services;

import com.SocialLift.SocialLift.Models.Serie;
import com.SocialLift.SocialLift.Models.Usuario;
import com.SocialLift.SocialLift.Repositories.SerieRepository;
import com.SocialLift.SocialLift.Repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }
    public void NewUsuario(Usuario usuario){
        usuarioRepository.save(usuario);
    }
    public List<Usuario> GetUsuario(){
        return usuarioRepository.findAll();
    }
    public Optional<Usuario> GetUsuarioById(Long id){
        return usuarioRepository.findById(id);
    }
    public void UpdateUsuario(Usuario usuario){
        usuarioRepository.save(usuario);
    }

    public Usuario GetUserByNombreUsuario(String nombreUsuario){
        return usuarioRepository.findByNombreUsuario(nombreUsuario);
    }
    public List<Usuario> GetSeguidoresById(Long id){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if(usuarioOptional.isPresent()){
            List<Usuario> seguidores = usuarioOptional.get().getSeguidores();
            seguidores.forEach(seguidor -> {
                seguidor.setSeguidores(null);
                seguidor.setSeguidos(null);
            });
            return seguidores;
        }
        return Collections.emptyList();
    }
    public List<Usuario> GetSeguiendoById(Long id){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if(usuarioOptional.isPresent()){
            List<Usuario> siguiendo = usuarioOptional.get().getSeguidos();
            siguiendo.forEach(seguidor -> {
                seguidor.setSeguidores(null);
                seguidor.setSeguidos(null);
            });
            return siguiendo;
        }
        return Collections.emptyList();
    }

    //En un futuro quitariamos el param idUsuarioLoggeado y lo obtendremos del singleton
    public void SeguirUsuarioById(Long idUsuarioLoggeado, Long idUsuarioASeguir) throws Exception {
        Optional<Usuario> usuarioASeguir = usuarioRepository.findById(idUsuarioASeguir);
        Optional<Usuario> usuarioLoggeado = usuarioRepository.findById(idUsuarioLoggeado);
        if(usuarioASeguir.isPresent() && usuarioLoggeado.isPresent()){
            usuarioLoggeado.get().getSeguidos().add(usuarioASeguir.get());
            usuarioASeguir.get().getSeguidores().add(usuarioLoggeado.get());
            UpdateUsuario(usuarioLoggeado.get());
        } else {
            throw new Exception("No se ha podido seguir al usuario");
        }

    }

    public void DeleteSeguimientoById(Long idUsuarioLoggeado, Long idUsuarioADesSeguir) throws Exception{
        Optional<Usuario> usuarioADesSeguir = usuarioRepository.findById(idUsuarioADesSeguir);
        Optional<Usuario> usuarioLoggeado = usuarioRepository.findById(idUsuarioLoggeado);
        if(usuarioADesSeguir.isPresent() && usuarioLoggeado.isPresent()){
            //Eliminamos de la lista de seguidos al usuario que ya no queremos seguir
            usuarioLoggeado.get().getSeguidos().remove(usuarioADesSeguir.get());
            //Al usuario que no queremos seguir le quitamos de sus seguidores a usuarioLoggeado
            usuarioADesSeguir.get().getSeguidores().remove(usuarioLoggeado.get());
            UpdateUsuario(usuarioLoggeado.get());
        } else {
            throw new Exception("No se ha podido des-seguir al usuario");
        }
    }
    public void DeleteUsuario(Long id){
        usuarioRepository.deleteById(id);
    }
}
