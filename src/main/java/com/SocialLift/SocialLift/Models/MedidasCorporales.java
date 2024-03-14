package com.SocialLift.SocialLift.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedidasCorporales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMedidasCorporales;

    @Column(nullable = false)
    private Date registro;

    private Double hombros;
    private Double espalda;
    private Double cintura;
    private Double gemelo;
    private Double biceps;
    private Double muslo;
    private Double pecho;


    @ManyToOne
    @JoinColumn(name = "idUsuario")
    @JsonIgnoreProperties({"seguidores", "seguidos", "IMC"})
    private Usuario usuario;
}
