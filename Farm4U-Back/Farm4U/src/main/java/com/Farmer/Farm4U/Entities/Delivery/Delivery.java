package com.Farmer.Farm4U.Entities.Delivery;



import com.Farmer.Farm4U.Entities.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;



@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name = "Delevery")
@Table
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long deliveryId;
    @Column(unique = true, nullable = false, length = 50)
    private String deliveryName;
    @Column(unique = true, nullable = false, length = 8)
    private long phone;
    @Column(nullable = false, length = 5)
    private double cotisation;
    @Column(nullable = false)
    private boolean deliveryStatus=false;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
