package com.example.healcy.main

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.healcy.R
import com.example.healcy.api.ApiConfig
import com.example.healcy.databinding.ActivityMainBinding
import com.example.healcy.education.EducationActivity
import com.example.healcy.emergency.EmergencyActivity
import com.example.healcy.factory.ViewModelFactory
import com.example.healcy.login.LoginActivity
import com.example.healcy.maps.MapsActivity
import com.example.healcy.monitoring.MonitoringActivity
import com.example.healcy.preferences.SettingPreference
import com.example.healcy.preferences.UserPreference
import com.example.healcy.preferences.dataStore
import com.example.healcy.response.UserResponse
import com.example.healcy.theme.ThemeActivity
import com.example.healcy.theme.ThemeViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
//    private lateinit var viewModel: MainViewModel
    private lateinit var pref: UserPreference

    private val REQUEST_CODE_EDUCATION = 1
    private val REQUEST_CODE_MONITORING = 2
    private val REQUEST_CODE_HOSPITAL = 3
    private val REQUEST_CODE_EMERGENCY = 4

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pref = UserPreference(this)

        setMenu()
//        getToken()
        setValid()
//        setViewModel()
        setTheme()
    }

    private fun setTheme() {
        val pref = SettingPreference.getInstance(dataStore)
        val darkModeViewModel = ViewModelProvider(this, ViewModelFactory(pref))[ThemeViewModel::class.java]
        darkModeViewModel.getThemeSettings().observe(this){isDarkModeActive ->
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }

//    private fun getToken() {
//        val token = pref.getUser().token
//        val client = ApiConfig().getApiService().getUser(token)
//        client.enqueue(object : Callback<UserResponse> {
//            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
//            }
//            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
//            }
//        })
//    }

    private fun setMenu() {
        binding.cvEducation.setOnClickListener {
            val education = Intent(this, EducationActivity::class.java)
//            startActivity(education)
            startActivityForResult(education, REQUEST_CODE_EDUCATION)
        }

        binding.cvMonitoring.setOnClickListener {
            val monitoring = Intent(this, MonitoringActivity::class.java)
//            startActivity(monitoring)
            startActivityForResult(monitoring, REQUEST_CODE_MONITORING)
        }

        binding.cvHospital.setOnClickListener {
            val hospital = Intent(this, MapsActivity::class.java)
//            startActivity(hospital)
            startActivityForResult(hospital, REQUEST_CODE_HOSPITAL)
        }

        binding.cvEmergency.setOnClickListener {
            val emergency = Intent(this, EmergencyActivity::class.java)
//            startActivity(emergency)
            startActivityForResult(emergency, REQUEST_CODE_EMERGENCY)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQUEST_CODE_EDUCATION -> {
                if (resultCode == Activity.RESULT_OK) {
                    // Tindakan yang akan dilakukan saat menerima hasil dari EducationActivity
                    Toast.makeText(this, "Education", Toast.LENGTH_SHORT).show()
                }
            }
            REQUEST_CODE_MONITORING -> {
                if (resultCode == Activity.RESULT_OK) {
                    // Tindakan yang akan dilakukan saat menerima hasil dari MonitoringActivity
                    Toast.makeText(this, "Monitoring", Toast.LENGTH_SHORT).show()
                }
            }
            REQUEST_CODE_HOSPITAL -> {
                if (resultCode == Activity.RESULT_OK) {
                    // Tindakan yang akan dilakukan saat menerima hasil dari MapsActivity
                    Toast.makeText(this, "Hospital", Toast.LENGTH_SHORT).show()
                }
            }
            REQUEST_CODE_EMERGENCY -> {
                if (resultCode == Activity.RESULT_OK) {
                    // Tindakan yang akan dilakukan saat menerima hasil dari EmergencyActivity
                    Toast.makeText(this, "Emergency Call", Toast.LENGTH_SHORT).show()
                }
            }
            // Tambahkan penanganan hasil dari aktivitas tujuan lainnya sesuai kebutuhan
        }
    }


//    private fun setViewModel() {
//        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
//        viewModel.getUser(pref.getUser().token)
//        viewModel.isLoading.observe(this){
//            showLoading(it)
//        }
//    }

    private fun setValid() {
        if(!pref.getUser().isLogin){
            val login = pref.getUser().isLogin
            Log.d(TAG, login.toString())

            val i = Intent(this, LoginActivity::class.java)
            startActivity(i)
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.setting_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_language -> {
                Intent(Settings.ACTION_LOCALE_SETTINGS).also {
                    startActivity(it)
                }
                return true
            }
            R.id.menu_theme -> {
                val i = Intent(this, ThemeActivity::class.java)
                startActivity(i)
                return true
            }
            R.id.menu_logout -> {
                pref.logout()
                val i = Intent(this, LoginActivity::class.java)
                Toast.makeText(this, getString(R.string.logout), Toast.LENGTH_SHORT).show()
                i.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(i)
                finish()
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading){
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    companion object{
        const val TAG = "extra_tag"
    }
}