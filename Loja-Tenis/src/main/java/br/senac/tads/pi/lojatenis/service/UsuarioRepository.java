package br.senac.tads.pi.lojatenis.service;

import br.senac.tads.pi.lojatenis.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    
}
