package br.senac.tads.pi.lojatenis.controller;

import br.senac.tads.pi.lojatenis.model.Usuario;
import br.senac.tads.pi.lojatenis.model.UsuarioDto;
import br.senac.tads.pi.lojatenis.service.UsuarioRepository;
import jakarta.validation.Valid;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repo;

    @GetMapping({"", "/"})
    public String showUsuariosList(Model model) {
        List<Usuario> usuarios = repo.findAll(Sort.by(Sort.Direction.DESC, "id"));
        model.addAttribute("usuarios", usuarios);
        return "usuarios/index";
    }

    @GetMapping("/create")
    public String showCriaUsuario(Model model) {
        UsuarioDto usuarioDto = new UsuarioDto();
        model.addAttribute("usuarioDto", usuarioDto);
        return "usuarios/CriaUsuario";
    }

    @PostMapping("/create")
    public String criarUsuario(
            @Valid @ModelAttribute UsuarioDto usuarioDto,
            BindingResult resultado) {

        if (resultado.hasFieldErrors()) {
            return "usuarios/CriaUsuario";
        }

        if (resultado.hasErrors()) {
            System.out.println("Erros de validação: " + resultado.getAllErrors());
            return "usuarios/CriaUsuario";
        }

        Usuario usuario = new Usuario();
        usuario.setNome(usuarioDto.getNome());
        usuario.setEmail(usuarioDto.getEmail());
        usuario.setCpf(usuarioDto.getCpf());
        usuario.setSenha(usuarioDto.getSenha());
        usuario.setGrupo(usuarioDto.getGrupo());
        usuario.setStatus(usuarioDto.getStatus());

        return "redirect:/usuarios";
    }

}
