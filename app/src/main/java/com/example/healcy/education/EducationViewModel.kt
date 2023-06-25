package com.example.healcy.education

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.healcy.api.ApiConfig
import com.example.healcy.data.Education
import com.example.healcy.response.DataArticle
import com.example.healcy.response.EducationResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EducationViewModel: ViewModel() {
//    private val _isEducation = MutableLiveData<List<Education>>()
//    val isEducation: LiveData<List<Education>> = _isEducation

    private val _isEducation = MutableLiveData<List<Education>?>()
    val isEducation: MutableLiveData<List<Education>?> get() = _isEducation

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: MutableLiveData<Boolean> = _isLoading

    companion object {
        const val TAG = "extra_tag"
    }

    fun getArticle() {
        _isLoading.value = true
        ApiConfig().getApiService().getArticle("Bearer ").enqueue(object : Callback<EducationResponse> {
            override fun onResponse(call: Call<EducationResponse>, response: Response<EducationResponse>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val articles = response.body()?.listData
                    _isEducation.value = articles
                }

            }

            override fun onFailure(call: Call<EducationResponse>, t: Throwable) {
                _isLoading.value = false
                Log.d(TAG, t.message.toString())
            }

        })
    }
}