package com.Farmer.Farm4U.Services;

import com.Farmer.Farm4U.Entities.Booking.Booking;

import com.Farmer.Farm4U.Repositories.BookingRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public void addNewBooking(@NotNull Booking booking) {
        Optional<Booking> bookingOptional = bookingRepository.findByBookingDate(booking.getBookingDate());
        if (bookingOptional.isPresent()) {
            throw new IllegalStateException("Date already in reserved");
        }
        bookingRepository.save(booking);
    }

    public void deleteBooking(Long bookingId) {
        bookingRepository.findByBookingId(bookingId);
        boolean exists = bookingRepository.existsById(bookingId);
        if (exists) {
            bookingRepository.deleteById(bookingId);
        } else {
            throw new IllegalStateException("booking with " + bookingId + " not found");
        }
    }
    @Transactional
    public void updateBooking (long bookingId, LocalDate bookingdate, LocalDate endReservation){

        Booking booking = bookingRepository.findByBookingId(bookingId).orElseThrow(() -> new IllegalStateException("booking with " + bookingId + " not found"));
        if(bookingdate != null && !bookingdate.equals(booking.getBookingDate())) {
            Optional<Booking> bookingOptional = bookingRepository.findByBookingDate(bookingdate);
            if (bookingOptional.isPresent()) {
                throw new IllegalStateException("date already reserved");
            }
            booking.setBookingDate(bookingdate);
        }
        if(endReservation != null && !endReservation.equals(booking.getEndReservation())) {
            Optional<Booking> bookingOptional = bookingRepository.findByReservDateEnd(endReservation);
            if (bookingOptional.isPresent()) {
                throw new IllegalStateException("address already in use");
            }
            booking.setEndReservation(endReservation);
        }
    }
}
