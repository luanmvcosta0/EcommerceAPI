package com.api.ecommerce.repositories;

import com.api.ecommerce.models.Categoria;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class CategoriaRepositoryTest {

    @Autowired
    CategoriaRepository categoriaRepository;

    @Test
    void Dado_um_nome_Quando_existente_Deve_retornar_uma_categoria() {
        Categoria categoria = new Categoria();
        categoria.setNome("Eletrônicos");
        categoriaRepository.save(categoria);

        boolean exists = categoriaRepository.existsByNome("Eletrônicos");

        assertThat(exists).isTrue();
    }

    @Test
    void Dado_um_nome_Quando_nao_existente_Deve_retornar_uma_categoria_falsa() {
        boolean exists = categoriaRepository.existsByNome("Alimentos");

        assertThat(exists).isFalse();

    }
}