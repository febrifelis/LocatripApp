package com.fbrproject.locatripapp.checkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.fbrproject.locatrip.R
import com.fbrproject.locatripapp.model.Checkout
import com.fbrproject.locatripapp.model.Penumpang
import com.fbrproject.locatripapp.model.Trip
import kotlinx.android.synthetic.main.activity_pilih_bangku.*

class PilihBangkuActivity : AppCompatActivity() {

    var statusA1: Boolean = false
    var statusA2: Boolean = false
    var statusA3: Boolean = false
    var statusA4: Boolean = false
    var statusB1: Boolean = false
    var statusB2: Boolean = false
    var statusB3: Boolean = false
    var statusB4: Boolean = false
    var statusC1: Boolean = false
    var statusC2: Boolean = false
    var statusC3: Boolean = false
    var statusC4: Boolean = false
    var statusD1: Boolean = false
    var statusD2: Boolean = false
    var statusD3: Boolean = false
    var statusD4: Boolean = false
    var total: Int = 0

    var isAvailableA1: Boolean = true
    var isAvailableA2: Boolean = true
    var isAvailableA3: Boolean = true
    var isAvailableA4: Boolean = true
    var isAvailableB1: Boolean = true
    var isAvailableB2: Boolean = true
    var isAvailableB3: Boolean = true
    var isAvailableB4: Boolean = true
    var isAvailableC1: Boolean = true
    var isAvailableC2: Boolean = true
    var isAvailableC3: Boolean = true
    var isAvailableC4: Boolean = true
    var isAvailableD1: Boolean = true
    var isAvailableD2: Boolean = true
    var isAvailableD3: Boolean = true
    var isAvailableD4: Boolean = true

    private lateinit var daftarPenumpang: ArrayList<Penumpang>

