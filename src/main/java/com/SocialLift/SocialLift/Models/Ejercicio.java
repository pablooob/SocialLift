package com.SocialLift.SocialLift.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Ejercicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEjercicio;

    @Column(nullable = false, unique = true)
    private String nombre;

    private String descripcion;

    @Column(nullable = false)
    private String tipo;

    @ManyToOne
    @JoinColumn(name = "idRutina")
    @JsonIgnore
    private Rutina rutinaPerteneciente;

    @ManyToOne
    @JoinColumn(name = "idPlantillaRutina")
    @JsonIgnore
    private PlantillaRutina plantillaRutinaPerteneciente;

    @ManyToOne
    @JoinColumn(name = "idSerie")
    @JsonIgnore
    private Serie serie;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    @JsonIgnore
    private Usuario usuario;


    @JsonIgnore
    public void setId(Long id) {
        this.idEjercicio = id;
    }
}
