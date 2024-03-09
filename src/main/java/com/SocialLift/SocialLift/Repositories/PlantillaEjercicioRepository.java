package com.SocialLift.SocialLift.Repositories;

import com.SocialLift.SocialLift.Models.PlantillaEjercicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlantillaEjercicioRepository extends JpaRepository<PlantillaEjercicio, Long> {
    List<PlantillaEjercicio> findByUsuarioIdUsuario(Long idUsuario);
}
