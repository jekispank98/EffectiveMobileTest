package com.example.effectivemobiletest.presentation.views

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.example.effectivemobiletest.R
import com.example.effectivemobiletest.databinding.ElemTouristInfoBinding
import com.example.effectivemobiletest.helpers.TouristData
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class TouristInfoView(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {
    private var _binding: ElemTouristInfoBinding

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        _binding = ElemTouristInfoBinding.inflate(inflater, this, true)
        _binding.btHideShow.setOnClickListener {
            setHideShowVisibility()
        }
        for (index in 0 until _binding.touristBlockLinear.childCount) {
            val view = _binding.touristBlockLinear.getChildAt(index)
            view as TextInputLayout
            view.editText?.setOnFocusChangeListener { v, hasFocus ->
                if (hasFocus()) {
                    view.editText?.backgroundTintList =
                        resources.getColorStateList(R.color.background_gray, null)
                }
            }
        }

    }

    private fun setHideShowVisibility() {

        if (_binding.touristInputData.visibility == View.VISIBLE) {
            _binding.apply {
                touristInputData.visibility = View.GONE
                btHideShow.background = resources.getDrawable(R.drawable.ic_blue_arrow_show, null)
            }
        } else {
            _binding.apply {
                touristInputData.visibility = View.VISIBLE
                btHideShow.background = resources.getDrawable(R.drawable.ic_blue_arrow_hide, null)
            }
        }
    }
    fun setTouristNumber(number: Int) {
        _binding.numberOfTourist.text = "${TouristData.getNumber(number)} турист"
    }

    fun checkEmptyField(): Boolean {
        var allFieldIsFilled = true
        for (index in 0 until _binding.touristBlockLinear.childCount) {
            val view = _binding.touristBlockLinear.getChildAt(index)
            if ((view is TextInputLayout) && (view.editText?.text?.toString()?.isBlank() == true)) {
                view.editText?.backgroundTintList =
                    resources.getColorStateList(R.color.error_red, null)
                allFieldIsFilled = false
            }
        }
        return allFieldIsFilled
    }
}