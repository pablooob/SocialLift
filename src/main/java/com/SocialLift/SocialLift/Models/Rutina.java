package com.SocialLift.SocialLift.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    private String descripcion;

    @Column(nullable = false)
    private Date fecha;

    @ManyToOne
    @JoinColumn(name = "idPlantillaRutina")
    private PlantillaRutina plantillaRutina;

    @OneToMany(mappedBy = "rutinaPerteneciente")
    private List<Ejercicio> ejercicios;

}
