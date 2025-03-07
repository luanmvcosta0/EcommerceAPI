package com.api.ecommerce.service;

import com.api.ecommerce.dtos.ProdutoDto;
import com.api.ecommerce.exceptions.ObjectNotFoundException;
import com.api.ecommerce.models.Categoria;
import com.api.ecommerce.models.Produto;
import com.api.ecommerce.repositories.CategoriaRepository;
import com.api.ecommerce.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    public List<ProdutoDto> findAllByCategoria(Long idCategoria) {
        categoriaRepository.findById(idCategoria);

        List<Produto> produtos = produtoRepository.findByCategoriaId(idCategoria);

        return produtos.stream()
                .map(ProdutoDto::new)
                .collect(Collectors.toList());
    }

    public Produto findById(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Produto não encontrado."));
    }

    public Produto save(ProdutoDto produtoDto) {
        Categoria cat = categoriaRepository.findById(produtoDto.getCategoriaId())
                .orElseThrow(() -> new ObjectNotFoundException("Categoria não encontrada"));

        Produto produto = new Produto();
        produto.setNome(produtoDto.getNome());
        produto.setDescricao(produtoDto.getDescricao());
        produto.setPreco(produtoDto.getPreco());
        produto.setQuantidadeEstoque(produtoDto.getQuantidadeEstoque());
        produto.setCategoria(cat);

        return produtoRepository.save(produto);
    }

    public Produto update(Long id, ProdutoDto produtoDto) {
        Produto produtoExistente = produtoRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Produto não encontrado"));

        Categoria cat = categoriaRepository.findById(produtoDto.getCategoriaId())
                .orElseThrow(() -> new ObjectNotFoundException("Categoria não encontrada"));

        Produto produtoAtualizado = new Produto();
        produtoAtualizado.setNome(produtoDto.getNome());
        produtoAtualizado.setDescricao(produtoDto.getDescricao());
        produtoAtualizado.setPreco(produtoDto.getPreco());
        produtoAtualizado.setQuantidadeEstoque(produtoDto.getQuantidadeEstoque());
        produtoAtualizado.setQuantidadeEstoque(produtoDto.getQuantidadeEstoque());

        return produtoRepository.save(produtoAtualizado);
    }
}
