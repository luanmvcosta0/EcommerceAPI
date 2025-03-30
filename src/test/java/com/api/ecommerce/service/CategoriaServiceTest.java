package com.api.ecommerce.service;

import com.api.ecommerce.models.Categoria;
import com.api.ecommerce.repositories.CategoriaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.api.ecommerce.exceptions.ObjectNotFoundException;
import com.api.ecommerce.models.Categoria;
import com.api.ecommerce.repositories.CategoriaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoriaServiceTest {

    @InjectMocks
    private CategoriaService categoriaService;

    @Mock
    private CategoriaRepository categoriaRepository;

    private Categoria categoria;

    @BeforeEach
    void setUp() {
        categoria = new Categoria();
        categoria.setId(1L);
        categoria.setNome("Eletrônicos");
        categoria.setDescricao("Produtos eletrônicos em geral");
    }

    @Test
    void Dado_uma_lista_Quando_buscar_todas_Entao_deve_retornar_uma_lista_de_categorias() {
        when(categoriaRepository.findAll()).thenReturn(Arrays.asList(categoria));

        List<Categoria> categorias = categoriaService.findAll();

        assertNotNull(categorias);
        assertFalse(categorias.isEmpty());
        assertEquals(1, categorias.size());
        verify(categoriaRepository, times(1)).findAll();
    }

    @Test
    void Dado_id_existente_Quando_buscar_por_id_Entao_deve_retornar_a_categoria() {
        when(categoriaRepository.findById(1L)).thenReturn(Optional.of(categoria));

        Categoria foundCategoria = categoriaService.findById(1L);

        assertNotNull(foundCategoria);
        assertEquals(1L, foundCategoria.getId());
        verify(categoriaRepository, times(1)).findById(1L);
    }

    @Test
    void Dado_id_inexistente_Quando_buscar_por_id_Entao_deve_retornar_exception() {
        when(categoriaRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> categoriaService.findById(2L));
        verify(categoriaRepository, times(1)).findById(2L);
    }

    @Test
    void Dado_uma_categoria_valida_Quando_salvar_Entao_deve_retornar_categoria_salva() {
    }

    @Test
    void Dado_categoria_ja_existente_Quando_salvar_Entao_deve_retornar_exception() {
    }

    @Test
    void Dado_id_existente_Quando_atualizar_Entao_deve_retornar_categoria_atualizada() {
    }

    @Test
    void Dado_id_inexistente_Quando_atualizar_Entao_deve_lancar_exeption() {
    }

    @Test
    void Dado_id_existente_Quando_excluir_Deve_retornar_rmover_categoria() {
    }

    @Test
    void Dado_categoria_com_produtos_Quando_excluir_Entao_deve_lancar_exception() {
    }
}