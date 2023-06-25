package com.example.healcy.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.healcy.api.ApiConfig
import com.example.healcy.response.LoginResponse
import com.example.healcy.response.LoginResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel: ViewModel() {
    private val _isLogin = MutableLiveData<LoginResult>()
    val isLogin: LiveData<LoginResult> = _isLogin

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object {
        const val TAG = "extra_tag"
    }

    fun userLogin(email: String, password: String) {
        _isLoading.value = true
        ApiConfig().getApiService().loginUser(email, password).enqueue(object :
            Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _isLogin.value = response.body()?.loginResult

                    Log.d(TAG, response.body()?.message.toString())
                    Log.d(TAG, response.body()?.loginResult?.token.toString())
                    Log.d(TAG, response.body()?.loginResult?.name ?: "name")
                }
            }
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                _isLoading.value = false
                Log.d(TAG, t.message.toString())
            }
        })
    }
}