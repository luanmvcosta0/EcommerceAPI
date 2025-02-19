package com.api.ecommerce.service;

import com.api.ecommerce.dtos.CategoriaDto;
import com.api.ecommerce.exceptions.BadRequestException;
import com.api.ecommerce.exceptions.ObjectNotFoundException;
import com.api.ecommerce.models.Categoria;
import com.api.ecommerce.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    public CategoriaDto findById(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Categoria não encontrada."));
        return new CategoriaDto(categoria);
    }

    public CategoriaDto save(CategoriaDto categoriaDto) {
        if (categoriaRepository.existsByNome(categoriaDto.getNome())) {
            throw new IllegalArgumentException("Categoria já existente.");
        }

        Categoria cat = new Categoria();
        cat.setNome(categoriaDto.getNome());
        cat.setDescricao(categoriaDto.getDescricao());

        cat = categoriaRepository.save(cat);
        return new CategoriaDto(cat);
    }

}
