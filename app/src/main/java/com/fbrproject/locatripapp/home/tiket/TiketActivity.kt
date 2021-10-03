package com.fbrproject.locatripapp.home.tiket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.fbrproject.locatrip.R
import com.fbrproject.locatripapp.home.HomeActivity
import com.fbrproject.locatripapp.model.Checkout
import com.fbrproject.locatripapp.model.Penumpang
import com.fbrproject.locatripapp.model.Trip
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_tiket.*
import kotlinx.android.synthetic.main.activity_tiket.tv_kapasitas
import kotlinx.android.synthetic.main.activity_tiket.tv_rate

class TiketActivity : AppCompatActivity() {

    private var dataList = ArrayList<Penumpang>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tiket)

        val data = intent.getParcelableExtra<Trip>("data")
        dataList = intent.getParcelableArrayListExtra<Penumpang>("dataList") as ArrayList<Penumpang>
        val tanggal = intent.getStringExtra("tanggal")

        if (dataList.isEmpty()) finish()

        tv_title.text = data?.judul
        tv_kapasitas.text = dataList.size.toString() + " Kursi"
        tv_rate.text = data?.rating
        textView39.text = tanggal + " 10.00"
        metode_pembayaran.text =
            "${getString(R.string.metode_pembayaran)} (${dataList[0].pembayaran})"

        Glide.with(this)
            .load(data?.poster)
            .into(iv_poster_image)

        rc_checkout.layoutManager = LinearLayoutManager(this)

        rc_checkout.adapter = TiketAdapter(dataList) {

        }
        iv_back.setOnClickListener {
            var intent = Intent(this,HomeActivity::class.java)
            startActivity(intent)
        }
    }
}