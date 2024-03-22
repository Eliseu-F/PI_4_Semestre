package br.senac.tads.pi.lojatenis.service;

import br.senac.tads.pi.lojatenis.model.Produto;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Eliseu
 */
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    
}