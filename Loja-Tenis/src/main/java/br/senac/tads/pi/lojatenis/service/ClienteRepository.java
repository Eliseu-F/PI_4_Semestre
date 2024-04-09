package br.senac.tads.pi.lojatenis.service;

import java.util.Optional;

import br.senac.tads.pi.lojatenis.model.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    Optional<Cliente> findByEmail(String email);
    boolean existsByEmail(String email);

}