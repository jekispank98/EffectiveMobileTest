package com.example.data.di

import com.example.data.BookingRepositoryImpl
import com.example.data.api.ApiService
import com.example.domain.repo.BookingRepository
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


private const val BASE_URL = "https://run.mocky.io/v3/"

val dataModule = module {

    single<ApiService> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }
    single<BookingRepository> {
        BookingRepositoryImpl(apiService = get())
    }
}
