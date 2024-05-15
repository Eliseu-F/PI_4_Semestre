package br.senac.tads.pi.lojatenis.service;

import br.senac.tads.pi.lojatenis.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByEmail(String email);
    boolean existsByEmail(String email);

    List<Usuario> findAll();

    

}
