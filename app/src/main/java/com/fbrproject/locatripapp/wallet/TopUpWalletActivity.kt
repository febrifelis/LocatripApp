package com.fbrproject.locatripapp.wallet

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fbrproject.locatrip.R
import com.fbrproject.locatrip.utils.Preferences
import com.fbrproject.locatripapp.home.HomeActivity
import com.fbrproject.locatripapp.wallet.model.Wallet
import kotlinx.android.synthetic.main.activity_my_wallet.*
import kotlinx.android.synthetic.main.activity_my_wallet_top_up.*
import kotlinx.android.synthetic.main.activity_my_wallet_top_up.tv_saldo
import kotlinx.android.synthetic.main.activity_top_up_wallet.*
import kotlinx.android.synthetic.main.fragment_setting.*
import java.text.NumberFormat
import java.util.*


class TopUpWalletActivity : AppCompatActivity() {

    private var dataList = ArrayList<Wallet>()

    private lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_up_wallet)

        preferences = Preferences(this)

        val localeID = Locale("in", "ID")
        val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
        tv_saldo.setText(formatRupiah.format(preferences.getLongValues("saldo")))


        btn_confirm.setOnClickListener {
            val url = "https://wa.me/8561805684"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }

        btn_home.setOnClickListener {
            var intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

    }

}

