package com.example.effectivemobiletest.presentation.views

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.effectivemobiletest.databinding.PhotoSliderBinding
import com.example.effectivemobiletest.presentation.adapters.SliderAdapter

class SlideView(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {

    private var _binding: PhotoSliderBinding
    private var slideRecyclerView: RecyclerView
    var sliderAdapter: SliderAdapter

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        _binding = PhotoSliderBinding.inflate(inflater, this, true)

        slideRecyclerView = _binding.sliderRecycler
        sliderAdapter = SliderAdapter(context)
        slideRecyclerView.adapter = sliderAdapter
    }
}