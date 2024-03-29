package com.SocialLift.SocialLift.Services;

import com.SocialLift.SocialLift.Models.PlantillaEjercicio;
import com.SocialLift.SocialLift.Repositories.PlantillaEjercicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlantillaEjercicioService {

    private PlantillaEjercicioRepository plantillaEjercicioRepository;

    @Autowired
    public PlantillaEjercicioService(PlantillaEjercicioRepository plantillaEjercicioRepository){
        this.plantillaEjercicioRepository = plantillaEjercicioRepository;
    }

    public PlantillaEjercicio NewPlantillaEjercicio(PlantillaEjercicio plantillaEjercicio){
        return plantillaEjercicioRepository.save(plantillaEjercicio);
    }

    public List<PlantillaEjercicio> GetPlantillaEjercicios(){
        return plantillaEjercicioRepository.findAll();
    }

    public Optional<PlantillaEjercicio> GetPlantillaEjercicioById(Long id){
        return plantillaEjercicioRepository.findById(id);
    }
    public List<PlantillaEjercicio> getPlantillaEjerciciosByUsuarioId(Long idUsuario) {
        return plantillaEjercicioRepository.findByUsuarioIdUsuario(idUsuario);
    }

    public void UpdatePlantillaEjercicio(PlantillaEjercicio plantillaEjercicio){
        plantillaEjercicioRepository.save(plantillaEjercicio);
    }

    public void DeletePlantillaEjercicio(Long id){
        plantillaEjercicioRepository.deleteById(id);
    }
}
