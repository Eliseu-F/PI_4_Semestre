package br.senac.tads.pi.lojatenis.service;

import org.springframework.data.jpa.repository.JpaRepository;

import br.senac.tads.pi.lojatenis.model.FormasDePagamento;

public interface FormaDePagamentoRepository extends JpaRepository <FormasDePagamento, Integer>{
    
}
