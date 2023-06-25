package com.example.healcy.signup

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.healcy.api.ApiConfig
import com.example.healcy.response.SignupResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupViewModel: ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object {
        const val TAG = "extra_tag"
    }

    fun userSignup(name: String, email: String, password: String){
        _isLoading.value = true
        ApiConfig().getApiService().signupUser(name, email, password).enqueue(object :
            Callback<SignupResponse> {
            override fun onResponse(call: Call<SignupResponse>, response: Response<SignupResponse>) {
                _isLoading.value = false
                if (response.isSuccessful  && response.body()?.message == "User created") {
                    Log.d(TAG, response.body()?.message.toString())
                }
            }

            override fun onFailure(call: Call<SignupResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, t.message.toString())
            }
        })
    }
}