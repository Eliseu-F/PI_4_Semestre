package br.senac.tads.pi.lojatenis.service;

import br.senac.tads.pi.lojatenis.model.Produto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Eliseu
 */
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
        List<Produto> findByMarca(String marca);
        List<Produto> findByModelo(String modelo);
        List<Produto> findByCor(String cor);
        List<Produto> findByMarcaAndCor(String marca, String cor);



}