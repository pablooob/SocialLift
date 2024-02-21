package com.SocialLift.SocialLift.Repositories;

import com.SocialLift.SocialLift.Models.Rutina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RutinaRepository extends JpaRepository<Rutina, Long> {
}
