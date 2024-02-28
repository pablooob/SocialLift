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
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

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
    private List<PlantillaRutina> plantillaRutinas;

    @OneToMany(mappedBy = "usuario")
    private List<PlantillaEjercicio> plantillaEjercicios;


    @ManyToMany
    @JoinTable(
            name = "seguidores_seguidos",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "seguido_id")
    )
    private List<Usuario> seguidores;

    @JsonIgnore
    @ManyToMany(mappedBy = "seguidores")
    private List<Usuario> seguidos;
}
