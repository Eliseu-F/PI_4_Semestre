package br.senac.tads.pi.lojatenis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.senac.tads.pi.lojatenis.model.Carrinho;
import br.senac.tads.pi.lojatenis.model.Produto;
import br.senac.tads.pi.lojatenis.service.ProdutoRepository;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/carrinho")
public class CarrinhoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public String mostrarCarrinho(HttpSession session, Model model) {
        Carrinho carrinho = (Carrinho) session.getAttribute("carrinho");
        // SE NAO EXISTER CARRINHO RETORNA PARA HOME
        if (carrinho == null) {
            return "redirect:/home";
        }

        // Adicione o carrinho ao modelo para exibição na página
        model.addAttribute("carrinho", carrinho);
        return "carrinho";
    }

    @PostMapping("/adicionar")
    public String adicionarProdutoAoCarrinho(@RequestParam int produtoId, @RequestParam int quantidade,
            HttpSession session) {
        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        // CRIA O CARRINHO NA SESSÃO DO SITE
        Carrinho carrinho = (Carrinho) session.getAttribute("carrinho");
        if (carrinho == null) {
            carrinho = new Carrinho();
            session.setAttribute("carrinho", carrinho);
        }

        // ADICIONA PRODUTO AO CARRINHO
        carrinho.adicionarItem(produto, quantidade);

        // REDIRECIONA PARA PAGINA DO CARRINHO
        return "redirect:/carrinho";
    }

    @PostMapping("/remove")
    public String removeProduto(@RequestParam int produtoId,
            HttpSession session) {
        
        Carrinho carrinho = (Carrinho) session.getAttribute("carrinho");
        if (carrinho == null) {
            session.setAttribute("carrinho", carrinho);
        }   

        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        // ADICIONA PRODUTO AO CARRINHO
        carrinho.removerItem(produto);

        // REDIRECIONA PARA PAGINA DO CARRINHO
        return "redirect:/carrinho";
    }


    
   

}
