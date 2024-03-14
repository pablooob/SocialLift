package com.SocialLift.SocialLift.Services;

import com.SocialLift.SocialLift.Models.Peso;
import com.SocialLift.SocialLift.Repositories.PesoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PesoService {

    private PesoRepository pesoRepository;

    @Autowired
    public PesoService(PesoRepository pesoRepository) {
        this.pesoRepository = pesoRepository;
    }

    public Peso NewPeso(Peso peso) {
        return pesoRepository.save(peso);
    }

    public List<Peso> GetPesos() {
        return pesoRepository.findAll();
    }

    public Optional<Peso> GetPesoById(Long id) {
        return pesoRepository.findById(id);
    }

    public void UpdatePeso(Peso peso) {
        pesoRepository.save(peso);
    }

    public void DeletePeso(Long id) {
        pesoRepository.deleteById(id);
    }
}
