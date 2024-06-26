package com.SocialLift.SocialLift.Services;

import com.SocialLift.SocialLift.Models.PlantillaEjercicio;
import com.SocialLift.SocialLift.Models.Rutina;
import com.SocialLift.SocialLift.Models.Usuario;
import com.SocialLift.SocialLift.Repositories.RutinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RutinaService {

    private RutinaRepository rutinaRepository;
    @Autowired
    public RutinaService(RutinaRepository rutinaRepository){
        this.rutinaRepository = rutinaRepository;
    }
    public Rutina NewRutina(Rutina rutina){
        return rutinaRepository.save(rutina);
    }
    public List<Rutina> GetRutinas() { return rutinaRepository.findAll();}

    public List<Rutina> getRutinaByUsuarioId(Long idUsuario) {
        return rutinaRepository.findByUsuarioIdUsuario(idUsuario);
    }
    public Optional<Rutina> GetRutinaById(Long id){ return rutinaRepository.findById(id);}

    public List<Rutina> getRutinasByUsuarios(List<Usuario> usuarios) {
        return rutinaRepository.findByUsuarioInAndIsPublicIsTrue(usuarios);
    }

    public void UpdateRutina(Rutina rutina) { rutinaRepository.save(rutina);}
    public void DeleteRutina(Long id){
        rutinaRepository.deleteById(id);
    }
}
