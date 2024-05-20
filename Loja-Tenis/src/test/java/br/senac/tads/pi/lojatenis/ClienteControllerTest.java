package br.senac.tads.pi.lojatenis;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;
import org.springframework.ui.Model;

import br.senac.tads.pi.lojatenis.controller.ClienteController;
import br.senac.tads.pi.lojatenis.model.ClienteDto;
import br.senac.tads.pi.lojatenis.service.ClienteRepository;

@ExtendWith(MockitoExtension.class)

public class ClienteControllerTest {

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private BindingResult bindingResult;

    @Mock
    private Model model;

    @InjectMocks
    private ClienteController clienteController;

    private ClienteDto clienteDto;

    @BeforeEach
    public void setUp() {
        clienteDto = new ClienteDto();
        clienteDto.setId(1);
        clienteDto.setNome("Teste");
        clienteDto.setEmail("teste@gmail.com");
        clienteDto.setSenha("123456");
        clienteDto.setConfirmaSenha("123456");
        clienteDto.setGenero("Masculino");
        clienteDto.setCpf("43595423801");
        LocalDate dataAtual = LocalDate.now();
        Date dataNascimento = Date.valueOf(dataAtual);
        clienteDto.setDataNascimento(dataNascimento);
        clienteDto.setCep("04857000");

    }

    @Test
    public void testCriarCliente() {
        when(clienteRepository.existsByEmail(clienteDto.getEmail())).thenReturn(false);

        String viewName = clienteController.criarCliente(clienteDto, bindingResult, model);

        assertEquals("clientes/CriaCliente", viewName);
    }

    @Test
    public void testCriarCliente_EmailJaExiste() {
        when(clienteRepository.existsByEmail(clienteDto.getEmail())).thenReturn(true);

        String viewName = clienteController.criarCliente(clienteDto, bindingResult, model);

        assertEquals("clientes/CriaCliente", viewName); // ou outra visão que você espera retornar
    }

    @Test
    public void testCriarCliente_SenhaNaoCoincide() {
        clienteDto.setConfirmaSenha("senhaDiferenteDaSenhaPrincipal");

        String viewName = clienteController.criarCliente(clienteDto, bindingResult, model);

        assertEquals("clientes/CriaCliente", viewName); // ou outra visão que você espera retornar
    }

    @Test
    public void testCriarCliente_EnderecoNaoFornecido() {
        clienteDto.setCep(null); // Simule o caso em que o cliente não fornece um endereço

        String viewName = clienteController.criarCliente(clienteDto, bindingResult, model);

        assertEquals("clientes/CriaCliente", viewName); // ou outra visão que você espera retornar
    }

    @Test
    public void testCriarCliente_CpfInvalido() {
        clienteDto.setCpf("12345678910"); 

        String viewName = clienteController.criarCliente(clienteDto, bindingResult, model);

        assertEquals("clientes/CriaCliente", viewName); // ou outra visão que você espera retornar
    }

}
