package com.api.ecommerce.controller;

import com.api.ecommerce.dtos.ProdutoDto;
import com.api.ecommerce.models.Produto;
import com.api.ecommerce.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("")
    public ResponseEntity<List<ProdutoDto>> findAll() {
        List<Produto> produtos = produtoService.findAll();
        List<ProdutoDto> produtosDto = produtos.stream()
                .map(ProdutoDto::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(produtosDto);
    }

    @GetMapping("/categoria/{id}")
    public ResponseEntity<List<ProdutoDto>> findAllByCategoria(@PathVariable Long id) {
        List<ProdutoDto> produtos = produtoService.findAllByCategoria(id);
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProdutoDto> findById(@PathVariable Long id) {
        Produto produto = produtoService.findById(id);
        return ResponseEntity.ok(new ProdutoDto(produto));
    }
}