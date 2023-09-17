package com.example.effectivemobiletest.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.effectivemobiletest.R
import com.example.effectivemobiletest.databinding.FragmentPaidBinding
import com.example.effectivemobiletest.helpers.InputData

class PaidFragment : Fragment() {
    private lateinit var _binding: FragmentPaidBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPaidBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding.apply {
            btBack.setOnClickListener {
                val action = PaidFragmentDirections.actionPaidFragmentToBookingFragment()
                findNavController().navigate(action)
            }
            btSuper.setOnClickListener {
                val action = PaidFragmentDirections.actionPaidFragmentToHotelFragment()
                findNavController().navigate(action)
            }
            val randomOrder = InputData.getRandomOrderNumber()
            orderConfirmationText.text = getString(R.string.ORDER_IN_WORK_DESC, randomOrder)
        }
    }
}