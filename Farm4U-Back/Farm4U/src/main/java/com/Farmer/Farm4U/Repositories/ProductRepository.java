package com.Farmer.Farm4U.Repositories;


import com.Farmer.Farm4U.Entities.Product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Optional<Product> findByProductId(Long id);
    Optional<Product> findByProductName(String productName);
    Optional<Product> findByQuantity(long quantity);
    Optional<Product> findByPriceUni(double priceUni);
    @Query("SELECT p FROM Product p JOIN p.user f WHERE f.userName = :userName")
    List<Product> findByUserName(@Param("userName") String userName);
    @Query("SELECT p FROM Product p JOIN p.category c where c.categoryName=:categoryName")
    List<Product> findByCategoryName(@Param("categoryName") String categoryName);
}
