package com.example.healcy.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.healcy.R
import com.example.healcy.login.LoginActivity
import com.example.healcy.main.MainActivity
import com.example.healcy.preferences.UserPreference

class SplashActivity : AppCompatActivity() {
    private lateinit var userPref: UserPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        userPref = UserPreference(this)
        val isLogin = userPref.getUser().isLogin

        Handler(Looper.getMainLooper()).postDelayed({
            when{
                isLogin ->{
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else ->{
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
            }
        }, 500)
    }
}