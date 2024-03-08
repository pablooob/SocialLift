package com.SocialLift.SocialLift.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Serie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idSerie;

    @Column(nullable = false)
    private int numeroSerie;

    @Column(nullable = false)
    private double peso;

    @Column(nullable = false)
    private String tipoPeso;

    @Column(nullable = false)
    private int numeroRepeticiones;

    @ManyToOne
    @JoinColumn(name = "idEjercicio", nullable = false)
    private Ejercicio ejercicio;
}
