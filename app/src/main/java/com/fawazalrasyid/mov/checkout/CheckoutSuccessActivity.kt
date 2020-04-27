package com.fawazalrasyid.mov.checkout

import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fawazalrasyid.mov.R
import com.fawazalrasyid.mov.home.HomeActivity
import com.fawazalrasyid.mov.home.model.Film
import com.fawazalrasyid.mov.home.tiket.TiketActivity
import kotlinx.android.synthetic.main.activity_checkout_success.*


class CheckoutSuccessActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout_success)

        val data = intent.getParcelableExtra<Film>("data")

        btn_tiket.setOnClickListener {
            finishAffinity()

            val intent = Intent(this@CheckoutSuccessActivity,
                TiketActivity::class.java).putExtra("data", data)
            startActivity(intent)


        }

        btn_home.setOnClickListener {
            finishAffinity()

            val intent = Intent(this@CheckoutSuccessActivity,
                HomeActivity::class.java)
            startActivity(intent)
        }

    }
}
