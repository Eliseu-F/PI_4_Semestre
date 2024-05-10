package br.senac.tads.pi.lojatenis.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.senac.tads.pi.lojatenis.model.Cliente;
import br.senac.tads.pi.lojatenis.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
     List<Pedido> findByCliente(Cliente cliente);
}
