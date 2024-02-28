package com.SocialLift.SocialLift.Services;

import com.SocialLift.SocialLift.Models.PlantillaRutina;
import com.SocialLift.SocialLift.Repositories.PlantillaRutinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlantillaRutinaService {

    private PlantillaRutinaRepository plantillaRutinaRepositoryRepository;

    @Autowired
    public PlantillaRutinaService(PlantillaRutinaRepository plantillaRutinaRepositoryRepository){
        this.plantillaRutinaRepositoryRepository = plantillaRutinaRepositoryRepository;
    }

    public void NewPlantillaRutina(PlantillaRutina plantillaRutina){
        plantillaRutinaRepositoryRepository.save(plantillaRutina);
    }

    public List<PlantillaRutina> GetPlantillaRutinas(){
        return plantillaRutinaRepositoryRepository.findAll();
    }

    public Optional<PlantillaRutina> GetPlantillaRutinaById(Long id){
        return plantillaRutinaRepositoryRepository.findById(id);
    }

    public void UpdatePlantillaRutina(PlantillaRutina plantillaRutina){
        plantillaRutinaRepositoryRepository.save(plantillaRutina);
    }

    public void DeletePlantillaRutina(Long id){
        plantillaRutinaRepositoryRepository.deleteById(id);
    }
}
