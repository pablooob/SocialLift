package com.SocialLift.SocialLift.Repositories;

import com.SocialLift.SocialLift.Models.MedidasCorporales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedidasCorporalesRepository extends JpaRepository<MedidasCorporales, Long> {
}
