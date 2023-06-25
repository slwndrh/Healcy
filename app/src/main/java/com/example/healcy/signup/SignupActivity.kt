package com.example.healcy.signup

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.healcy.R
import com.example.healcy.databinding.ActivitySignupBinding
import com.example.healcy.login.LoginActivity

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private lateinit var viewModel: SignupViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setAction()
        setViewModel()
        playAnimation()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun setSignup(){
        val name = binding.edSignupName.text.toString().trim()
        val email = binding.edSignupEmail.text.toString().trim()
        val password = binding.edSignupPassword.text.toString().trim()
        val button = binding.btnSignup

        when {
            name.isEmpty() -> {
                binding.etlSignupName.error = getString(R.string.message_name)
            }
            email.isEmpty() -> {
                binding.etlSignupEmail.error = getString(R.string.message_email)
            }
            !binding.etlSignupPassword.error.isNullOrEmpty() -> {
                button.isEnabled = false
            }
            else -> {
                if (password.length >= 8){
                    viewModel.userSignup(name, email, password)

                    val i = Intent(this, LoginActivity::class.java)
                    startActivity(i)
                    finish()
                }
            }
        }
    }

    private fun setAction() {
        binding.btnSignup.setOnClickListener {
            setSignup()
        }
    }

    private fun setViewModel() {
        viewModel = ViewModelProvider(this)[SignupViewModel::class.java]
        viewModel.isLoading.observe(this) {
            showLoading(it)
        }
    }

    private fun playAnimation() {
        val title = ObjectAnimator.ofFloat(binding.titleSignup, View.ALPHA, 1f).setDuration(500)
        val tvName = ObjectAnimator.ofFloat(binding.tvSignupName, View.ALPHA, 1f).setDuration(500)
        val etlName = ObjectAnimator.ofFloat(binding.etlSignupName, View.ALPHA, 1f).setDuration(500)
        val tvEmail = ObjectAnimator.ofFloat(binding.tvSignupEmail, View.ALPHA, 1f).setDuration(500)
        val etlEmail = ObjectAnimator.ofFloat(binding.etlSignupEmail, View.ALPHA, 1f).setDuration(500)
        val tvPassword = ObjectAnimator.ofFloat(binding.tvSignupPassword, View.ALPHA, 1f).setDuration(500)
        val etlPassword = ObjectAnimator.ofFloat(binding.etlSignupPassword, View.ALPHA, 1f).setDuration(500)
        val signup = ObjectAnimator.ofFloat(binding.btnSignup, View.ALPHA, 1f).setDuration(500)

        AnimatorSet().apply {
            playSequentially(title, tvName, etlName, tvEmail, etlEmail, tvPassword, etlPassword, signup)
            startDelay = 300
        }.start()
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading){
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}