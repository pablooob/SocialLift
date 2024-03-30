package com.SocialLift.SocialLift.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    private String imagen;

    private String descripcion;

    @Column(nullable = false)
    private String tipo;

    @OneToMany(mappedBy = "plantillaRutina")
    @JsonIgnoreProperties("plantillaRutina")
    private List<Rutina> rutinas;

    @ManyToMany
    @JoinTable(
            name = "rutina_ejercicio",
            joinColumns = @JoinColumn(name = "idRutina"),
            inverseJoinColumns = @JoinColumn(name = "idEjercicio")
    )
    private List<PlantillaEjercicio> plantillaEjercicios;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    @JsonIgnoreProperties({"seguidores", "seguidos"})
    private Usuario usuario;

    @OneToOne
    @JoinColumn(name = "color_id")
    private ColorRutina color;

}
