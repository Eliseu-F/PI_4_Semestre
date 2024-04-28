package br.senac.tads.pi.lojatenis.service;

import br.senac.tads.pi.lojatenis.model.Cliente;
import br.senac.tads.pi.lojatenis.model.Produto;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Eliseu
 */
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
        List<Produto> findByMarca(String marca);

}