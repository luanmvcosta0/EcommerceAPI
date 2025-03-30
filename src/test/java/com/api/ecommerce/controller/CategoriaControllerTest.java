package com.api.ecommerce.controller;

import com.api.ecommerce.dtos.CategoriaDto;
import com.api.ecommerce.models.Categoria;
import com.api.ecommerce.service.CategoriaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class CategoriaControllerTest {

    @Mock
    private CategoriaService categoriaService;

    @InjectMocks
    private CategoriaController categoriaController;

    private Categoria categoria;
    private CategoriaDto categoriaDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        categoria = new Categoria();
        categoria.setId(1L);
        categoria.setNome("Eletrônicos");
        categoria.setDescricao("Produtos eletrônicos em geral");

        categoriaDto = new CategoriaDto(categoria);
    }

    @Test
    void FindAll_Quando_buscar_todas_Entao_deve_retornar_uma_lista_de_categoriaDto() {
        when(categoriaService.findAll()).thenReturn(Arrays.asList(categoria));

        ResponseEntity<List<CategoriaDto>> listaCategoria = categoriaController.findAll();

        assertNotNull(listaCategoria);
        assertEquals(HttpStatus.OK, listaCategoria.getStatusCode());
        assertEquals(1, listaCategoria.getBody().size());
        assertEquals("Eletrônicos", listaCategoria.getBody().get(0).getNome());

        verify(categoriaService, times(1)).findAll();
    }

    @Test
    void FindById_Quando_buscar_por_id_Entao_deve_retornar_a_categoria() {
        when(categoriaService.findById(1L)).thenReturn(categoria);

        ResponseEntity<CategoriaDto> response = categoriaController.findById(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Eletrônicos", response.getBody().getNome());

        verify(categoriaService, times(1)).findById(1L);
    }

    @Test
    void Save_Quando_salvar_Entao_deve_retornar_uma_categoria_salva() {
        when(categoriaService.save(any(Categoria.class))).thenReturn(categoria);

        ResponseEntity<CategoriaDto> response = categoriaController.save(categoriaDto);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Eletrônicos", response.getBody().getNome());

        verify(categoriaService, times(1)).save(any(Categoria.class));
    }

    @Test
    void Update_Quando_encontrar_uma_categoria_Entao_deve_retornar_uma_categoria_atualizada() {
        when(categoriaService.update(eq(1L), any(Categoria.class))).thenReturn(categoria);

        ResponseEntity<CategoriaDto> response = categoriaController.update(1L, categoriaDto);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Eletrônicos", response.getBody().getNome());

        verify(categoriaService, times(1)).update(eq(1L), any(Categoria.class));
    }

    @Test
    void Delete_Quando_encontrar_uma_categoria_Entao_deve_deletar_categoria() {
        doNothing().when(categoriaService).delete(1L);

        ResponseEntity<Void> response = categoriaController.delete(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        verify(categoriaService, times(1)).delete(1L);
    }
}