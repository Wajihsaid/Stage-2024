import React, { useState, useEffect } from "react";
import axios from "axios";
import MotelClient from "./MotelC.jsx";
import './booking.css'

function BookingClient() {
    const [motels, setMotels] = useState([]);

    const [bookingDate, setBookingDate] = useState("");
    const [endReservationDate, setEndReservationDate] = useState("");
console.log("motelssss",motels)
    useEffect(() => {
        fetchMotels();
    }, []);

    const fetchMotels = async () => {
        try {
            const response = await axios.get(`http://localhost:8080/motel`);
            setMotels(response.data);
        } catch (error) {
            console.error("Error fetching motels:", error);
        }
    };

    const handleBooking = async (motelId) => {
        if (!bookingDate || !endReservationDate) {
            alert("Please select both reservation and end reservation dates.");
            return;
        }
        if (new Date(endReservationDate) <= new Date(bookingDate)) {
            alert("End reservation date must be after the reservation date.");
            return;
        }

        const newBooking = {
            bookingDate,
            endReservation: endReservationDate,
            motel: { motelId }
        };

        try {
            await axios.post(`http://localhost:8080/booking`, newBooking);
            alert("Booking successful!");
        } catch (error) {
            console.error("Error creating booking:", error);
            alert("Booking failed. Please try again.");
        }
    };

    return (
        <>
        <div className="alli">
            <h1>Booking bag</h1>
            <div className="motel-booking">
                {motels.map((motel) => (
                    <div key={motel.motelId}>
                        <MotelClient
                            motelId={motel.motelId}
                            url={motel.url}
                            localisation={motel.localisation}
                            price={motel.price}
                            availability={motel.availability.toString()}
                        />
                        <div className="input-box">
                            <input
                                className="input-for"
                                type="date"
                                value={bookingDate}
                                onChange={(e) => setBookingDate(e.target.value)}
                            />
                        </div>
                        <div className="input-box">
                            <input
                                className="input-for"
                                type="date"
                                value={endReservationDate}
                                onChange={(e) => setEndReservationDate(e.target.value)}
                            />
                        </div>
                        <button
                            className="button"
                            onClick={() => handleBooking(motel.motelId)}
                        >
                            Book
                        </button>
                    </div>
                ))}
            </div>
        </div>
        </>
    );
}

export default BookingClient;
