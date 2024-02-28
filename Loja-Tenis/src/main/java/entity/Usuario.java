package entity;

import entity.enumeration.Grupo;
import entity.enumeration.Status;
import jakarta.persistence.Entity;
import java.time.LocalDate;

@Entity
public class Usuario {
    
     private String nome;
     
     private String email;
     
     private String cpf;
    
    private String senha;
    
    private Status status;

    private Grupo grupo;
    
    public Usuario() {
    }

    public Usuario(String nome, String email, String cpf, String senha, Status status, Grupo grupo) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.senha = senha;
        this.status = status;
        this.grupo = grupo;
    }

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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }
    
    
}
