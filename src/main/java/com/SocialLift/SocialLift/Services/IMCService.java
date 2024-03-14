package com.SocialLift.SocialLift.Services;

import com.SocialLift.SocialLift.Models.IMC;
import com.SocialLift.SocialLift.Repositories.IMCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IMCService {

    private IMCRepository imcRepository;

    @Autowired
    public IMCService(IMCRepository imcRepository) {
        this.imcRepository = imcRepository;
    }

    public IMC NewIMC(IMC imc) {
        return imcRepository.save(imc);
    }

    public List<IMC> GetIMCs() {
        return imcRepository.findAll();
    }

    public Optional<IMC> GetIMCById(Long id) {
        return imcRepository.findById(id);
    }

    public void UpdateIMC(IMC imc) {
        imcRepository.save(imc);
    }

    public void DeleteIMC(Long id) {
        imcRepository.deleteById(id);
    }
}
