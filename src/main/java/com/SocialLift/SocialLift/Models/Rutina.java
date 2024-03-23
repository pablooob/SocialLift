package com.SocialLift.SocialLift.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.Color;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rutina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRutina;

    @Column(nullable = false)
    private String nombre;

    private String tipo;

    private String descripcion;

    private Boolean isPublic;

    @Column(nullable = false)
    private Date fecha;

    private Color color;

    @ManyToOne
    @JoinColumn(name = "idPlantillaRutina")
    @JsonIgnoreProperties("rutinas")
    private PlantillaRutina plantillaRutina;

    @OneToMany(mappedBy = "rutina", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonIgnoreProperties("rutina")
    private List<Ejercicio> Ejercicios;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    @JsonIgnoreProperties({"seguidores", "seguidos"})
    private Usuario usuario;

   @JsonIgnore
    public void setId(Long id) {
        this.idRutina = id;
    }
}
