package com.example.effectivemobiletest.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.domain.model.hotelModel.Hotel
import com.example.effectivemobiletest.R
import com.example.effectivemobiletest.databinding.FragmentHotelBinding
import com.example.effectivemobiletest.helpers.InputData
import com.google.android.flexbox.FlexboxLayout
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class HotelFragment : Fragment() {
    private lateinit var _binding: FragmentHotelBinding

    private val mainViewModel: MainViewModel by sharedViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHotelBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.getHotel()
        mainViewModel.dataOfHotel.observe(viewLifecycleOwner) { hotel ->
            setPhotoToSlider(hotel)
            _binding.apply {
                with(hotelData) {
                    ratingName.text = hotel?.rating_name
                    ratingScore.text = hotel?.rating.toString()
                    hotelName.text = hotel?.name
                    hotelAdress.text = hotel?.adress
                    hotelAdress.setOnClickListener { }
                }
                tourPrice.text = "от ${hotel?.minimal_price?.let { InputData.getNumberFormat(it) }} ₽"
                priceTourDescription.text = hotel?.price_for_it?.lowercase()
                setHotelPeculiarities(hotel?.about_the_hotel?.peculiarities)
                hotelDescription.text = hotel?.about_the_hotel?.description
                btToChoosingRoom.setOnClickListener {
                val action = HotelFragmentDirections.actionHotelFragmentToRoomFragment(hotel?.name)
                    findNavController().navigate(action)
                }
            }
        }
    }

    private fun setHotelPeculiarities(peculiarities: List<String>?) {
        _binding.hotelPeculiriaties.removeAllViews()
        peculiarities?.forEach {
            val textView = TextView(context)
            val params = FlexboxLayout.LayoutParams(
                FlexboxLayout.LayoutParams.WRAP_CONTENT,
                FlexboxLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(2, 2, 2, 2)

            textView.apply {
                text = it
                textSize = 16f
                setTextColor(context.resources.getColor(R.color.gray))
                setPadding(10, 5, 5, 10)
                layoutParams = params
            }
            _binding.hotelPeculiriaties.addView(textView)
        }
    }

    private fun setPhotoToSlider(hotel: Hotel?) {
        if (hotel != null) {
            _binding.hotelPhotoSlider.sliderAdapter.setData(hotel.image_urls)
        }
    }
}
