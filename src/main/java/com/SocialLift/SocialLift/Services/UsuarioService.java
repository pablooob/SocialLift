package com.SocialLift.SocialLift.Services;

import com.SocialLift.SocialLift.Models.Serie;
import com.SocialLift.SocialLift.Models.Usuario;
import com.SocialLift.SocialLift.Repositories.SerieRepository;
import com.SocialLift.SocialLift.Repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void DeleteUsuario(Long id){
        usuarioRepository.deleteById(id);
    }
}
