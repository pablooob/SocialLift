package com.SocialLift.SocialLift.Repositories;

import com.SocialLift.SocialLift.Models.Peso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PesoRepository extends JpaRepository<Peso, Long> {
}
