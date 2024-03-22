package br.senac.tads.pi.lojatenis.controller;

import br.senac.tads.pi.lojatenis.model.Usuario;
import br.senac.tads.pi.lojatenis.model.UsuarioDto;
import br.senac.tads.pi.lojatenis.service.UsuarioRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Controller
@Service
public class SignUpController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    PasswordEncoder passwordEncoder;
    
    public SignUpController(UsuarioRepository usuarioRepository){
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @GetMapping("/signup")
    public String getSignupPage(){
        return "usuarios/signup";
    }

    @PostMapping("/signup")
    public String submitSignUp(UsuarioDto usuarioDto, HttpServletRequest request) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(usuarioDto.getEmail());

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();

            // Encriptar a senha fornecida antes de comparar
            String senhaEncriptada = passwordEncoder.encode(usuarioDto.getSenha());

            if (passwordEncoder.matches(usuarioDto.getSenha(), usuario.getSenha())) {
                HttpSession session = request.getSession();
                session.setAttribute("grupo", usuario.getGrupo());

                if ("Cliente".equals(usuario.getGrupo())) {
                    return "redirect:/signup?error=cliente";
                } else if ("Administrador".equals(usuario.getGrupo()) && "Ativo".equals(usuario.getStatus())) {
                    return "redirect:/";
                } else if ("Estoquista".equals(usuario.getGrupo()) && "Ativo".equals(usuario.getStatus())) {
                    return "redirect:/";
                } else {
                    return "redirect:/signup?error";
                }
            } else {
                return "redirect:/signup?error";
            }
        } else {
            return "redirect:/signup?error";
        }
    }
}