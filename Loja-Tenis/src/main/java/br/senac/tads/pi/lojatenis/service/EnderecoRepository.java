package br.senac.tads.pi.lojatenis.service;



import br.senac.tads.pi.lojatenis.model.Endereco;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

}