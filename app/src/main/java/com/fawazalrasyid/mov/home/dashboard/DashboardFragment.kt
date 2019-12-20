package com.fawazalrasyid.mov.home.dashboard


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.fawazalrasyid.mov.DetailMovieActivity
import com.fawazalrasyid.mov.DetailMovieComingActivity

import com.fawazalrasyid.mov.R
import com.fawazalrasyid.mov.home.model.Comingsoon
import com.fawazalrasyid.mov.home.model.Film
import com.fawazalrasyid.mov.utils.Preferences
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_dashboard.*
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

/**
 * A simple [Fragment] subclass.
 */
class DashboardFragment : Fragment() {

    private lateinit var preferences: Preferences
    lateinit var mDatabase: DatabaseReference
    lateinit var mDatabaseComing: DatabaseReference

    private var dataList = ArrayList<Film>()
    private var dataListComingsoon = ArrayList<Comingsoon>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        preferences = Preferences(activity!!.applicationContext)
        mDatabase = FirebaseDatabase.getInstance().getReference("Film")
        mDatabaseComing = FirebaseDatabase.getInstance().getReference("Comingsoon")

        tv_nama.setText(preferences.getValues("nama"))
        if (!preferences.getValues("saldo").equals("")){
            currecy(preferences.getValues("saldo")!!.toDouble(), tv_saldo)
        }

        Glide.with(this)
            .load(preferences.getValues("url"))
            .placeholder(R.drawable.user_pic)
            .apply(RequestOptions.circleCropTransform())
            .into(iv_profile)

        rv_nowplaying.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rv_comingsoon.layoutManager = LinearLayoutManager(context!!.applicationContext)
        getData()
        getDataComing()

    }

    private fun getData() {
        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                dataList.clear()
                for (getdataSnapshot in dataSnapshot.getChildren()) {

                    val film = getdataSnapshot.getValue(Film::class.java)
                    dataList.add(film!!)
                }

                rv_nowplaying.adapter = NowPlayingAdapter(dataList) {
                    val intent = Intent(context,
                        DetailMovieActivity::class.java).putExtra("data", it)
                    startActivity(intent)
                }

//                rv_comingsoon.adapter = ComingSoonAdapter(dataList) {
//                    val intent = Intent(context,
//                        DetailMovieActivity::class.java).putExtra("data", it)
//                    startActivity(intent)
//                }

            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, ""+error.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun getDataComing() {
        mDatabaseComing.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                dataListComingsoon.clear()
                for (getdataSnapshot in dataSnapshot.getChildren()) {

                    val comingsoon = getdataSnapshot.getValue(Comingsoon::class.java)
                    dataListComingsoon.add(comingsoon!!)
                }

                rv_comingsoon.adapter = ComingSoonAdapter(dataListComingsoon) {
                    val intent = Intent(context,
                        DetailMovieComingActivity::class.java).putExtra("data", it)
                    startActivity(intent)
                }

            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, ""+error.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun currecy(harga:Double, textView: TextView) {
        val localeID = Locale("in", "ID")
        val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
        textView.setText(formatRupiah.format(harga))

    }

}
