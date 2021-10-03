package com.fbrproject.locatripapp.home.tiket

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fbrproject.locatrip.R
import com.fbrproject.locatripapp.model.Trip

class TiketListAdapter(
    private var data: List<Pair<Trip, Int>>,
    private val listener: (Pair<Trip, Int>) -> Unit
) : RecyclerView.Adapter<TiketListAdapter.ViewHolder>() {

    lateinit var contextAdapter: Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflatedView = layoutInflater.inflate(R.layout.row_item_tiket, parent, false)
        return ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(data[position], listener, contextAdapter)
    }

    override fun getItemCount(): Int = data.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvTitle: TextView = view.findViewById(R.id.tv_judul)
        private val tvKursiDipesan: TextView = view.findViewById(R.id.tv_kursiDipesan)

        private val tvImage: ImageView = view.findViewById(R.id.iv_poster_image)

        fun bindItem(data: Pair<Trip, Int>, listener: (Pair<Trip, Int>) -> Unit, context: Context) {
            tvTitle.text = data.first.judul
            tvKursiDipesan.text = data.second.toString() + " Kursi"

            Glide.with(context)
                .load(data.first.poster)
                .into(tvImage)

            itemView.setOnClickListener {
                listener(data)
            }
        }
    }

}
