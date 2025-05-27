package com.Farmer.Farm4U.Controlleurs;

import com.Farmer.Farm4U.Entities.Booking.Booking;

import com.Farmer.Farm4U.Services.BookingService;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController()
@RequestMapping("/booking")
@CrossOrigin("http://localhost:5173")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }
    @GetMapping
    public List<Booking> getAllBookings(){
        return bookingService.getAllBookings();
    }
    @PostMapping()
    public void registerNewBooking(@RequestBody Booking booking){
        bookingService.addNewBooking(booking);
    }
    @DeleteMapping(path = "{bookingId}")
    public  void deleteBooking(@PathVariable("bookingId") Long bookingId){
        bookingService.deleteBooking(bookingId);
    }
    @PutMapping(path = "{bookingId}")
    public void updateBooking(@PathVariable("bookingId") Long bookingId,
                              @RequestParam(required = false)LocalDate bookingDate,
                              @RequestParam(required = false)LocalDate endreservdate){
        bookingService.updateBooking(bookingId, bookingDate, endreservdate);
    }
}
