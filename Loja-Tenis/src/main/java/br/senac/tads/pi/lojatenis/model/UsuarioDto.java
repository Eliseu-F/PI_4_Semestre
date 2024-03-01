package br.senac.tads.pi.lojatenis.model;

/**
 * Plataforma para desenvolvimento utilizada como base para criar as validações 
 * do forms html
 * https://jakarta.ee/specifications/bean-validation/3.0/apidocs/jakarta/validation/constraints/package-summary
 * 
 * O que é um DTO
 * https://pt.stackoverflow.com/questions/31362/o-que-%C3%A9-um-dto
 * 
 * @author eliseu.santos
 */
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

//Data Transfer Object (DTO)
public class UsuarioDto {
    @NotEmpty(message = "O campo de nome não pode estar vazio")
    private String nome;
    
    @NotEmpty(message = "O campo de email não pode estar vazio")
    @Pattern(regexp=".+@.+\\..+", message="Please provide a valid email address")
    @Email
    private String email;
    
    @NotEmpty(message = "Necessário inserir o CPF")
    @Size(min = 12, message = "Insira um CPF Valido. ")
    @Size(max = 12, message = "Insira um CPF Valido")
    private String cpf; 
    
    @NotEmpty(message = "O campo de email não pode estar vazio")
    private String senha;
    
    @NotEmpty(message = "O campo de email não pode estar vazio")
    private String confirmaSenha;
    
    @NotEmpty(message = "O campo de email não pode estar vazio")
    private String status;

    @NotEmpty(message = "O campo de email não pode estar vazio")
    private String grupo;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getConfirmaSenha() {
        return confirmaSenha;
    }

    public void setConfirmaSenha(String confirmaSenha) {
        this.confirmaSenha = confirmaSenha;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }
    
    
}
