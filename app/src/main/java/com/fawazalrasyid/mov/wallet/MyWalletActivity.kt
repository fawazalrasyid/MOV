package com.fawazalrasyid.mov.wallet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.fawazalrasyid.mov.R
import com.fawazalrasyid.mov.wallet.adapter.WalletAdapter
import com.fawazalrasyid.mov.wallet.model.Wallet
import kotlinx.android.synthetic.main.activity_checkout.*
import kotlinx.android.synthetic.main.activity_my_wallet.*
import kotlinx.android.synthetic.main.activity_my_wallet.iv_back

class MyWalletActivity : AppCompatActivity() {

    private var dataList = ArrayList<Wallet>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_wallet)

        dataList.add(
            Wallet(
                "Avengers Returns",
                "Sabtu 12 Jan, 2020",
                700000.0,
                "0"
            )
        )
        dataList.add(
            Wallet(
                "Top Up",
                "Sabtu 12 Jan, 2020",
                1700000.0,
                "1"
            )
        )
        dataList.add(
            Wallet(
                "Avengers Returns",
                "Sabtu 12 Jan, 2020",
                700000.0,
                "0"
            )
        )

        iv_back.setOnClickListener {
            finish()
        }

        rv_transaksi.layoutManager = LinearLayoutManager(this)
        rv_transaksi.adapter = WalletAdapter(dataList){

        }

        btn_topup.setOnClickListener {
            startActivity(Intent(this, MyWalletTopupActivity::class.java))
        }

    }
}
