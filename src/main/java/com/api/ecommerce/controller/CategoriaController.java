package com.api.ecommerce.controller;

import com.api.ecommerce.dtos.CategoriaDto;
import com.api.ecommerce.models.Categoria;
import com.api.ecommerce.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<List<CategoriaDto>> findAll() {
        return ResponseEntity.ok(categoriaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<CategoriaDto> save(@RequestBody CategoriaDto categoriaDto) {
        return ResponseEntity.ok(categoriaService.save(categoriaDto));
    }

//    @PutMapping("{id}")
//    public ResponseEntity<CategoriaDto> update(@PathVariable Long id, @RequestBody CategoriaDto categoriaDto) {
//        CategoriaDto catAtualiza = categoriaService.update(id, categoriaDto);
//        return ResponseEntity.ok().body(catAtualiza);
//    }
//
//    @DeleteMapping("{id}")
//    public ResponseEntity<Void> delete(@PathVariable Long id) {
//        categoriaService.delete(id);
//        return ResponseEntity.noContent().build();
//    }

}
