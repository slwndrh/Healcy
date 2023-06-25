package com.example.healcy.emergency

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.healcy.R

class EmergencyActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var tvCall1: TextView
    private lateinit var tvCall2: TextView
    private lateinit var tvCall3: TextView
    private lateinit var tvCall4: TextView
    private lateinit var tvCall5: TextView

    private lateinit var tvNumber1: TextView
    private lateinit var tvNumber2: TextView
    private lateinit var tvNumber3: TextView
    private lateinit var tvNumber4: TextView
    private lateinit var tvNumber5: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emergency)

        supportActionBar?.title = getString(R.string.emergency)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setHospital1()
        setHospital2()
        setHospital3()
        setHospital4()
        setHospital5()
    }

    private fun setHospital5() {
        tvCall5 = findViewById(R.id.tv_call5)
        tvNumber5 = findViewById(R.id.tv_number5)

        tvCall5.text = getString(R.string.hospital5)
        tvNumber5.text = getString(R.string.number5)

        val btnCall5 : Button = findViewById(R.id.btn_call5)
        btnCall5.setOnClickListener(this)
    }

    private fun setHospital4() {
        tvCall4 = findViewById(R.id.tv_call4)
        tvNumber4 = findViewById(R.id.tv_number4)

        tvCall4.text = getString(R.string.hospital4)
        tvNumber4.text = getString(R.string.number4)

        val btnCall4 : Button = findViewById(R.id.btn_call4)
        btnCall4.setOnClickListener(this)
    }

    private fun setHospital3() {
        tvCall3 = findViewById(R.id.tv_call3)
        tvNumber3 = findViewById(R.id.tv_number3)

        tvCall3.text = getString(R.string.hospital3)
        tvNumber3.text = getString(R.string.number3)

        val btnCall3 : Button = findViewById(R.id.btn_call3)
        btnCall3.setOnClickListener(this)
    }

    private fun setHospital2() {
        tvCall2 = findViewById(R.id.tv_call2)
        tvNumber2 = findViewById(R.id.tv_number2)

        tvCall2.text = getString(R.string.hospital2)
        tvNumber2.text = getString(R.string.number2)

        val btnCall2 : Button = findViewById(R.id.btn_call2)
        btnCall2.setOnClickListener(this)
    }

    private fun setHospital1() {
        tvCall1 = findViewById(R.id.tv_call1)
        tvNumber1 = findViewById(R.id.tv_number1)

        tvCall1.text = getString(R.string.hospital1)
        tvNumber1.text = getString(R.string.number1)

        val btnCall1 : Button = findViewById(R.id.btn_call1)
        btnCall1.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_call1 -> {
                val phoneNumber = "0341362459"
                val dialPhoneIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
                startActivity(dialPhoneIntent)
            }
            R.id.btn_call2 -> {
                val phoneNumber = "0341362101"
                val dialPhoneIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
                startActivity(dialPhoneIntent)
            }
            R.id.btn_call3 -> {
                val phoneNumber = "0341470805"
                val dialPhoneIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
                startActivity(dialPhoneIntent)
            }
            R.id.btn_call4 -> {
                val phoneNumber = "0341551311"
                val dialPhoneIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
                startActivity(dialPhoneIntent)
            }
            R.id.btn_call5 -> {
                val phoneNumber = "0341551356"
                val dialPhoneIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
                startActivity(dialPhoneIntent)
            }
        }
    }
}