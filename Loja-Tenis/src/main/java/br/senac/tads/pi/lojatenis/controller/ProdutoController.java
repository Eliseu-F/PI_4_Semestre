package br.senac.tads.pi.lojatenis.controller;

import br.senac.tads.pi.lojatenis.model.Produto;
import br.senac.tads.pi.lojatenis.model.ProdutoDto;
import br.senac.tads.pi.lojatenis.service.ProdutoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Eliseu
 */
@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repo;
    
    @GetMapping({"", "/"})
    public String showProdutosList(Model model) {
        List<Produto> produtos = repo.findAll(Sort.by(Sort.Direction.ASC, "id"));
        model.addAttribute("produtos", produtos);
        return "produtos/index";
    }
    
     @GetMapping("/create")
    public String showCriaProduto(Model model) {
         ProdutoDto produtoDto = new ProdutoDto();
        model.addAttribute("produtoDto", produtoDto);
        return "produtos/CriaProduto";
    }

}
