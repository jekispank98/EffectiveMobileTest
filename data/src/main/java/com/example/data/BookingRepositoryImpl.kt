package com.example.data

import com.example.data.api.ApiService
import com.example.domain.model.bookingModel.Booking
import com.example.domain.model.hotelModel.Hotel
import com.example.domain.model.roomModel.Rooms
import com.example.domain.repo.BookingRepository
import retrofit2.Response

class BookingRepositoryImpl(private val apiService: ApiService): BookingRepository {

    override suspend fun getHotels(): Hotel? {
        return apiService.getHotels().body()
    }

    override suspend fun getRoom(): Rooms? {
        return apiService.getRoom().body()
    }

    override suspend fun getBookingInfo(): Booking? {
        return apiService.getBookingInfo().body()
    }
}