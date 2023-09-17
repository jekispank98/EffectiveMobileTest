package com.example.effectivemobiletest.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.forEach
import androidx.core.view.get
import androidx.core.view.iterator
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.domain.model.bookingModel.Booking
import com.example.effectivemobiletest.R
import com.example.effectivemobiletest.databinding.FragmentBookingBinding
import com.example.effectivemobiletest.helpers.InputData
import com.example.effectivemobiletest.presentation.views.TouristInfoView
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import ru.tinkoff.decoro.MaskImpl
import ru.tinkoff.decoro.slots.PredefinedSlots
import ru.tinkoff.decoro.watchers.MaskFormatWatcher

class BookingFragment : Fragment() {
    private lateinit var _binding: FragmentBookingBinding
    private val mainViewModel: MainViewModel by sharedViewModel()
    private var touristNumber = 1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBookingBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setPhoneEntering()
        mainViewModel.apply {
            getBookingInfo()
            bookingData.observe(viewLifecycleOwner) { booking ->
                setInfoAboutBooking(booking)
            }
        }
    }

    private fun setInfoAboutBooking(booking: Booking?) {
        _binding.apply {
            with(hotelInfo) {
                ratingScore.text = booking?.horating.toString()
                ratingName.text = booking?.rating_name
                hotelName.text = booking?.hotel_name
                hotelAdress.text = booking?.hotel_adress
            }
            departureFrom.text = booking?.departure
            countryCity.text = booking?.arrival_country
            dates.text = "${booking?.tour_date_start} - ${booking?.tour_date_stop}"
            countOfNights.text = booking?.number_of_nights.toString()
            hotelName.text = booking?.hotel_name
            room.text = booking?.room
            food.text = booking?.nutrition
            tourCost.text = "${booking?.tour_price?.let { InputData.getNumberFormat(it) }} ₽"
            fuelCost.text = "${booking?.fuel_charge?.let { InputData.getNumberFormat(it) }} ₽"
            serviceCost.text = "${booking?.service_charge?.let { InputData.getNumberFormat(it) }} ₽"
            toBePaid.text = "${InputData.getNumberFormat(calculateCost(booking))} ₽"
            btBack.setOnClickListener {
                val action =
                    BookingFragmentDirections.actionBookingFragmentToRoomFragment(booking?.hotel_name)
                findNavController().navigate(action)
            }
            btPay.apply {
                text = "${resources.getText(R.string.TO_PAY)} ${
                    InputData.getNumberFormat(
                        calculateCost(booking)
                    )
                } ₽"
                setOnClickListener {
                    if (checkFillingOfEditText()) {
                        val action = BookingFragmentDirections.actionBookingFragmentToPaidFragment()
                        findNavController().navigate(action)
                    }
                    _binding.touristLinear.forEach {
                        if (it is TouristInfoView) {
                            it.checkEmptyField()
                        }
                    }
                }
            }

            btAddTourist.setOnClickListener {
                val newTourist = TouristInfoView(requireContext(), null)
                newTourist.setTouristNumber(touristNumber)
                _binding.touristLinear.addView(newTourist)
                touristNumber++
            }
            mailEditText.setOnFocusChangeListener { view, hasFocus ->
                if (!view.hasFocus() &&
                    !InputData.checkEnteredMail(mailEditText.text.toString())
                ) {
                    setErrorColorToView(_binding.mailEditText)
                } else if (mailEditText.text?.isEmpty() == true || mailEditText.hasFocus()) _binding.mailEditText.backgroundTintList =
                    resources.getColorStateList(R.color.background_gray, null)
            }

        }
    }

    private fun checkFillingOfEditText(): Boolean {
        var filled = true
        for (view in 0 until _binding.touristLinear.childCount) {
            val touristInfoView = _binding.touristLinear.getChildAt(view) as TouristInfoView
            val touristInfoIsFilled = touristInfoView.checkEmptyField()
            if (filled && !touristInfoIsFilled) filled = false
        }
        if (!InputData.checkEnteredMail(_binding.mailEditText.text.toString()) ||
            !InputData.checkEnteredPhone(_binding.phoneEditText.text.toString())
        ) {
            filled = false
            setErrorColorToView(_binding.mailEditText)
            setErrorColorToView(_binding.phoneEditText)
        }


        return filled
    }

    private fun setPhoneEntering() {
        val phoneEditText = _binding.phoneEditText

        phoneEditText.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus && phoneEditText.text?.isEmpty() == true) {
                phoneEditText.backgroundTintList =
                    resources.getColorStateList(R.color.background_gray, null)
                phoneEditText.setText("+7")
                phoneEditText.text?.let { phoneEditText.setSelection(it.length) }

            } else if (hasFocus || (!hasFocus && phoneEditText.text?.length == 18)) {
                phoneEditText.backgroundTintList =
                    resources.getColorStateList(R.color.background_gray, null)
            } else if (!hasFocus &&
                !InputData.checkEnteredPhone(phoneEditText.text.toString())
            ) {
                setErrorColorToView(_binding.phoneEditText)
            }
            val mask = MaskImpl.createTerminated(PredefinedSlots.RUS_PHONE_NUMBER)
            val watcher = MaskFormatWatcher(mask)
            watcher.installOn(phoneEditText)
        }
    }


    private fun calculateCost(booking: Booking?): Int {
        return if (booking != null) {
            booking.service_charge + booking.fuel_charge + booking.tour_price
        } else 0
    }

    private fun setErrorColorToView(view: View) {
        view.backgroundTintList =
            resources.getColorStateList(R.color.error_red, null)
    }
}