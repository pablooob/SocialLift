package com.SocialLift.SocialLift.Repositories;

import com.SocialLift.SocialLift.Models.IMC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMCRepository extends JpaRepository<IMC, Long> {
}
