package com.example.effectivemobiletest.presentation.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.effectivemobiletest.R
import org.koin.java.KoinJavaComponent.inject

class SliderAdapter(private val context: Context) : RecyclerView.Adapter<SliderAdapter.SliderHolder>() {
    private var slideList = emptyList<String>().toMutableList()

    class SliderHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView = itemView.findViewById<ImageView>(R.id.slide)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_slide, parent, false)
        return SliderHolder(view)
    }

    override fun onBindViewHolder(holder: SliderHolder, position: Int) {
        val imageUri = slideList[position]
        Glide.with(context).load(imageUri).into(holder.imageView)

    }

    override fun getItemCount(): Int {
        return slideList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<String>) {
        slideList = list.toMutableList()
        notifyDataSetChanged()
    }
}