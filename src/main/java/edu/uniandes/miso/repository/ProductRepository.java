package edu.uniandes.miso.repository;

import edu.uniandes.miso.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
