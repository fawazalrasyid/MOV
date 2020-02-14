package com.fawazalrasyid.mov.home.tiket

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.fawazalrasyid.mov.R
import com.fawazalrasyid.mov.checkout.model.Checkout
import com.fawazalrasyid.mov.home.model.Film
import kotlinx.android.synthetic.main.activity_tiket.*

class TiketActivity : AppCompatActivity() {

    lateinit var myDialog : Dialog
    lateinit var btn : Button

    private var dataList = ArrayList<Checkout>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tiket)

        val data = intent.getParcelableExtra<Film>("data")

        tv_title.text = data.judul
        tv_genre.text = data.genre
        tv_rate.text = data.rating

        Glide.with(this)
            .load(data.poster)
            .into(iv_poster_image)

        rc_checkout.layoutManager = LinearLayoutManager(this)
        dataList.add(Checkout("C2",""))
        dataList.add(Checkout("C3",""))

        rc_checkout.adapter = TiketAdapter(dataList) {
        }


        iv_qr.setOnClickListener{
            showDialog()
        }

        imageView3.setOnClickListener {
            finish()
        }

    }

    fun showDialog(){
        myDialog = Dialog(this)
        //myDialog.requestWindowFeature(window.FEATURE_NO_TITLE)
        myDialog.setContentView(R.layout.item_qr_popup)
        myDialog.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        btn = myDialog.findViewById(R.id.btn_tutup)
        btn.setOnClickListener{
            myDialog.cancel()
        }
        myDialog.setCanceledOnTouchOutside(false)

        myDialog.show()
    }

}
