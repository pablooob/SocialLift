package com.SocialLift.SocialLift.Services;

import com.SocialLift.SocialLift.Models.MedidasCorporales;
import com.SocialLift.SocialLift.Repositories.MedidasCorporalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedidasCorporalesService {

    private MedidasCorporalesRepository medidasCorporalesRepository;

    @Autowired
    public MedidasCorporalesService(MedidasCorporalesRepository medidasCorporalesRepository) {
        this.medidasCorporalesRepository = medidasCorporalesRepository;
    }

    public MedidasCorporales NewMedidasCorporales(MedidasCorporales medidasCorporales) {
        return medidasCorporalesRepository.save(medidasCorporales);
    }

    public List<MedidasCorporales> GetMedidasCorporales() {
        return medidasCorporalesRepository.findAll();
    }

    public Optional<MedidasCorporales> GetMedidasCorporalesById(Long id) {
        return medidasCorporalesRepository.findById(id);
    }

    public void UpdateMedidasCorporales(MedidasCorporales medidasCorporales) {
        medidasCorporalesRepository.save(medidasCorporales);
    }

    public void DeleteMedidasCorporales(Long id) {
        medidasCorporalesRepository.deleteById(id);
    }
}
