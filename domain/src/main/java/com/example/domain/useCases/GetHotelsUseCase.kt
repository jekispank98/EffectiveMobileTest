package com.example.domain.useCases
import com.example.domain.model.hotelModel.Hotel
import com.example.domain.repo.BookingRepository

class GetHotelsUseCase(private val bookingRepository: BookingRepository) {

    suspend fun getHotelsUseCase(): Hotel? {
        return bookingRepository.getHotels()
    }
}