package com.SocialLift.SocialLift.Services;

import com.SocialLift.SocialLift.Models.Rutina;
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
    public void NewRutina(Rutina rutina){
        rutinaRepository.save(rutina);
    }
    public List<Rutina> GetRutinas() { return rutinaRepository.findAll();}
    public Optional<Rutina> GetRutinaById(Long id){ return rutinaRepository.findById(id);}

    public void UpdateRutina(Rutina rutina) { rutinaRepository.save(rutina);}
    public void DeleteRutina(Long id){
        rutinaRepository.deleteById(id);
    }
}
