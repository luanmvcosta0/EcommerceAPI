package com.api.ecommerce.repositories;

import com.api.ecommerce.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProtudoRepository extends JpaRepository<Produto, Long> {
}
