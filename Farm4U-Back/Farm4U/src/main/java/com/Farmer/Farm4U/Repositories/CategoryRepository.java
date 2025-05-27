package com.Farmer.Farm4U.Repositories;


import com.Farmer.Farm4U.Entities.Category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    Optional<Category> findByCategoryId(Long id);
    Optional<Category> findByCategoryName(String categoryName);
}
