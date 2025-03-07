package com.api.ecommerce.controller;

import com.api.ecommerce.dtos.ProdutoDto;
import com.api.ecommerce.models.Produto;
import com.api.ecommerce.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/produto")
@CrossOrigin("*")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Operation(summary = "Busca toodos os produtos existentes")
    @GetMapping("")
    public ResponseEntity<List<ProdutoDto>> findAll() {
        List<Produto> produtos = produtoService.findAll();
        List<ProdutoDto> produtosDto = produtos.stream()
                .map(ProdutoDto::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(produtosDto);
    }

    @Operation(summary = "Busca todos os produtos de uma categoria")
    @GetMapping("/categoria/{id}")
    public ResponseEntity<List<ProdutoDto>> findAllByCategoria(@PathVariable Long id) {
        List<ProdutoDto> produtos = produtoService.findAllByCategoria(id);
        return ResponseEntity.ok(produtos);
    }

    @Operation(summary = "Busca o produto pelo ID")
    @GetMapping("{id}")
    public ResponseEntity<ProdutoDto> findById(@PathVariable Long id) {
        Produto produto = produtoService.findById(id);
        return ResponseEntity.ok(new ProdutoDto(produto));
    }

    @Operation(summary = "Cadastra um novo produto")
    @PostMapping("")
    public ResponseEntity<ProdutoDto> save(@Valid @RequestBody ProdutoDto produtoDto){
        Produto produtoSalvo = produtoService.save(produtoDto);
        return ResponseEntity.ok(new ProdutoDto(produtoSalvo));
    }

    @Operation(summary = "Atualiza o produto")
    @PutMapping("{id}")
    public ResponseEntity<ProdutoDto> update(@PathVariable Long id, @Valid @RequestBody ProdutoDto produtoDto) {
        Produto produtoAtualizado = produtoService.update(id, produtoDto);
        return ResponseEntity.ok(new ProdutoDto(produtoAtualizado));
    }

    @Operation(summary = "Deleta o produto")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        produtoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}