package com.SocialLift.SocialLift.Services;

import com.SocialLift.SocialLift.Models.ColorRutina;
import com.SocialLift.SocialLift.Models.Ejercicio;
import com.SocialLift.SocialLift.Repositories.ColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ColorService {
    private ColorRepository colorRepository;

    @Autowired
    public ColorService(ColorRepository colorRepository) {this.colorRepository = colorRepository;}

    public ColorRutina NewColor(ColorRutina color){
        return colorRepository.save(color);
    }

    public List<ColorRutina> GetColor(){
        return colorRepository.findAll();
    }

    public Optional<ColorRutina> GetEjercicioById(Long id){
        return colorRepository.findById(id);
    }

    public void UpdateEjercicio(ColorRutina color
    ){
        colorRepository.save(color);
    }

    public void DeleteEjercicio(Long id){
        colorRepository.deleteById(id);
    }
}

