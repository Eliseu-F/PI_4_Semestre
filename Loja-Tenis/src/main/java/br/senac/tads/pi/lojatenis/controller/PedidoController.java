package br.senac.tads.pi.lojatenis.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.senac.tads.pi.lojatenis.model.StatusUpdateDto;

import br.senac.tads.pi.lojatenis.model.Pedido;
import br.senac.tads.pi.lojatenis.service.AtualizarProdutoRepository;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import org.springframework.data.domain.Sort;

@Controller
@RequestMapping("/pedido")
public class PedidoController {
    @Autowired
    private AtualizarProdutoRepository repo;

    @GetMapping("/MostraPedidos")
    public String showPedidosList(Model model, HttpSession session) {
        List<Pedido> pedidos = repo.findAll(Sort.by(Sort.Direction.DESC, "dataPedido"));
        model.addAttribute("pedidos", pedidos);
        return "pedidos/MostraPedidos";
    }

    @PutMapping("/updateStatus/{id}")
    @ResponseBody
    public ResponseEntity<?> updateStatus(@PathVariable Long id, @RequestBody StatusUpdateDto statusUpdate) {
        Optional<Pedido> pedidoOpt = repo.findById(id);
        if (pedidoOpt.isPresent()) {
            Pedido pedido = pedidoOpt.get();
            String newStatus = statusUpdate.getStatus();
            pedido.setStatus(newStatus);
            repo.save(pedido);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
