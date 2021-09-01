package com.example.poorstore;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {

    List<Product> findByCategory(String category);

    List<Product> findAllByCategory(String category);

    Product findById(long id);

    Product findByProductName(String productName);

    @Query("SELECT p FROM Product p WHERE CONCAT(p.productName, p.category, p.price, p.quantity) LIKE %?1%")
    public List<Product> search(String keyword);

}
