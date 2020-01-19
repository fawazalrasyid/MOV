package com.fawazalrasyid.mov.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import com.bumptech.glide.Glide
import com.fawazalrasyid.mov.*
import com.fawazalrasyid.mov.home.HomeActivity
import com.fawazalrasyid.mov.sign.signin.SignInActivity
import com.fawazalrasyid.mov.utils.Preferences
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreenActivity : AppCompatActivity() {

    lateinit var preferences : Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        setContentView(R.layout.activity_splash_screen)

        preferences = Preferences(this)

        if (preferences.getValues("status").equals("1")) {
            Handler().postDelayed({
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }, 4000)
        }
        else{
            Handler().postDelayed({
                startActivity(Intent(this, OnboardingOneActivity::class.java))
                finish()
            }, 4000)
        }

        Glide.with(this)
            .load(R.drawable.loader)
            .into(iv_loader)

    }
}
