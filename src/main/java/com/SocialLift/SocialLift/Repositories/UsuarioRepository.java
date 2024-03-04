package com.SocialLift.SocialLift.Repositories;

import com.SocialLift.SocialLift.Models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    public Usuario findByNombreUsuario(String nombreUsuario);
}
