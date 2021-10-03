package com.fbrproject.locatripapp.wallet

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fbrproject.locatrip.R
import com.fbrproject.locatrip.utils.Preferences
import com.fbrproject.locatripapp.wallet.model.Wallet
import kotlinx.android.synthetic.main.activity_my_wallet.btn_top
import kotlinx.android.synthetic.main.activity_my_wallet.tv_saldo
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class MyWalletActivity : AppCompatActivity() {

    private var dataList = ArrayList<Wallet>()

    private lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topup)

        preferences = Preferences(this)

        val localeID = Locale("in", "ID")
        val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
        tv_saldo.setText(formatRupiah.format(preferences.getLongValues("saldo")))

        btn_top.setOnClickListener {
            var intent = Intent(this, TopUpWalletActivity::class.java)
            startActivity(intent)
        }
    }
}
