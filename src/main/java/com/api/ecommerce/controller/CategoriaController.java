package com.api.ecommerce.controller;

import com.api.ecommerce.dtos.CategoriaDto;
import com.api.ecommerce.models.Categoria;
import com.api.ecommerce.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jmx.export.annotation.ManagedOperation;
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
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("")
    public ResponseEntity<List<CategoriaDto>> findAll() {
        List<Categoria> categorias = categoriaService.findAll();
        List<CategoriaDto> categoriaDtos = categorias.stream()
                .map(CategoriaDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(categoriaDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDto> findById(@PathVariable Long id) {
        Categoria categoria = categoriaService.findById(id);
        return ResponseEntity.ok(new CategoriaDto(categoria));
    }

    @PostMapping("")
    public ResponseEntity<CategoriaDto> save(@Valid @RequestBody CategoriaDto categoriaDto) {
        Categoria categoria = new Categoria(categoriaDto);
        Categoria savedCategoria = categoriaService.save(categoria);
        return ResponseEntity.ok(new CategoriaDto(savedCategoria));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDto> update(@PathVariable Long id, @Valid @RequestBody CategoriaDto categoriaDto) {
        Categoria categoria = new Categoria(categoriaDto);
        Categoria updatedCategoria = categoriaService.update(id, categoria);
        return ResponseEntity.ok(new CategoriaDto(updatedCategoria));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
