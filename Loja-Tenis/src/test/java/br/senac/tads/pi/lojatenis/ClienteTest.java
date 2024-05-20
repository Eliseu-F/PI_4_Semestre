package br.senac.tads.pi.lojatenis;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import br.senac.tads.pi.lojatenis.controller.ClienteController;
import br.senac.tads.pi.lojatenis.model.Cliente;
import br.senac.tads.pi.lojatenis.model.ClienteDto;
import br.senac.tads.pi.lojatenis.service.ClienteRepository;
import br.senac.tads.pi.lojatenis.service.EnderecoRepository;
import br.senac.tads.pi.lojatenis.service.PedidoRepository;
import jakarta.servlet.http.HttpSession;

@ExtendWith(MockitoExtension.class)
class ClienteTest {

    @InjectMocks
    ClienteController clienteController;

    @Mock
    ClienteRepository clienteRepository;

    @Mock
    Model model;

    @Mock
    HttpSession session;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    
    @Test
    void testCriarCliente() {
        ClienteDto clienteDto = new ClienteDto();

        clienteDto.setNome("Teste Junior");
        clienteDto.setEmail("teste@gmail.com");
        clienteDto.setGenero("Masculino");
        clienteDto.setCpf("43595423801");
        LocalDate dataAtual = LocalDate.now();
        Date dataNascimento = Date.valueOf(dataAtual);
        clienteDto.setDataNascimento(dataNascimento);

        assertEquals("Teste Junior", clienteDto.getNome(), "Nome do cliente não está correto");
        assertEquals("teste@gmail.com", clienteDto.getEmail(), "Email do cliente não está correto");
        assertEquals("Masculino", clienteDto.getGenero(), "Gênero do cliente não está correto");
        assertEquals("43595423801", clienteDto.getCpf(), "CPF do cliente não está correto");
        assertEquals(dataNascimento, clienteDto.getDataNascimento(), "Data de nascimento do cliente não está correta");
    }


    @Test
    void testCriarClienteComEmailDuplicado() {
        ClienteRepository clienteRepository = mock(ClienteRepository.class);
        EnderecoRepository enderecoRepository = mock(EnderecoRepository.class);
        PedidoRepository pedidoRepository = mock(PedidoRepository.class);
        PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
        ClienteController clienteController = new ClienteController(clienteRepository);

        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setEmail("teste@gmail.com");

        when(clienteRepository.existsByEmail(eq("teste@gmail.com"))).thenReturn(true);

        Model model = mock(Model.class);
        BindingResult bindingResult = mock(BindingResult.class);

        String viewName = clienteController.criarCliente(clienteDto, bindingResult, model);

        assertEquals("clientes/CriaCliente", viewName);

        verify(clienteRepository, never()).save(any(Cliente.class));
    }

    
}
