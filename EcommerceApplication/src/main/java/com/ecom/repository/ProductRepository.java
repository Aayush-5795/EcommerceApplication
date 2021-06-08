package com.ecom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.entity.Category;
import com.ecom.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	List<Product> findByNameContaining(String productName);

	List<Product> findByNameContainingAndCategoryContaining(String productName, Category category);

	List<Product> findByCategory(Category category);

	List<Product> findByNameLike(String productName);

	List<Product> findByNameContainingAndCategory(String productName, Category category);

}
