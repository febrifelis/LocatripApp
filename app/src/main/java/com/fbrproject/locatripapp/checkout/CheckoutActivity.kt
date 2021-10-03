package com.fbrproject.locatripapp.checkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.fbrproject.locatrip.R
import com.fbrproject.locatrip.utils.Preferences
import com.fbrproject.locatripapp.model.Checkout
import com.fbrproject.locatripapp.model.Penumpang
import com.fbrproject.locatripapp.model.Trip
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_check_out.*
import kotlinx.android.synthetic.main.activity_pilih_bangku.*
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class CheckoutActivity : AppCompatActivity() {

    private var dataList = ArrayList<Checkout>()
    private var trip = Trip()
    private var tanggal = ""
    private var total: Long = 0L
    private lateinit var preferences: Preferences
    private lateinit var mDatabase: FirebaseDatabase
    private lateinit var tripDatabase: DatabaseReference
    private lateinit var userDatabase: DatabaseReference
    var saldo = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_out)

        if (!intent.hasExtra("trip") || !intent.hasExtra("tanggal")) {
            finish()
        }

        dataList = intent.getSerializableExtra("data") as ArrayList<Checkout>
        trip = intent.getParcelableExtra<Trip>("trip") as Trip

        preferences = Preferences(this)

        tanggal = intent.getStringExtra("tanggal") ?: ""
        mDatabase = FirebaseDatabase.getInstance()
        tripDatabase = mDatabase.getReference("Trip")
            .child(trip.judul.toString())
            .child("jadwal")
            .child(tanggal)
        userDatabase = mDatabase.getReference("User")
            .child(preferences.getValues("user") ?: "")

        saldo = preferences.getLongValues("saldo")

        for (a in dataList.indices) {
            total += trip.price ?: 0
        }

        dataList.add(Checkout("Total harus dibayar", total))

        val localeID = Locale("in", "ID")
        val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
        tv_saldo.setText(formatRupiah.format(preferences.getLongValues("saldo")))

        ArrayAdapter.createFromResource(
            this,
            R.array.metode_pembayaran,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner_pembayaran.adapter = adapter
        }
        spinner_pembayaran.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
                if (pos == 0) {
                    textView21.visibility = View.VISIBLE
                    textView22.visibility = View.VISIBLE
                    tv_saldo.visibility = View.VISIBLE
                } else {
                    textView21.visibility = View.GONE
                    textView22.visibility = View.GONE
                    tv_saldo.visibility = View.GONE
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }

        btn_tiket.setOnClickListener {
            if (spinner_pembayaran.selectedItemPosition == 0) {
                if (saldo >= total) {
                    checkout()
                } else {
                    Toast.makeText(
                        this,
                        "Saldo pada e-wallet kamu tidak mencukupi untuk melakukan transaksi",
                        Toast.LENGTH_LONG
                    ).show()
                }
            } else {
                checkout()
            }
        }

        btn_home1.setOnClickListener {
            finish()
        }

        textView17.text = String.format("%s, 10.00", tanggal)
        rc_checkout.layoutManager = LinearLayoutManager(this)
        rc_checkout.adapter = CheckoutAdapter(dataList) {
        }
    }

    private fun checkout() {
        val metodePembayaran = if (spinner_pembayaran.selectedItemPosition == 0) {
            "e-wallet"
        } else {
            "cash"
        }

        val daftarPenumpang = ArrayList<Penumpang>()
        for (i in 0..dataList.size - 2) {
            val penumpang = Penumpang(
                preferences.getValues("user"),
                preferences.getValues("nama"),
                preferences.getValues("url"),
                dataList[i].kursi,
                metodePembayaran
            )
            daftarPenumpang.add(penumpang)

            if (i == dataList.size - 2) {
                tripDatabase.child(dataList[i].kursi ?: "").setValue(penumpang)
                    .addOnSuccessListener {
                        if (spinner_pembayaran.selectedItemPosition == 0) {
                            saldo -= total
                            preferences.setLongValues("saldo", saldo)

                            userDatabase.child("saldo").setValue(saldo)
                        }

                        Toast.makeText(this, "Berhasil melakukan pembayaran!", Toast.LENGTH_LONG)
                            .show()
                        val intent =
                            Intent(this@CheckoutActivity, CheckoutSuccessActivity::class.java)
                                .putExtra("trip", trip)
                                .putExtra("daftarPenumpang", daftarPenumpang)
                                .putExtra("tanggal", tanggal)
                        startActivity(intent)
                    }
                    .addOnFailureListener {
                        return@addOnFailureListener
                    }
            } else {
                tripDatabase.child(dataList[i].kursi ?: "").setValue(penumpang)
                    .addOnFailureListener {
                        return@addOnFailureListener
                    }
            }
        }
    }
}