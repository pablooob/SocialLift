package com.SocialLift.SocialLift.Models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Serie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String numeroSerie;

    @Column(nullable = false)
    private String peso;

    @Column(nullable = false)
    private String numeroRepeticiones;

}
