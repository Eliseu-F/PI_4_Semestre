package br.senac.tads.pi.lojatenis.service;

import org.springframework.data.jpa.repository.JpaRepository;

import br.senac.tads.pi.lojatenis.model.ItemPedido;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {
    
}
