package com.example.effectivemobiletest.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.effectivemobiletest.R
import com.example.effectivemobiletest.databinding.FragmentRoomBinding
import com.example.effectivemobiletest.presentation.adapters.RoomAdapter
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class RoomFragment : Fragment() {
    private lateinit var _binding: FragmentRoomBinding
    private var hotel: String? = null
    private lateinit var roomRecyclerView: RecyclerView
    private lateinit var roomAdapter: RoomAdapter

    private val mainViewModel: MainViewModel by sharedViewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        hotel = arguments?.getString(HOTEL_PARAM)
        _binding = FragmentRoomBinding.inflate(inflater, container, false)
        roomRecyclerView = _binding.roomsRecycler
        roomAdapter = RoomAdapter(requireContext())
        roomRecyclerView.adapter = roomAdapter
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel.apply {
            getRooms()
            dataOfRooms.observe(viewLifecycleOwner){
                it?.rooms?.let { it1 -> roomAdapter.setData(it1) }
            }
        }
        _binding.apply {
            hotelName.text = hotel
            btBack.setOnClickListener {
                val action = RoomFragmentDirections.actionRoomFragmentToHotelFragment()
                findNavController().navigate(action)
            }
        }
    }

    companion object {
        private const val HOTEL_PARAM = "hotelName"
    }
}