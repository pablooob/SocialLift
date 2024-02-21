package com.SocialLift.SocialLift.Services;

import com.SocialLift.SocialLift.Models.Ejercicio;
import com.SocialLift.SocialLift.Repositories.EjercicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EjercicioService {

    private EjercicioRepository ejercicioRepository;

    @Autowired
    public EjercicioService(EjercicioRepository ejercicioRepository){
        this.ejercicioRepository = ejercicioRepository;
    }

    public void NewEjercicio(Ejercicio ejercicio){
        ejercicioRepository.save(ejercicio);
    }

    public List<Ejercicio> GetEjercicios(){
        return ejercicioRepository.findAll();
    }

    public Optional<Ejercicio> GetEjercicioById(Long id){
        return ejercicioRepository.findById(id);
    }

    public void UpdateEjercicio(Ejercicio ejercicio){
        ejercicioRepository.save(ejercicio);
    }

    public void DeleteEjercicio(Long id){
        ejercicioRepository.deleteById(id);
    }
}
