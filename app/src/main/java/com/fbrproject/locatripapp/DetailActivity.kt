package com.fbrproject.locatripapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.fbrproject.locatrip.R
import com.fbrproject.locatripapp.checkout.PilihBangkuActivity
import com.fbrproject.locatripapp.home.dashboard.PenumpangAdapter
import com.fbrproject.locatripapp.model.Penumpang
import com.fbrproject.locatripapp.model.Trip
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_detail.*
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class DetailActivity : AppCompatActivity() {

    private lateinit var mDatabase: DatabaseReference
    private var dataList = ArrayList<Penumpang>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val data = intent.getParcelableExtra<Trip>("data")

        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DATE, 1)
        val tanggal = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(calendar.time)

        mDatabase = FirebaseDatabase.getInstance().getReference("Trip")
            .child(data?.judul.toString())
            .child("jadwal")
            .child(tanggal)

        val localeID = Locale("in", "ID")
        val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
        val harga = formatRupiah.format(data?.price)

        tv_trip.text = String.format("%s (%s 10.00)", data?.judul, tanggal)
        tv_harga.text = String.format(Locale.getDefault(), "%s/kursi", harga)
        tv_kapasitas.text = data?.kapasitas
        tv_desc.text = data?.desc
        tv_rate.text = data?.rating

        Glide.with(this)
            .load(data?.poster)
            .into(iv_poster)

        rv_penumpang.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        getData()

        btn_pilih_bangku.setOnClickListener {
            val intent =
                Intent(this@DetailActivity, PilihBangkuActivity::class.java).putExtra("data", data)
                    .putExtra("penumpang", dataList).putExtra("tanggal", tanggal)
            startActivity(intent)
        }
    }

    private fun getData() {
        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                dataList.clear()

                for (getdataSnapshot in p0.children) {
                    val penumpang = getdataSnapshot.getValue(Penumpang::class.java)
                    dataList.add(penumpang!!)
                }

                rv_penumpang.adapter = PenumpangAdapter(dataList) {}
            }

            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(this@DetailActivity, "" + p0.message, Toast.LENGTH_LONG).show()
            }

        })
    }
}