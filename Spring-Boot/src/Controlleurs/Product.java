package com.Farmer.Farm4U.Controlleurs;

import com.Farmer.Farm4U.Entities.Product.Product;
import com.Farmer.Farm4U.Services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/product")
@CrossOrigin("http://localhost:5173")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }
    @GetMapping("/{useName}")
    public List<Product> getProduct(@PathVariable("useName") String userName){
        return productService.getProductByFarmerName(userName);
    }
    @GetMapping("/{categoryName}")
    public List<Product> getProductByCategoryName(@PathVariable("categoryName") String categoryName){
        return  productService.getProductByCategoryName(categoryName);
    }

    @PostMapping("/{userName}/{categoryName}")
    public void registerNewProduct(@RequestBody Product product, @PathVariable("categoryName") String categoryName,@PathVariable("userName") String userName){
        productService.addNewProduct(product, categoryName,userName);
    }
    @DeleteMapping(path = "/{productId}")
    public  void deleteProduct(@PathVariable("productId") Long productId){
        productService.deleteProduct(productId);
    }

    @PutMapping(path = "/{productId}")
    public void updateProduct(@PathVariable("productId") Long productId, @RequestBody Product product){
        productService.updateProduct(productId, product.getProductName(), product.getQuantity(),product.getPriceUni());
    }
}
