package com.Farmer.Farm4U.Entities.Category;

import com.Farmer.Farm4U.Entities.Product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name = "Category")
@Table
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long categoryId;
    @Column(nullable = false, length = 50, unique = true)
    private String categoryName;
    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
