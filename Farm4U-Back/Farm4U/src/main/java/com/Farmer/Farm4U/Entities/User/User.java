package com.Farmer.Farm4U.Entities.User;

import com.Farmer.Farm4U.Entities.Booking.Booking;
import com.Farmer.Farm4U.Entities.Delivery.Delivery;
import com.Farmer.Farm4U.Entities.Order.Order;
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
@Entity(name = "CLIENT")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("client")
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long userId;
    @Column(nullable = false, unique = true, length = 50)
    protected String userName;
    @Column(nullable = false, length = 50)
    protected String address;
    @Column(nullable = false, length = 50)
    protected String email;
    @Column(nullable = false, length = 50)
    protected String password;
    @Column(nullable = false, length = 8)
    protected long phone;
    @OneToMany(mappedBy = "user")
    protected List<Delivery> deliveryList;
    @OneToMany(mappedBy = "user")
    protected List<Order> orderList;
    @OneToMany(mappedBy = "user")
    protected List<Booking> reservationList;
    @OneToMany(mappedBy = "user" )
    protected List<Product> productList;
}
