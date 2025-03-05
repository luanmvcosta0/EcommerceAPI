package com.api.ecommerce.service;

import com.api.ecommerce.exceptions.ObjectNotFoundException;
import com.api.ecommerce.models.Categoria;
import com.api.ecommerce.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    public Categoria findById(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Categoria não encontrada."));
    }

    public Categoria save(Categoria categoria) {
        if (categoriaRepository.existsByNome(categoria.getNome())) {
            throw new IllegalArgumentException("Categoria já existente.");
        }
        return categoriaRepository.save(categoria);
    }

    public Categoria update(Long id, Categoria categoria) {
        Categoria cat = categoriaRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Categoria não encontrada."));

        cat.setNome(categoria.getNome());
        cat.setDescricao(categoria.getDescricao());

        return categoriaRepository.save(cat);
    }

    public void delete(Long id) {
        Categoria cat = categoriaRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Categoria não encontrada."));

        if (!cat.getProdutos().isEmpty()) {
            throw new DataIntegrityViolationException("Não é possível excluir esta categoria, pois há produtos associados.");
        }

        categoriaRepository.deleteById(id);
    }

}
