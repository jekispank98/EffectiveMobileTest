package com.example.effectivemobiletest.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.model.bookingModel.Booking
import com.example.domain.model.hotelModel.Hotel
import com.example.domain.model.roomModel.Rooms
import com.example.domain.useCases.GetBookingInfoUseCase
import com.example.domain.useCases.GetHotelsUseCase
import com.example.domain.useCases.GetRoomsUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val getHotels: GetHotelsUseCase,
    private val getRooms: GetRoomsUseCase,
    private val getBookingInfo: GetBookingInfoUseCase,
    private val application: Application
): ViewModel() {

    private var _dataOfHotel = MutableLiveData<Hotel?>()
    val dataOfHotel: LiveData<Hotel?> = _dataOfHotel

    private var _dataOfRooms = MutableLiveData<Rooms?>()
    val dataOfRooms: LiveData<Rooms?> = _dataOfRooms

    private var _bookingData = MutableLiveData<Booking?>()
    val bookingData: LiveData<Booking?> = _bookingData

    fun getHotel() {
        CoroutineScope(Dispatchers.Main).launch {

            val hotel = getHotels.getHotelsUseCase()
            _dataOfHotel.value = hotel
        }
    }

    fun getRooms() {
        CoroutineScope(Dispatchers.Main).launch {
            val rooms = getRooms.getRoomsUseCase()
            _dataOfRooms.value = rooms
        }
    }

    fun getBookingInfo() {
        CoroutineScope(Dispatchers.Main).launch {
            val bookingInfo = getBookingInfo.getBookingInfoUseCase()
            _bookingData.value = bookingInfo
        }
    }
}