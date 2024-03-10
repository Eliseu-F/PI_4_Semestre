package br.senac.tads.pi.lojatenis.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "produtos")
public class ProdutoDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @NotEmpty(message = "O campo de nome não pode estar vazio")
    private String nome;
    
    @NotEmpty(message = "O campo de nome não pode estar vazio")
    private String avaliacao;

    private Boolean status;
    
    @NotEmpty(message = "O campo de nome não pode estar vazio")
    @Min(0)
    private Float preco;
    
    @NotEmpty(message = "O campo de nome não pode estar vazio")
    private int qtd_estoque;
    
    private String descricao; 
    
    private MultipartFile arquivo_imagem ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(String avaliacao) {
        this.avaliacao = avaliacao;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Float getPreco() {
        return preco;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }

    public int getQtd_estoque() {
        return qtd_estoque;
    }

    public void setQtd_estoque(int qtd_estoque) {
        this.qtd_estoque = qtd_estoque;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public MultipartFile getArquivo_imagem() {
        return arquivo_imagem;
    }

    public void setArquivo_imagem(MultipartFile arquivo_imagem) {
        this.arquivo_imagem = arquivo_imagem;
    }
    
    
}
