package br.senac.tads.pi.lojatenis.controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;


import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import br.senac.tads.pi.lojatenis.model.Cliente;
import br.senac.tads.pi.lojatenis.model.ClienteDto;
import br.senac.tads.pi.lojatenis.service.ClienteRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@Service
public class LoginController{

    @Autowired
    private ClienteRepository clienteRepository;

    PasswordEncoder passwordEncoder;

    public LoginController(ClienteRepository clienteRepository) {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @GetMapping("/login")
    public String getLogin(){
        return "clientes/login";
    }


    @PostMapping("/login")
    public String submitLogin(ClienteDto clienteDto, HttpServletRequest request){
        Optional<Cliente> clienteOptional = clienteRepository.findByEmail(clienteDto.getEmail());

        if(clienteOptional.isPresent()){
            Cliente cliente = clienteOptional.get();

            String senhaEncriptada = passwordEncoder.encode(clienteDto.getSenha());

            if(passwordEncoder.matches(clienteDto.getSenha(), cliente.getSenha())){

            HttpSession session = request.getSession();
            session.setAttribute("clienteLogado", cliente);

            return "redirect:/home";

        }
        else {
            System.out.println("Login nao existe ou Login Incorreto");
            return "redirect:/login?error";
        }
    }
    return "redirect:/login?error";
}
}