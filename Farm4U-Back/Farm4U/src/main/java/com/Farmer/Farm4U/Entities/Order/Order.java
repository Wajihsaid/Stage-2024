package com.Farmer.Farm4U.Entities.Order;

import com.Farmer.Farm4U.Entities.Delivery.Delivery;
import com.Farmer.Farm4U.Entities.Product.Product;
import com.Farmer.Farm4U.Entities.User.User;
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
@Entity(name = "Command")
@Table
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;
    @Column(nullable = false)
    private long quantitDem;
    private double prixTotal;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToMany
    private List<Product> products;
    @ManyToMany
    private List<Delivery> deliveries;
}
