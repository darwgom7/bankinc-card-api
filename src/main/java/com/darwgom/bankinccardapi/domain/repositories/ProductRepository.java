package com.darwgom.bankinccardapi.domain.repositories;

import com.darwgom.bankinccardapi.domain.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Boolean existsByProductCode(String productCode);
    Optional<Product> findByProductCode(String productCode);

}
