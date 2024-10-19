package com.Farmer.Farm4U.Services;

import com.Farmer.Farm4U.Entities.Category.Category;
import com.Farmer.Farm4U.Repositories.CategoryRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category getCategoryByName(String categoryName) {
        Optional<Category> optionalCategory = categoryRepository.findByCategoryName(categoryName);
        if (optionalCategory.isPresent()) {
            return optionalCategory.get();
        }
        else
            return null;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public void addNewCategory(@NotNull Category category) {
        Optional<Category> categoryOptional = categoryRepository.findByCategoryId(category.getCategoryId());
        if (categoryOptional.isPresent()) {
            throw new IllegalStateException("Category already exist!");
        }
        categoryRepository.save(category);
    }

    public void deleteCategory(Long categoryId) {
        categoryRepository.findByCategoryId(categoryId);
        boolean exists = categoryRepository.existsById(categoryId);
        if (exists) {
            categoryRepository.deleteById(categoryId);
        } else {
            throw new IllegalStateException("Category with " + categoryId + " not found");
        }
    }
    @Transactional
    public void updateCategory ( long categoryId, String categoryName){
        Category category = categoryRepository.findByCategoryId(categoryId).
                orElseThrow(() -> new IllegalStateException("category with " + categoryId + " not found"));
        if(categoryName != null && !categoryName.isEmpty() && !category.getCategoryName().equals(categoryName)) {
            Optional<Category> categoryOptional = categoryRepository.findByCategoryName(categoryName);
            if (categoryOptional.isPresent()) {
                throw new IllegalStateException("Category already exist");
            }
            category.setCategoryName(categoryName);
        }

    }
}
