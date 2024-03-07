package br.senac.tads.pi.lojatenis.repositories;

import br.senac.tads.pi.lojatenis.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Usuario, String> {
    Usuario findByEmail(String email);
}
