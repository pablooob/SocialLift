package com.SocialLift.SocialLift.Services;

import com.SocialLift.SocialLift.Models.PlantillaRutina;
import com.SocialLift.SocialLift.Models.Rutina;
import com.SocialLift.SocialLift.Models.Usuario;
import com.SocialLift.SocialLift.Repositories.PlantillaRutinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlantillaRutinaService {

    private PlantillaRutinaRepository plantillaRutinaRepository;

    @Autowired
    public PlantillaRutinaService(PlantillaRutinaRepository plantillaRutinaRepositoryRepository) {
        this.plantillaRutinaRepository = plantillaRutinaRepositoryRepository;
    }

    public PlantillaRutina NewPlantillaRutina(PlantillaRutina plantillaRutina) {
        return plantillaRutinaRepository.save(plantillaRutina);
    }

    public List<PlantillaRutina> GetPlantillaRutinas() {
        return plantillaRutinaRepository.findAll();
    }

    public Optional<PlantillaRutina> GetPlantillaRutinaById(Long id) {
        return plantillaRutinaRepository.findById(id);
    }

    public List<PlantillaRutina> getPlantillaRutinasByUsuarioId(Long idUsuario) {
        return plantillaRutinaRepository.findByUsuarioIdUsuario(idUsuario);
    }

    public List<PlantillaRutina> findByNombreContaining(String nombre) {
        return plantillaRutinaRepository.findByNombreContaining(nombre);
    }

    public List<PlantillaRutina> obtenerRutinasPorUsuarioGuardados(Long idUsuario) {
        return plantillaRutinaRepository.findByUsuarioGuardadosIdUsuario(idUsuario);
    }

    public void UpdatePlantillaRutina(PlantillaRutina plantillaRutina) {
        plantillaRutinaRepository.save(plantillaRutina);
    }

    public void DeletePlantillaRutina(Long id) {
        plantillaRutinaRepository.deleteById(id);
    }

    public void AñadirRutinaGuardadaById(Long idUsuario, Long idRutina) {
        Optional<PlantillaRutina> optionalRutina = GetPlantillaRutinaById(idRutina);
        if (optionalRutina.isPresent()) {
            PlantillaRutina plantillaRutina = optionalRutina.get();
            boolean usuarioPresente = plantillaRutina.getUsuarioGuardados().stream()
                    .anyMatch(usuario1 -> usuario1.getIdUsuario().equals(idUsuario));
            if (!usuarioPresente) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(idUsuario);
                plantillaRutina.getUsuarioGuardados().add(usuario);
                plantillaRutinaRepository.save(plantillaRutina);
            }

        } else {
            new Exception("La rutina con el ID proporcionado no fue encontrada.");
        }
    }

    public void EliminarRutinaGuardadaById(Long idUsuario, Long idRutina) {
        Optional<PlantillaRutina> optionalRutina = GetPlantillaRutinaById(idRutina);
        if (optionalRutina.isPresent()) {
            PlantillaRutina plantillaRutina = optionalRutina.get();
            plantillaRutina.getUsuarioGuardados().removeIf(usuario -> usuario.getIdUsuario().equals(idUsuario));
            plantillaRutinaRepository.save(plantillaRutina);
        } else {
            new Exception("La rutina con el ID proporcionado no fue encontrada.");
        }
    }
}
