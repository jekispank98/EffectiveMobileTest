package com.example.domain.useCases

import com.example.domain.model.bookingModel.Booking
import com.example.domain.repo.BookingRepository

class GetBookingInfoUseCase(private val bookingRepository: BookingRepository) {

    suspend fun getBookingInfoUseCase(): Booking? {
        return bookingRepository.getBookingInfo()
    }
}