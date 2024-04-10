/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.senac.tads.pi.lojatenis.controller;

import br.senac.tads.pi.lojatenis.model.Cliente;
import br.senac.tads.pi.lojatenis.model.Produto;
import br.senac.tads.pi.lojatenis.service.ProdutoRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 *
 * @author Alexsandro
 */

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private ProdutoRepository repo;


    @GetMapping({"", "/"})
    public String home(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        Cliente clienteLogado = (Cliente) session.getAttribute("clienteLogado");
        
        if (clienteLogado != null) {
            model.addAttribute("usuarioLogado", true);
        } else {
            model.addAttribute("usuarioLogado", false);
        }
        List<Produto> produtos = repo.findAll();

        model.addAttribute("produtos", produtos);
        return "home/index";
    }

}
