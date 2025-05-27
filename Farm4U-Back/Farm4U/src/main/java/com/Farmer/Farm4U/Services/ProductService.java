package com.Farmer.Farm4U.Services;

import com.Farmer.Farm4U.Entities.Category.Category;
import com.Farmer.Farm4U.Entities.Product.Product;
import com.Farmer.Farm4U.Entities.User.User;
import com.Farmer.Farm4U.Repositories.ProductRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final UserService userService;

    public ProductService(ProductRepository productRepository, CategoryService categoryService, UserService userService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.userService = userService;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getProductByFarmerName(String userName) {
        return productRepository.findByUserName(userName);
    }
    public List<Product> getProductByCategoryName(String categoryName) {
        return productRepository.findByCategoryName(categoryName);
    }

    public void addNewProduct(@NotNull Product product, String categoryName, String userName)  {
        Category category = categoryService.getCategoryByName(categoryName);
        User user=userService.getUserByName(userName);
        if (category == null) {
            throw new RuntimeException("Category not found");
        }
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        Optional<Product> productOptional = productRepository.findByProductId(product.getProductId());
        if (productOptional.isPresent()) {
            throw new IllegalStateException("Product already exist!");
        }
        product.setUser(user);
        product.setCategory(category);
        productRepository.save(product);
    }

    public void deleteProduct(Long productId) {
        productRepository.findByProductId(productId);
        boolean exists = productRepository.existsById(productId);
        if (exists) {
            productRepository.deleteById(productId);
        } else {
            throw new IllegalStateException("product with " + productId + " not found");
        }
    }
    @Transactional
    public void updateProduct ( long productId, String productName, long quantity,double priceUni){
        Product product = productRepository.findByProductId(productId).
                orElseThrow(() -> new IllegalStateException("product with " + productId + " not found"));
        if(productName != null && !productName.isEmpty() && !product.getProductName().equals(productName)) {
            Optional<Product> productOptional = productRepository.findByProductName(productName);
            if (productOptional.isPresent()) {
                throw new IllegalStateException("Product already in use");
            }
            product.setProductName(productName);
        }
        if(quantity > 0 && quantity != product.getQuantity()) {
            Optional<Product> productOptional = productRepository.findByQuantity(quantity);
            if (productOptional.isPresent()) {
                throw new IllegalStateException("Quantity already Updated");
            }
            product.setQuantity(quantity);
        }
        if(priceUni > 0 && priceUni != product.getPriceUni()) {
            Optional<Product> productOptional = productRepository.findByPriceUni(priceUni);
            if (productOptional.isPresent()) {
                throw new IllegalStateException("Price already Updated");
            }
            product.setPriceUni(priceUni);
        }
    }
}
