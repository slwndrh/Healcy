package com.example.healcy.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.healcy.api.ApiConfig
import com.example.healcy.data.User
import com.example.healcy.response.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel() {
//    private val _isLogin = MutableLiveData<ArrayList<User>>()
//    val isLogin: LiveData<ArrayList<User>> = _isLogin
//
//    private val _isLoading = MutableLiveData<Boolean>()
//    val isLoading: LiveData<Boolean> = _isLoading
//
//    companion object {
//        const val TAG = "extra_tag"
//    }
//
//    fun getUser(auth: String) {
//        _isLoading.value = true
//        ApiConfig().getApiService().getUser("Bearer $auth").enqueue(object : Callback<UserResponse> {
//            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
//                _isLoading.value = false
//                if (response.isSuccessful) {
//                    _isLogin.postValue(response.body()?.user)
//                    Log.d(TAG, response.body()?.user.toString())
//                }
//
//            }
//
//            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
//                _isLoading.value = false
//                Log.d(TAG, t.message.toString())
//            }
//
//        })
//    }
}