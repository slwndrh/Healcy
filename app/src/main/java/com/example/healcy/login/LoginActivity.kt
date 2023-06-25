package com.example.healcy.login

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.healcy.R
import com.example.healcy.data.User
import com.example.healcy.databinding.ActivityLoginBinding
import com.example.healcy.main.MainActivity
import com.example.healcy.preferences.UserPreference
import com.example.healcy.signup.SignupActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel
    private lateinit var pref: SharedPreferences
    lateinit var userPref: UserPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        setAction()
        setPref()
        setViewModel()
        playAnimation()
    }

    private fun setLogin() {
        val email = binding.edLoginEmail.text.toString().trim()
        val password = binding.edLoginPassword.text.toString().trim()
        val button = binding.btnLogin

        when {
            email.isEmpty() -> {
                binding.etlEmail.error = getString(R.string.message_email)
            }
            !binding.etlPwd.error.isNullOrEmpty() -> {
                button.isEnabled = false
            }
            else -> {
                if (password.length >= 8){
                    viewModel.userLogin(email, password)
                }
                viewModel.isLogin.observe(this) {
                    binding.progressBar.visibility = View.VISIBLE
                    if (it != null) {
                        AlertDialog.Builder(this).apply {
                            setTitle(getString(R.string.title_add))
                            setMessage(getString(R.string.message_add) + " ${it.name}!")
                            setPositiveButton(getString(R.string.btn_add)) { _, _ ->
                                val i = Intent(context, MainActivity::class.java)
                                i.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                                startActivity(i)
                                finish()
                            }
                            create()
                            show()
                        }
                        saveUser(User(it.id, it.name, it.email, it.token, true))
                    }
                }
            }
        }
    }

    private fun saveUser(user: User) {
        userPref.setUser(user)
    }


    private fun setAction() {
        binding.btnLogin.setOnClickListener{
            setLogin()

            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, 0)
        }

        binding.btnSignup.setOnClickListener {
            val i = Intent(this, SignupActivity::class.java)
            startActivity(i, ActivityOptionsCompat.makeSceneTransitionAnimation(this@LoginActivity as Activity).toBundle())
        }
    }

    private fun setPref() {
        pref = getSharedPreferences(EXTRA_PREF, Context.MODE_PRIVATE)
        userPref = UserPreference(this)
    }

    private fun setViewModel() {
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        viewModel.isLoading.observe(this){
            showLoading(it)
        }
    }

    private fun playAnimation() {
        ObjectAnimator.ofFloat(binding.ivLogin, View.TRANSLATION_X, -30f, 30f).apply {
            duration = 5000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()

        val titleLogin = ObjectAnimator.ofFloat(binding.titleLogin, View.ALPHA, 1f).setDuration(500)
        val messageLogin = ObjectAnimator.ofFloat(binding.messageLogin, View.ALPHA, 1f).setDuration(500)
        val tvEmail = ObjectAnimator.ofFloat(binding.tvLoginEmail, View.ALPHA, 1f).setDuration(500)
        val etlEmail = ObjectAnimator.ofFloat(binding.etlEmail, View.ALPHA, 1f).setDuration(500)
        val tvPassword = ObjectAnimator.ofFloat(binding.tvLoginPassword, View.ALPHA, 1f).setDuration(500)
        val etlPassword = ObjectAnimator.ofFloat(binding.etlPwd, View.ALPHA, 1f).setDuration(500)
        val login = ObjectAnimator.ofFloat(binding.btnLogin, View.ALPHA, 1f).setDuration(500)
        val signup = ObjectAnimator.ofFloat(binding.btnSignup, View.ALPHA, 1f).setDuration(500)

        val together = AnimatorSet().apply {
            playTogether(tvEmail, etlEmail)
        }

        val togetherr = AnimatorSet().apply {
            playTogether(tvPassword, etlPassword)
        }

        val togetherrr = AnimatorSet().apply {
            playTogether(login, signup)
        }

        AnimatorSet().apply {
            playSequentially(titleLogin, messageLogin, together, togetherr, togetherrr)
            startDelay = 400
        }.start()
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading){
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    companion object {
        const val EXTRA_PREF = "extra_pref"
    }
}