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
public class PlantillaRutina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPlantillaRutina;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String tipo;

    @OneToMany(mappedBy = "plantillaRutina")
    private List<Rutina> rutinas;

    @ManyToMany
    @JoinTable(
            name = "rutina_ejercicio",
            joinColumns = @JoinColumn(name = "rutina_id"),
            inverseJoinColumns = @JoinColumn(name = "ejercicio_id")
    )
    private List<PlantillaEjercicio> plantillaEjercicios;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

}
