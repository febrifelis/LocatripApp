package com.fbrproject.locatripapp.checkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fbrproject.locatrip.R
import com.fbrproject.locatripapp.home.HomeActivity
import com.fbrproject.locatripapp.home.tiket.TiketActivity
import com.fbrproject.locatripapp.model.Penumpang
import com.fbrproject.locatripapp.model.Trip
import kotlinx.android.synthetic.main.activity_check_out_success.*

class CheckoutSuccessActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_out_success)

        btn_home.setOnClickListener {
            finishAffinity()
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        val trip = intent.getParcelableExtra<Trip>("trip")
        val daftarPenumpang = intent.getParcelableArrayListExtra<Penumpang>("daftarPenumpang")
        val tanggal = intent.getStringExtra("tanggal")
        btn_tiket.setOnClickListener {
            val intent = Intent(this, TiketActivity::class.java)
                .putExtra("data", trip)
                .putParcelableArrayListExtra("dataList", daftarPenumpang)
                .putExtra("tanggal", tanggal)
            startActivity(intent)
            finish()
        }

    }
}