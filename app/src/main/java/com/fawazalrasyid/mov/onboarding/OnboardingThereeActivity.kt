package com.fawazalrasyid.mov.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fawazalrasyid.mov.R
import com.fawazalrasyid.mov.sign.signin.SignInActivity
import kotlinx.android.synthetic.main.activity_onboarding.*

class OnboardingThereeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.slide_in_noanim, R.anim.slide_out_noanim)
        setContentView(R.layout.activity_onboarding_three)

        btn_tiket.setOnClickListener {
            val intent = Intent(this@OnboardingThereeActivity,
                SignInActivity::class.java)
            startActivity(intent)
            finishAffinity()
        }

    }
}