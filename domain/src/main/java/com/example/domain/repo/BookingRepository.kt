package com.example.domain.repo

import com.example.domain.model.bookingModel.Booking
import com.example.domain.model.hotelModel.Hotel
import com.example.domain.model.roomModel.Rooms

interface BookingRepository {
    suspend fun getHotels(): Hotel?

    suspend fun getRoom(): Rooms?

    suspend fun getBookingInfo(): Booking?
}