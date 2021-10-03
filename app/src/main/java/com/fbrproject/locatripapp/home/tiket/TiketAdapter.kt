package com.fbrproject.locatripapp.home.tiket

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
import com.fbrproject.locatripapp.model.Checkout
import com.fbrproject.locatripapp.model.Penumpang
import com.fbrproject.locatripapp.model.Trip
import java.text.NumberFormat
import java.util.*

class TiketAdapter(
    private var data: List<Penumpang>,
    private val listener: (Penumpang) -> Unit
) :
    RecyclerView.Adapter<TiketAdapter.ViewHolder>() {
    lateinit var contextAdapter: Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflatedView = layoutInflater.inflate(R.layout.row_item_checkout_white, parent, false)
        return ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(data[position], listener, contextAdapter)
    }

    override fun getItemCount(): Int = data.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvTitle: TextView = view.findViewById(R.id.tv_kursi)

        fun bindItem(
            data: Penumpang,
            listener: (Penumpang) -> Unit,
            context: Context
        ) {

            tvTitle.setText("Kursi No. " + data.kursi)

            itemView.setOnClickListener {
                listener(data)
            }
        }
    }
}