package com.Farmer.Farm4U.Repositories;

import com.Farmer.Farm4U.Entities.Booking.Booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Long> {
    @Query("SELECT b FROM Booking b WHERE b.bookingId=?1  ")
    Optional<Booking> findByBookingId(Long bookingId);
    @Query("SELECT b FROM Booking b WHERE b.bookingDate=?1")
    Optional<Booking> findByBookingDate(LocalDate bookingDate);
    @Query("SELECT b FROM Booking b WHERE b.endReservation=?1")
    Optional<Booking> findByReservDateEnd(LocalDate endReservation);
}
