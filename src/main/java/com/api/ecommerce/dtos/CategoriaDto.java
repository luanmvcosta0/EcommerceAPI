package com.api.ecommerce.dtos;

import com.api.ecommerce.models.Categoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CategoriaDto {

    private Long id;

    @Size(min = 3, max = 20, message = "Mínimo de 3 caracteres e máximo de 20")
    @NotBlank(message = "O campo NOME é requerido")
    private String nome;

    @Size(min = 30, max = 60, message = "Mínimo de 30 caracteres e máximo de 60")
    @NotBlank(message = "O campo DESCRIÇÃO é requerido")
    private String descricao;

    public CategoriaDto() {
    }

    public CategoriaDto(Long id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public CategoriaDto(Categoria categoria) {
        this.id = categoria.getId();
        this.nome = categoria.getNome();
        this.descricao = categoria.getDescricao();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
