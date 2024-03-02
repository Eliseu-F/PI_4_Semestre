package br.senac.tads.pi.lojatenis.controller;

import br.senac.tads.pi.lojatenis.model.Usuario;
import br.senac.tads.pi.lojatenis.model.UsuarioDto;
import br.senac.tads.pi.lojatenis.service.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller

public class SignUpController {

    @GetMapping("/signup")
    public String getSignupPage(){
        return "usuarios/signup";
    }

    @PostMapping("/signup")
    public String submitSignUp(UsuarioDto usuarioDto){
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(usuarioDto.getEmail());

        if(usuarioOptional.isPresent() && usuarioOptional.get().getSenha().equals(usuarioDto.getSenha())){
            return "redirect:/usuarios";
        }else {
            return "redirect:/signup?error";
        }
    }
    @Autowired
    private UsuarioRepository usuarioRepository;
}
