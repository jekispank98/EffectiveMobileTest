package com.example.domain.di

import com.example.domain.repo.BookingRepository
import com.example.domain.useCases.GetBookingInfoUseCase
import com.example.domain.useCases.GetHotelsUseCase
import com.example.domain.useCases.GetRoomsUseCase
import org.koin.dsl.module

val domainModule = module {

    factory<GetHotelsUseCase> {
        GetHotelsUseCase(bookingRepository = get())
    }

    factory<GetRoomsUseCase> {
        GetRoomsUseCase(bookingRepository = get())
    }
    factory<GetBookingInfoUseCase> {
        GetBookingInfoUseCase(bookingRepository = get())
    }
}