package br.senac.tads.pi.lojatenis.model;

/**
 *
 * @author eliseu.santos
 */
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UsuarioDto {
    @NotEmpty(message = "Necessário inserir o nome")
    private String nome;
    
    @NotEmpty(message = "Necessário inserir o email")
    private String email;
    
    @NotEmpty(message = "Necessário inserir o CPF")
    @Size(min = 12, message = "Insira um CPF Valido. EX: xxx.xxx.xxx-xx")
    @Size(max = 12, message = "Insira um CPF Valido. EX: xxx.xxx.xxx-xx")
    private String cpf; 
    
}
