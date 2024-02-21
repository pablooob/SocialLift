package com.SocialLift.SocialLift.Services;

import com.SocialLift.SocialLift.Models.PlantillaRutina;
import com.SocialLift.SocialLift.Models.Serie;
import com.SocialLift.SocialLift.Repositories.PlantillaRutinaRepository;
import com.SocialLift.SocialLift.Repositories.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SerieService {

    private SerieRepository serieRepository;

    @Autowired
    public SerieService(SerieRepository serieRepository){
        this.serieRepository = serieRepository;
    }
    public void NewSerie(Serie serie){
        serieRepository.save(serie);
    }
    public List<Serie> GetSeries(){
        return serieRepository.findAll();
    }
    public Optional<Serie> GetSerieById(Long id){
        return serieRepository.findById(id);
    }
    public void UpdateSerie(Serie serie){
        serieRepository.save(serie);
    }
    public void DeleteSerie(Long id){
        serieRepository.deleteById(id);
    }
}
