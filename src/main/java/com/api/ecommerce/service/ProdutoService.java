package com.api.ecommerce.service;

import com.api.ecommerce.dtos.ProdutoDto;
import com.api.ecommerce.exceptions.ObjectNotFoundException;
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

    public List<ProdutoDto> findAll() {
        return produtoRepository.findAll()
                .stream()
                .map(ProdutoDto::new)
                .collect(Collectors.toList());
    }

    public ProdutoDto findById(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Produto não encontrado"));

        return new ProdutoDto(produto);
    }

}
