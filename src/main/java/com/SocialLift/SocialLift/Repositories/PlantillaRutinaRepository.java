package com.SocialLift.SocialLift.Repositories;

import com.SocialLift.SocialLift.Models.PlantillaRutina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlantillaRutinaRepository extends JpaRepository<PlantillaRutina, Long> {
    List<PlantillaRutina> findByUsuarioIdUsuario(Long idUsuario);

    List<PlantillaRutina> findByNombreContaining(String nombre);
}
