package com.SocialLift.SocialLift.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    private String imagen;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellidos;

    @Column(nullable = false, unique = true)
    private String correo;

    @Column(nullable = false, unique = true)
    private String nombreUsuario;

    @Column(nullable = false)
    private String contrasenya;

    @OneToMany(mappedBy = "usuario")
    @JsonIgnore
    private List<PlantillaRutina> plantillaRutinas;

    @OneToMany(mappedBy = "usuario")
    @JsonIgnore
    private List<Rutina> Rutinas;

    @OneToMany(mappedBy = "usuario")
    @JsonIgnore
    private List<PlantillaEjercicio> plantillaEjercicios;

    @ManyToMany
    @JoinTable(
            name = "seguidores_seguidos",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "seguido_id")
    )
    @JsonIgnoreProperties({"seguidores", "seguidos"})
    private List<Usuario> seguidores;


    @ManyToMany(mappedBy = "seguidores")
    @JsonIgnoreProperties({"seguidores", "seguidos"})
    private List<Usuario> seguidos;
}
