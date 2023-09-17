package com.example.effectivemobiletest.presentation.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.roomModel.Room
import com.example.effectivemobiletest.R
import com.example.effectivemobiletest.helpers.InputData
import com.example.effectivemobiletest.presentation.RoomFragmentDirections
import com.example.effectivemobiletest.presentation.views.SlideView
import com.google.android.flexbox.FlexboxLayout

class RoomAdapter(private val context: Context): RecyclerView.Adapter<RoomAdapter.RoomHolder>() {

    private var roomList = emptyList<Room>().toMutableList()

    class RoomHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val roomPhotos = itemView.findViewById<SlideView>(R.id.room_photo_slider)
        val roomName = itemView.findViewById<TextView>(R.id.room_name)
        val roomPrice = itemView.findViewById<TextView>(R.id.room_price)
        val priceRoomDescription = itemView.findViewById<TextView>(R.id.price_room_description)
        val pecularities = itemView.findViewById<FlexboxLayout>(R.id.room_peculiriaties)
        val btToBooking = itemView.findViewById<AppCompatButton>(R.id.bt_choose_room)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_room, parent, false)
        return RoomHolder(view)
    }

    override fun getItemCount(): Int {
        return roomList.size
    }

    override fun onBindViewHolder(holder: RoomHolder, position: Int) {
        val room = roomList[position]
        holder.apply {
            roomPhotos.sliderAdapter.setData(room.image_urls)
            roomName.text = room.name
            roomPrice.text = "${InputData.getNumberFormat(room.price)} ₽"
            priceRoomDescription.text = room.price_per
            convertHotelPeculiarities(room.peculiarities).forEach {
                holder.pecularities.addView(it)
            }
            btToBooking.setOnClickListener {
                val action = RoomFragmentDirections.actionRoomFragmentToBookingFragment()
                holder.itemView.findNavController().navigate(action)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<Room>) {
        roomList = list.toMutableList()
        notifyDataSetChanged()
    }

    private fun convertHotelPeculiarities(peculiarities: List<String>?): List<TextView> {
        val list = emptyList<TextView>().toMutableList()
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
            list.add(textView)
        }
        return list
    }
}