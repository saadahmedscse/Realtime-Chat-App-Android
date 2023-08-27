package com.saadahmedsoft.sparkconvo.view.splash

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.saadahmedsoft.shortintent.Anim
import com.saadahmedsoft.shortintent.ShortIntent
import com.saadahmedsoft.sparkconvo.R
import com.saadahmedsoft.sparkconvo.helper.delay
import com.saadahmedsoft.sparkconvo.helper.getBearerToken
import com.saadahmedsoft.sparkconvo.view.auth.AuthActivity
import com.saadahmedsoft.sparkconvo.view.dashboard.DashboardActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        delay(1500) {
            ShortIntent.getInstance(this)
                .addDestination(if (getBearerToken() == null) AuthActivity::class.java else DashboardActivity::class.java)
                .addTransition(Anim.FADE)
                .finish(this)
        }
    }
}