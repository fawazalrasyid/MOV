package com.fawazalrasyid.mov.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fawazalrasyid.mov.R
import kotlinx.android.synthetic.main.activity_onboarding.*

class OnboardingTwoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.slide_in_noanim, R.anim.slide_out_noanim)
        setContentView(R.layout.activity_onboarding_two)

        btn_tiket.setOnClickListener {
            val intent = Intent(this@OnboardingTwoActivity,
                OnboardingThereeActivity::class.java)
            startActivity(intent)
        }

    }
}