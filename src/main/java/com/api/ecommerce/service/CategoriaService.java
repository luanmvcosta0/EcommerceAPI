package com.api.ecommerce.service;

import com.api.ecommerce.exceptions.ObjectNotFoundException;
import com.api.ecommerce.models.Categoria;
import com.api.ecommerce.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    public Categoria findById(Long id) {
        Optional<Categoria> cat = categoriaRepository.findById(id);
        if (cat.isPresent()) {
            return cat.get();
        }
        throw new ObjectNotFoundException("Categoria não encontrada");
    }

}
