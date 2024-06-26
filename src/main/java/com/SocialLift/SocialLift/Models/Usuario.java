package com.SocialLift.SocialLift.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    private String imagen;

    private Double alturaActual;
    private Double pesoActual;
    private Double hombroActual;
    private Double espaldaActual;
    private Double cinturaActual;
    private Double gemeloActual;
    private Double bicepsActual;
    private Double musloActual;
    private Double pechoActual;

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

    @OneToMany(mappedBy = "usuario")
    @JsonIgnoreProperties("Usuario")
    private List<IMC> IMCs;

    @OneToMany(mappedBy = "usuario")
    @JsonIgnoreProperties("Usuario")
    private List<Peso> pesos;

    @OneToMany(mappedBy = "usuario")
    @JsonIgnoreProperties("Usuario")
    private List<MedidasCorporales> MedidasCorporales;

    @ManyToMany(mappedBy = "usuarioGuardados")
    @JsonIgnore
    private List<PlantillaRutina> plantillaRutinaGuardadas;

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
