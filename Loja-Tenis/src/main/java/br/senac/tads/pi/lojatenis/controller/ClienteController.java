package br.senac.tads.pi.lojatenis.controller;

import java.security.Principal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.senac.tads.pi.lojatenis.model.Cliente;
import br.senac.tads.pi.lojatenis.model.ClienteDto;
import br.senac.tads.pi.lojatenis.service.ClienteRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/clientes")
@Service
public class ClienteController {

    @Autowired
    private ClienteRepository repo;

    PasswordEncoder passwordEncoder;

    public ClienteController(ClienteRepository clienteRepository) {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }


    @GetMapping("/create")
    public String showCriaCliente(Model model) {
        ClienteDto clienteDto = new ClienteDto();
        model.addAttribute("clienteDto", clienteDto);
        return "clientes/CriaCliente";
    }

    @PostMapping("/create")
    public String criarCliente(@ModelAttribute("clienteDto") @Valid ClienteDto clienteDto, BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            // Se houver erros de validação, retorne para o formulário de registro
            return "clientes/CriaCliente";
        }

        if (repo.existsByEmail((clienteDto.getEmail()))) {
            bindingResult.rejectValue("email", "error.clienteDto", "Este email já está em uso");
            return "clientes/CriaCliente";
        }

        // Verificar se o cliente forneceu um endereço
        if (clienteDto.getCep() == null || clienteDto.getLogradouro() == null || clienteDto.getNumero() == null) {
            // Adicionar uma mensagem de erro ao BindingResult
            bindingResult.reject("endereco", "É necessário fornecer um endereço");

            // Retornar para o formulário de registro com a mensagem de erro
            return "clientes/CriaCliente";
        }

        // Mapear clienteDto para a entidade cliente
        Cliente cliente = new Cliente();

        cliente.setNome(clienteDto.getNome());
        cliente.setEmail(clienteDto.getEmail());
        cliente.setGenero(clienteDto.getGenero());
        cliente.setCpf(clienteDto.getCpf());
        cliente.setDataNascimento(clienteDto.getDataNascimento());
       
        cliente.setStatus(clienteDto.getStatus());
        cliente.setCep(clienteDto.getCep());
        cliente.setLogradouro(clienteDto.getLogradouro());
        cliente.setNumero(clienteDto.getNumero());
        cliente.setComplemento(clienteDto.getComplemento());
        cliente.setBairro(clienteDto.getBairro());
        cliente.setCidade(clienteDto.getCidade());
        cliente.setUf(clienteDto.getUf());

        // encripatar a senha usando o Bcrypt
        String senhaEcripitada = this.passwordEncoder.encode(clienteDto.getSenha());
        cliente.setSenha(senhaEcripitada);

        String cpf = clienteDto.getCpf();
        if (!isValidCPF(cpf)) {
            bindingResult.rejectValue("cpf", "error.clienteDto", "CPF inválido");
            return "clientes/Criacliente";
        }

        // Configurar atributos de clienteDto para cliente
        // Salvar cliente no repositório
        repo.save(cliente);

        // Redirecionar para a lista de usuários após a criação bem-sucedida
        return "redirect:/login";
    }

    private boolean isValidCPF(String cpf) {
        // Remove caracteres especiais do CPF
        cpf = cpf.replaceAll("[^0-9]", "");

        // Verifica se o CPF possui 11 dígitos
        if (cpf.length() != 11) {
            return false;
        }

        // Calcula o primeiro dígito verificador
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += (cpf.charAt(i) - '0') * (10 - i);
        }
        int remainder = 11 - (sum % 11);
        int digit1 = (remainder >= 10) ? 0 : remainder;

        // Verifica o primeiro dígito verificador
        if (digit1 != (cpf.charAt(9) - '0')) {
            return false;
        }

        // Calcula o segundo dígito verificador
        sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += (cpf.charAt(i) - '0') * (11 - i);
        }
        remainder = 11 - (sum % 11);
        int digit2 = (remainder >= 10) ? 0 : remainder;

        // Verifica o segundo dígito verificador
        if (digit2 != (cpf.charAt(10) - '0')) {
            return false;
        }

        return true;
    }



    

}
