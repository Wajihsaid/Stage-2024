package com.Farmer.Farm4U.Controlleurs;

import com.Farmer.Farm4U.Entities.Category.Category;
import com.Farmer.Farm4U.Services.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/category")
@CrossOrigin("http://localhost:5173")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @GetMapping
    public List<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }
    @PostMapping()
    public void registerNewProduct(@RequestBody Category category){
        categoryService.addNewCategory(category);
    }
    @DeleteMapping(path = "{categoryId}")
    public  void deleteCategory(@PathVariable("categoryId") Long categoryId){
        categoryService.deleteCategory(categoryId);
    }
    @PutMapping(path = "{categoryId}")
    public void updateCategory(@PathVariable("categoryId") Long categoryId,
                            @RequestParam(required = false) String categoryName){
        categoryService.updateCategory(categoryId,categoryName);
    }
}
