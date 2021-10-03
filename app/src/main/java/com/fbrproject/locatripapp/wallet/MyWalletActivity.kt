package com.fbrproject.locatripapp.wallet

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.fbrproject.locatrip.R
import com.fbrproject.locatripapp.wallet.adapter.WalletAdapter
import com.fbrproject.locatripapp.wallet.model.Wallet
import kotlinx.android.synthetic.main.activity_my_wallet.*

class MyWalletActivity : AppCompatActivity() {

    private var dataList = ArrayList<Wallet>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_wallet)

        loadDummyData()

    }

    private fun initListener() {
        rv_transaksi.layoutManager = LinearLayoutManager(this)
        rv_transaksi.adapter = WalletAdapter(dataList){

        }

        btn_top_up.setOnClickListener {
            startActivity(Intent(this, MyWalletTopUpActivity::class.java))
        }
    }

    private fun loadDummyData() {
        dataList.add(
            Wallet(
                "Jakarta - Bandung",
                "Selasa, 12 Jan 2021",
                700000.0,
                "0"
            )
        )
        dataList.add(
            Wallet(
                "Top Up",
                "Selasa, 12 Jan 2021",
                1700000.0,
                "1"
            )
        )
        dataList.add(
            Wallet(
                "Jakarta - Bandung",
                "Selasa, 12 Jan 2021",
                700000.0,
                "0"
            )
        )

        initListener()
    }
}
