package br.senac.tads.pi.lojatenis.controller;

import br.senac.tads.pi.lojatenis.model.Produto;
import br.senac.tads.pi.lojatenis.model.ProdutoDto;
import br.senac.tads.pi.lojatenis.service.ProdutoRepository;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


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
    
    @PostMapping("/create")
    public String criarProduto(@ModelAttribute("produtoDto") @Valid ProdutoDto produtoDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            // Se houver erros de validação, retorne para o formulário de registro
            return "produtos/CriaProduto";
        }

        // Mapear produtoDto para a entidade produto
        Produto produto = new Produto();

        produto.setNome(produtoDto.getNome());
        produto.setAvaliacao(produtoDto.getAvaliacao());
        produto.setPreco(produtoDto.getPreco());
        produto.setQtd_estoque(produtoDto.getQtd_estoque());
        produto.setDescricao(produtoDto.getDescricao());
        produto.setStatus(produtoDto.getStatus());
        // Configurar atributos de produtoDto para produto

        // Salvar produto no repositório
        repo.save(produto);

        // Redirecionar para a lista de usuários após a criação bem-sucedida
        return "redirect:/produtos";
    }

}
