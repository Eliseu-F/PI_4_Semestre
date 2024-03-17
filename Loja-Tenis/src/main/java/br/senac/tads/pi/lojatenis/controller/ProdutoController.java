package br.senac.tads.pi.lojatenis.controller;

import br.senac.tads.pi.lojatenis.model.Produto;
import br.senac.tads.pi.lojatenis.model.ProdutoDto;
import br.senac.tads.pi.lojatenis.service.ProdutoRepository;
import jakarta.validation.Valid;



import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repo;

    @GetMapping({"", "/"})
    public String showProdutosList(Model model) {
        List<Produto> produtos = repo.findAll(Sort.by(Sort.Direction.DESC, "id"));
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

        List<String> imagensSalvas = new ArrayList<>();
        for (MultipartFile imagem : produtoDto.getImagens()) {
            String nomeArquivo = UUID.randomUUID().toString() + "_" + imagem.getOriginalFilename();

            try {
                String diretorioImagens = "src/main/resources/static/imagens_produtos/";
                Path uploadPath = (Path) Paths.get(diretorioImagens);
                if(!Files.exists(uploadPath)){
                    System.out.println("Diretorio nao existe");
                }
                Path filePath = uploadPath.resolve(nomeArquivo);
                Files.copy(imagem.getInputStream(), filePath);
                imagensSalvas.add(nomeArquivo);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        //defini a primeira imagem como padrão
        String imagemPadrao = imagensSalvas.get(0);

            // Mapear produtoDto para a entidade produto
        Produto produto = new Produto();

        produto.setNome(produtoDto.getNome());
        produto.setAvaliacao(produtoDto.getAvaliacao());
        produto.setPreco(produtoDto.getPreco());
        produto.setQtd_estoque(produtoDto.getQtd_estoque());
        produto.setDescricao(produtoDto.getDescricao());
        produto.setStatus(produtoDto.getStatus());
        produto.setImagens(imagensSalvas);
        produto.setImagemPadrao(imagemPadrao);
        // Configurar atributos de produtoDto para produto

        // Salvar produto no repositório
        repo.save(produto);

        // Redirecionar para a lista de usuários após a criação bem-sucedida
        return "redirect:/produtos";
    }

    @GetMapping("/edit")
    public String MostraEdicao(Model model, @RequestParam int id) {

        try {
            Produto produto = repo.findById(id).get();
            model.addAttribute("produto", produto);

            ProdutoDto produtoDto = new ProdutoDto();
            produtoDto.setId(produto.getId());
            produtoDto.setNome(produto.getNome());
            produtoDto.setAvaliacao(produto.getAvaliacao());
            produtoDto.setPreco(produto.getPreco());
            produtoDto.setQtd_estoque(produto.getQtd_estoque());
            produtoDto.setDescricao(produto.getDescricao());
            produtoDto.setStatus(produto.getStatus());

            model.addAttribute("produtoDto", produtoDto);

        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            return "redirect:/produtos";
        }
        return "produtos/EditarProduto";
    }
    
    

    @PostMapping("/edit")
    public String editarProduto(Model model, Principal principal, @RequestParam int id, @Valid @ModelAttribute ProdutoDto produtoDto, BindingResult bindingResult) {

        try {
            Produto produto = repo.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
            model.addAttribute("produto", produto);

            if (bindingResult.hasErrors()) {
                // Se houver erros de validação, retorne para o formulário de edição
                return "produtos/EditarProduto";
            }

            // Configurar atributos de produtoDto para produto
            produto.setNome(produtoDto.getNome());
            produto.setAvaliacao(produtoDto.getAvaliacao());
            produto.setPreco(produtoDto.getPreco());
            produto.setQtd_estoque(produtoDto.getQtd_estoque());
            produto.setDescricao(produtoDto.getDescricao());

            // Salvar produto no repositório
            repo.save(produto);

        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }

        // Redirecionar para a lista de usuários após a edição bem-sucedida
        return "redirect:/produtos";
    }
    
    

    @PostMapping("/atualizarStatus")
    public String atualizaStatus(@RequestParam int id, @ModelAttribute ProdutoDto produtoDto) {
        Produto produto = repo.findById(id).orElseThrow(() -> new RuntimeException("produto não encontrado"));

        //altera o status do produto
        produto.setStatus("Ativo".equals(produto.getStatus()) ? "Inativo" : "Ativo");
        //se o status for ativo, se for true, altera para inativo, caso contrario altera para ativo

        repo.save(produto);
        return "redirect:/produtos";
    }

}
