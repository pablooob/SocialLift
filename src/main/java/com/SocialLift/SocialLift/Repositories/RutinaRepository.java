package com.SocialLift.SocialLift.Repositories;

import com.SocialLift.SocialLift.Models.PlantillaEjercicio;
import com.SocialLift.SocialLift.Models.Rutina;
import com.SocialLift.SocialLift.Models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RutinaRepository extends JpaRepository<Rutina, Long> {
    List<Rutina> findByUsuarioIdUsuario(Long idUsuario);
    List<Rutina> findByUsuarioIn(List<Usuario> usuarios);
}