    private var dataList = ArrayList<Checkout>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pilih_bangku)

        imageView3.setOnClickListener { onBackPressed() }

        val data = intent.getParcelableExtra<Trip>("data")
        daftarPenumpang =
            intent.getParcelableArrayListExtra<Penumpang>("penumpang") as ArrayList<Penumpang>

        tv_kursi.text = data!!.judul

        for (penumpang in daftarPenumpang) {
            when (penumpang.kursi) {
                "A1" -> {
                    a1.setImageResource(R.drawable.ic_rectangle_not_available)
                    isAvailableA1 = false
                }
                "A2" -> {
                    a2.setImageResource(R.drawable.ic_rectangle_not_available)
                    isAvailableA2 = false
                }
                "A3" -> {
                    a3.setImageResource(R.drawable.ic_rectangle_not_available)
                    isAvailableA3 = false
                }
                "A4" -> {
                    a4.setImageResource(R.drawable.ic_rectangle_not_available)
                    isAvailableA4 = false
                }
                "B1" -> {
                    b1.setImageResource(R.drawable.ic_rectangle_not_available)
                    isAvailableB1 = false
                }
                "B2" -> {
                    b2.setImageResource(R.drawable.ic_rectangle_not_available)
                    isAvailableB2 = false
                }
                "B3" -> {
                    b3.setImageResource(R.drawable.ic_rectangle_not_available)
                    isAvailableB3 = false
                }
                "B4" -> {
                    b4.setImageResource(R.drawable.ic_rectangle_not_available)
                    isAvailableB4 = false
                }
                "C1" -> {
                    c1.setImageResource(R.drawable.ic_rectangle_not_available)
                    isAvailableC1 = false
                }
                "C2" -> {
                    c2.setImageResource(R.drawable.ic_rectangle_not_available)
                    isAvailableC2 = false
                }
                "C3" -> {
                    c3.setImageResource(R.drawable.ic_rectangle_not_available)
                    isAvailableC3 = false
                }
                "C4" -> {
                    c4.setImageResource(R.drawable.ic_rectangle_not_available)
                    isAvailableC4 = false
                }
                "D1" -> {
                    d1.setImageResource(R.drawable.ic_rectangle_not_available)
                    isAvailableD1 = false
                }
                "D2" -> {
                    d2.setImageResource(R.drawable.ic_rectangle_not_available)
                    isAvailableD2 = false
                }
                "D3" -> {
                    d3.setImageResource(R.drawable.ic_rectangle_not_available)
                    isAvailableD3 = false
                }
                "D4" -> {
                    d4.setImageResource(R.drawable.ic_rectangle_not_available)
                    isAvailableD4 = false
                }
            }
        }

        if (isAvailableA1)
            a1.setOnClickListener {
                if (statusA1) {
                    a1.setImageResource(R.drawable.ic_rectangle_avail)
                    statusA1 = false
                    total -= 1
                    belitiket(total)

                    // delete data
                    dataList.remove(Checkout("A1", data.price))

                } else {
                    a1.setImageResource(R.drawable.ic_rectangle_selected)
                    statusA1 = true
                    total += 1
                    belitiket(total)

                    val data = Checkout("A1", data.price)
                    dataList.add(data)
                }
            }

        if (isAvailableA2)
            a2.setOnClickListener {
                if (statusA2) {
                    a2.setImageResource(R.drawable.ic_rectangle_avail)
                    statusA2 = false
                    total -= 1
                    belitiket(total)

                    // delete data
                    dataList.remove(Checkout("A2", data.price))

                } else {
                    a2.setImageResource(R.drawable.ic_rectangle_selected)
                    statusA2 = true
                    total += 1
                    belitiket(total)

                    val data = Checkout("A2", data.price)
                    dataList.add(data)
                }
            }

        if (isAvailableA3)
            a3.setOnClickListener {
                if (statusA3) {
                    a3.setImageResource(R.drawable.ic_rectangle_avail)
                    statusA3 = false
                    total -= 1
                    belitiket(total)

                    // delete data
                    dataList.remove(Checkout("A3", data.price))

                } else {
                    a3.setImageResource(R.drawable.ic_rectangle_selected)
                    statusA3 = true
                    total += 1
                    belitiket(total)

                    val data = Checkout("A3", data.price)
                    dataList.add(data)
                }
            }

        if (isAvailableA4)
            a4.setOnClickListener {
                if (statusA4) {
                    a4.setImageResource(R.drawable.ic_rectangle_avail)
                    statusA4 = false
                    total -= 1
                    belitiket(total)

                    // delete data
                    dataList.remove(Checkout("A4", data.price))

                } else {
                    a4.setImageResource(R.drawable.ic_rectangle_selected)
                    statusA4 = true
                    total += 1
                    belitiket(total)

                    val data = Checkout("A4", data.price)
                    dataList.add(data)
                }
            }

        if (isAvailableB1)
            b1.setOnClickListener {
                if (statusB1) {
                    b1.setImageResource(R.drawable.ic_rectangle_avail)
                    statusB1 = false
                    total -= 1
                    belitiket(total)

                    // delete data
                    dataList.remove(Checkout("B1", data.price))

                } else {
                    b1.setImageResource(R.drawable.ic_rectangle_selected)
                    statusB1 = true
                    total += 1
                    belitiket(total)

                    val data = Checkout("B1", data.price)
                    dataList.add(data)
                }
            }

        if (isAvailableB2)
            b2.setOnClickListener {
                if (statusB2) {
                    b2.setImageResource(R.drawable.ic_rectangle_avail)
                    statusB2 = false
                    total -= 1
                    belitiket(total)

                    // delete data
                    dataList.remove(Checkout("B2", data.price))

                } else {
                    b2.setImageResource(R.drawable.ic_rectangle_selected)
                    statusB2 = true
                    total += 1
                    belitiket(total)

                    val data = Checkout("B2", data.price)
                    dataList.add(data)
                }
            }

        if (isAvailableB3)
            b3.setOnClickListener {
                if (statusB3) {
                    b3.setImageResource(R.drawable.ic_rectangle_avail)
                    statusB3 = false
                    total -= 1
                    belitiket(total)

                    // delete data
                    dataList.remove(Checkout("B3", data.price))

                } else {
                    b3.setImageResource(R.drawable.ic_rectangle_selected)
                    statusB3 = true
                    total += 1
                    belitiket(total)

                    val data = Checkout("B3", data.price)
                    dataList.add(data)
                }
            }

        if (isAvailableB4)
            b4.setOnClickListener {
                if (statusB4) {
                    b4.setImageResource(R.drawable.ic_rectangle_avail)
                    statusB4 = false
                    total -= 1
                    belitiket(total)

                    // delete data
                    dataList.remove(Checkout("B4", data.price))

                } else {
                    b4.setImageResource(R.drawable.ic_rectangle_selected)
                    statusB4 = true
                    total += 1
                    belitiket(total)

                    val data = Checkout("B4", data.price)
                    dataList.add(data)
                }
            }

        if (isAvailableC1)
            c1.setOnClickListener {
                if (statusC1) {
                    c1.setImageResource(R.drawable.ic_rectangle_avail)
                    statusC1 = false
                    total -= 1
                    belitiket(total)

                    // delete data
                    dataList.remove(Checkout("C1", data.price))

                } else {
                    c1.setImageResource(R.drawable.ic_rectangle_selected)
                    statusC1 = true
                    total += 1
                    belitiket(total)

                    val data = Checkout("C1", data.price)
                    dataList.add(data)
                }
            }

        if (isAvailableC2)
            c2.setOnClickListener {
                if (statusC2) {
                    c2.setImageResource(R.drawable.ic_rectangle_avail)
                    statusC2 = false
                    total -= 1
                    belitiket(total)

                    // delete data
                    dataList.remove(Checkout("C2", data.price))

                } else {
                    c2.setImageResource(R.drawable.ic_rectangle_selected)
                    statusC2 = true
                    total += 1
                    belitiket(total)

                    val data = Checkout("C2", data.price)
                    dataList.add(data)
                }
            }

        if (isAvailableC3)
            c3.setOnClickListener {
                if (statusC3) {
                    c3.setImageResource(R.drawable.ic_rectangle_avail)
                    statusC3 = false
                    total -= 1
                    belitiket(total)

                    // delete data
                    dataList.remove(Checkout("C3", data.price))

                } else {
                    c3.setImageResource(R.drawable.ic_rectangle_selected)
                    statusC3 = true
                    total += 1
                    belitiket(total)

                    val data = Checkout("C3", data.price)
                    dataList.add(data)
                }
            }

        if (isAvailableC4)
            c4.setOnClickListener {
                if (statusC4) {
                    c4.setImageResource(R.drawable.ic_rectangle_avail)
                    statusC4 = false
                    total -= 1
                    belitiket(total)

                    // delete data
                    dataList.remove(Checkout("C4", data.price))

                } else {
                    c4.setImageResource(R.drawable.ic_rectangle_selected)
                    statusC4 = true
                    total += 1
                    belitiket(total)

                    val data = Checkout("C4", data.price)
                    dataList.add(data)
                }
            }

        if (isAvailableD1)
            d1.setOnClickListener {
                if (statusD1) {
                    d1.setImageResource(R.drawable.ic_rectangle_avail)
                    statusD1 = false
                    total -= 1
                    belitiket(total)

                    // delete data
                    dataList.remove(Checkout("D1", data.price))

                } else {
                    d1.setImageResource(R.drawable.ic_rectangle_selected)
                    statusD1 = true
                    total += 1
                    belitiket(total)

                    val data = Checkout("D1", data.price)
                    dataList.add(data)
                }
            }

        if (isAvailableD2)
            d2.setOnClickListener {
                if (statusD2) {
                    d2.setImageResource(R.drawable.ic_rectangle_avail)
                    statusD2 = false
                    total -= 1
                    belitiket(total)

                    // delete data
                    dataList.remove(Checkout("D2", data.price))

                } else {
                    d2.setImageResource(R.drawable.ic_rectangle_selected)
                    statusD2 = true
                    total += 1
                    belitiket(total)

                    val data = Checkout("D2", data.price)
                    dataList.add(data)
                }
            }

        if (isAvailableD3)
            d3.setOnClickListener {
                if (statusD3) {
                    d3.setImageResource(R.drawable.ic_rectangle_avail)
                    statusD3 = false
                    total -= 1
                    belitiket(total)

                    // delete data
                    dataList.remove(Checkout("D3", data.price))

                } else {
                    d3.setImageResource(R.drawable.ic_rectangle_selected)
                    statusD3 = true
                    total += 1
                    belitiket(total)

                    val data = Checkout("D3", data.price)
                    dataList.add(data)
                }
            }

        if (isAvailableD4)
            d4.setOnClickListener {
                if (statusD4) {
                    d4.setImageResource(R.drawable.ic_rectangle_avail)
                    statusD4 = false
                    total -= 1
                    belitiket(total)

                    // delete data
                    dataList.remove(Checkout("D4", data.price))

                } else {
                    d4.setImageResource(R.drawable.ic_rectangle_selected)
                    statusD4 = true
                    total += 1
                    belitiket(total)

                    val data = Checkout("D4", data.price)
                    dataList.add(data)
                }
            }

        val tanggal = intent.getStringExtra("tanggal")
        btn_home.setOnClickListener {
            val intent = Intent(
                this,
                CheckoutActivity::class.java
            ).putExtra("data", dataList).putExtra("trip", data).putExtra("tanggal", tanggal)
            startActivity(intent)
        }
    }

    private fun belitiket(total: Int) {
        if (total == 0) {
            btn_home.setText("Beli Tiket")
            btn_home.visibility = View.INVISIBLE
        } else {
            btn_home.setText("Beli Tiket (" + total + ")")
            btn_home.visibility = View.VISIBLE
        }
    }
}
