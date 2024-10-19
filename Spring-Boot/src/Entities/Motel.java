package com.Farmer.Farm4U.Entities.Motel;

import com.Farmer.Farm4U.Entities.Booking.Booking;
import com.Farmer.Farm4U.Entities.Farm.Farmer;
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
@Entity(name = "motel")
@Table
public class Motel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long motelId;
    @Column(nullable = false, unique = true, length = 50)
    private String localisation;
    @Column(nullable = false)
    private float price;
    @Column(nullable = false)
    private Boolean availability=false;
    @OneToMany(mappedBy = "motel")
    private List<Booking> reservation;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Farmer farmer;
}
