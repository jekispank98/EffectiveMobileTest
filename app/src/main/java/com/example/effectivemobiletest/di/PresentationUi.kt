package com.example.effectivemobiletest.di

import com.example.effectivemobiletest.presentation.MainViewModel
import com.example.effectivemobiletest.presentation.adapters.SliderAdapter
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {

    viewModel<MainViewModel> {
        MainViewModel(
            getHotels = get(),
            getRooms = get(),
            getBookingInfo = get(),
            application = get()
        )
    }
    single {
        SliderAdapter(context = get())
    }
}