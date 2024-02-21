package com.SocialLift.SocialLift.Repositories;

import com.SocialLift.SocialLift.Models.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SerieRepository extends JpaRepository<Serie, Long> {
}
