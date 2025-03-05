package com.api.ecommerce.service;

import com.api.ecommerce.dtos.ProdutoDto;
import com.api.ecommerce.exceptions.ObjectNotFoundException;
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
}
