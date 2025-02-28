package com.api.ecommerce.service;

import com.api.ecommerce.dtos.CategoriaDto;
import com.api.ecommerce.exceptions.ObjectNotFoundException;
import com.api.ecommerce.models.Categoria;
import com.api.ecommerce.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<CategoriaDto> findAll() {
        return categoriaRepository.findAll()
                .stream()
                .map(CategoriaDto::new)
                .collect(Collectors.toList());
    }

    public CategoriaDto findById(Long id) {
        Categoria cat = categoriaRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Categoria não encontrada."));

        return new CategoriaDto(cat);
    }

    public CategoriaDto save(CategoriaDto categoriaDto) {
        if (categoriaRepository.existsByNome(categoriaDto.getNome())) {
            throw new IllegalArgumentException("Categoria já existente.");
        }

        Categoria catSalva = new Categoria(categoriaDto);
        return new CategoriaDto(categoriaRepository.save(catSalva));

//        Categoria cat = new Categoria();
//        cat.setNome(categoriaDto.getNome());
//        cat.setDescricao(categoriaDto.getDescricao());
//
//        cat = categoriaRepository.save(cat);
//        return new CategoriaDto(cat);
    }

    public CategoriaDto update(Long id, CategoriaDto categoriaDto) {
        Categoria cat = categoriaRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Categoria não encontrada."));

                cat.setNome(categoriaDto.getNome());
                cat.setDescricao(categoriaDto.getDescricao());

                cat = categoriaRepository.save(cat);
                return new CategoriaDto(cat);
    }

//    public void delete(Long id) {
//        Categoria cat = findById(id);
//        if (!cat.getProdutos().isEmpty()) {
//
//            throw new DataIntegrityViolationException("Não é possível excluir esta categoria, pois há produtos associados.");
//        }
//        categoriaRepository.deleteById(id);
//
//    }


}
