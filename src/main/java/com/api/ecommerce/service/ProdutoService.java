package com.api.ecommerce.service;

import com.api.ecommerce.dtos.ProdutoDto;
import com.api.ecommerce.models.Produto;
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
    private CategoriaService categoriaService;

    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    public List<ProdutoDto> findAllByCategoria(Long idCategoria) {
        categoriaService.findById(idCategoria);

        List<Produto> produtos = produtoRepository.findByCategoriaId(idCategoria);

        return produtos.stream()
                .map(ProdutoDto::new)
                .collect(Collectors.toList());
    }
}
