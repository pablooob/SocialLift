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
public class IMC {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idIMC;

    @Column(nullable = false)
    private Date registro;

    private Double Value;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    @JsonIgnoreProperties({"seguidores", "seguidos", "IMC"})
    private Usuario usuario;
}
