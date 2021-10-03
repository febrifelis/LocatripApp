package com.fbrproject.locatripapp.home.dashboard

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fbrproject.locatrip.R
import com.fbrproject.locatripapp.model.Trip

class TripBaruAdapter(private var data: List<Trip>,
                      private val listener:(Trip) -> Unit)
    : RecyclerView.Adapter<TripBaruAdapter.ViewHolder>() {

    lateinit var contextAdapter : Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TripBaruAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflatedView = layoutInflater.inflate(R.layout.row_item_trip_baru, parent, false)
        return ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: TripBaruAdapter.ViewHolder, position: Int) {
        holder.bindItem(data[position], listener, contextAdapter)
    }

    override fun getItemCount(): Int = data.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvTitle:TextView = view.findViewById(R.id.tv_kursi)
        private val tvKapasitas:TextView = view.findViewById(R.id.tv_kapasitas)
        private val tvRate:TextView = view.findViewById(R.id.tv_rate)

        private val tvImage:ImageView = view.findViewById(R.id.iv_poster_image)

        fun bindItem(data:Trip, listener: (Trip) -> Unit, context : Context) {
            tvTitle.setText(data.judul)
            tvKapasitas.setText(data.kapasitas)
            tvRate.setText(data.rating)

            Glide.with(context)
                .load(data.poster)
                .into(tvImage)

            itemView.setOnClickListener{
                listener(data)
            }
        }
    }

}
