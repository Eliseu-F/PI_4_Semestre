package br.senac.tads.pi.lojatenis.service;


import org.springframework.data.jpa.repository.JpaRepository;

import br.senac.tads.pi.lojatenis.model.Pedido;

public interface AtualizarProdutoRepository extends JpaRepository<Pedido, Long> {
}
