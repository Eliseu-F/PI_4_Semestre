package br.senac.tads.pi.lojatenis.controller;

import br.senac.tads.pi.lojatenis.model.Usuario;
import br.senac.tads.pi.lojatenis.model.UsuarioDto;
import br.senac.tads.pi.lojatenis.service.UsuarioRepository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repo;

    @GetMapping({"", "/"})
    public String showUsuariosList(Model model) {
        List<Usuario> usuarios = repo.findAll(Sort.by(Sort.Direction.DESC,"id"));
        model.addAttribute("usuarios", usuarios);
        return "usuarios/index";
    }
    
    @GetMapping({"/create"})
    public String showCriaUsuario(Model model) {
        UsuarioDto usuarioDto  = new UsuarioDto();
        model.addAttribute("usuarioDto",usuarioDto);
        return "usuarios/CriaUsuario";
    }
}
