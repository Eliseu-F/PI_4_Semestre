package br.senac.tads.pi.lojatenis;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import br.senac.tads.pi.lojatenis.controller.UsuarioController;
import br.senac.tads.pi.lojatenis.model.Usuario;
import br.senac.tads.pi.lojatenis.model.UsuarioDto;
import br.senac.tads.pi.lojatenis.service.UsuarioRepository;
import jakarta.servlet.http.HttpSession;

@ExtendWith(MockitoExtension.class)
class UsuarioTest {

    @InjectMocks
    UsuarioController usuarioController;

    @Mock
    UsuarioRepository usuarioRepository;

    @Mock
    Model model;

    @Mock
    HttpSession session;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testMostrarListaDeUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(new Usuario());
        when(usuarioRepository.findAll(Sort.by(Sort.Direction.DESC, "id"))).thenReturn(usuarios);

        String viewName = usuarioController.showUsuariosList(model, session);

        verify(usuarioRepository, times(1)).findAll(Sort.by(Sort.Direction.DESC, "id"));

        assertEquals("usuarios/index", viewName);

        verify(model, times(1)).addAttribute("usuarios", usuarios);
    }

    @Test
    void testCriarUsuario() {
        
        UsuarioDto usuarioDto = new UsuarioDto();

        usuarioDto.setId(1);
        usuarioDto.setNome("testante da silva");
        usuarioDto.setEmail("testante@gmail.com");
        usuarioDto.setCpf("43595423801");
        usuarioDto.setSenha("123456");
        usuarioDto.setConfirmaSenha("123456");
        usuarioDto.setGrupo("Administrador");
        usuarioDto.setStatus("Ativo");

        when(usuarioRepository.existsByEmail(eq("testante@gmail.com"))).thenReturn(false); 
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(new Usuario()); 
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false); 

        String viewName = usuarioController.criarUsuario(usuarioDto, bindingResult, model);

        assertEquals("redirect:/usuarios", viewName);
        verify(usuarioRepository, times(1)).save(any(Usuario.class));

    }

    @Test
    void testAtualizaStatus() {
        Usuario usuario = new Usuario();
        usuario.setStatus("Ativo");
        
        when(usuarioRepository.findById(anyInt())).thenReturn(Optional.of(usuario));

        usuarioController.atualizaStatus(1, new UsuarioDto());

        assertEquals("Inativo", usuario.getStatus());
    }
}
