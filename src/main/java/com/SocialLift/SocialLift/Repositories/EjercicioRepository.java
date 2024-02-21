package com.SocialLift.SocialLift.Repositories;

import com.SocialLift.SocialLift.Models.Ejercicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EjercicioRepository extends JpaRepository<Ejercicio, Long> {
}
