package com.SocialLift.SocialLift.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Ejercicio{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEjercicio;

    @ManyToOne
    @JoinColumn(name = "idRutina")
    @JsonIgnoreProperties({"ejercicios","plantillaRutina"})
    private Rutina rutina;

    @OneToMany(mappedBy = "ejercicio", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("ejercicio")
    private List<Serie> series;

    @ManyToOne
    @JoinColumn(name = "idPlantillaEjercicio")
    private PlantillaEjercicio plantillaEjercicio;

    @JsonIgnore
    public void setId(Long id) {
        this.idEjercicio = id;
    }
}
