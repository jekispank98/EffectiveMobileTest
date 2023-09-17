package com.example.domain.useCases

import com.example.domain.model.roomModel.Rooms
import com.example.domain.repo.BookingRepository

class GetRoomsUseCase(private val bookingRepository: BookingRepository) {
    suspend fun getRoomsUseCase(): Rooms? {
        return bookingRepository.getRoom()
    }
}