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
    private val _isEducation = MutableLiveData<EducationResponse>()
    val isEducation: LiveData<EducationResponse> = _isEducation

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val eduResponse = EducationResponse(error = false, message = "", id = "")

    fun getArticle(id:String, author:String, linkImage: String, title: String, content: String){
        _isLoading.value = true
        val client =ApiConfig().getApiService().getArticle(id, author, linkImage, title, content)
        client.enqueue(object: Callback<EducationResponse>{
            override fun onResponse(call: Call<EducationResponse>, response: Response<EducationResponse>) {
                _isLoading.value = false
                val responseBody = response.body()
                Log.d(TAG, "onResponse: ${responseBody}")
                if (response.isSuccessful){
                    _isEducation.value = eduResponse
                    Log.d(TAG, responseBody?.message.toString())
                    Log.d(TAG,response.body()?.id.toString())
                    Log.d(TAG,response.body()?.id ?: "token")
                }
                if(!response.isSuccessful){
                }

            }

            override fun onFailure(call: Call<EducationResponse>, t: Throwable) {
                _isLoading.value = false
                Log.d(TAG,t.message.toString())

            }

        })
    }
    companion object{
        const val TAG = "extra_tag"
    }
}

//class EducationViewModel: ViewModel() {
////    private val _isEducation = MutableLiveData<List<Education>>()
////    val isEducation: LiveData<List<Education>> = _isEducation
//
//    private val _isEducation = MutableLiveData<List<DataArticle>?>()
//    val isEducation: MutableLiveData<List<DataArticle>?> get() = _isEducation
//
//    private val _isLoading = MutableLiveData<Boolean>()
//    val isLoading: MutableLiveData<Boolean> = _isLoading
//
//    companion object {
//        const val TAG = "extra_tag"
//    }
//
//    fun getArticle() {
//        _isLoading.value = true
//        ApiConfig().getApiService().getArticle().enqueue(object : Callback<EducationResponse> {
//            override fun onResponse(call: Call<EducationResponse>, response: Response<EducationResponse>) {
//                _isLoading.value = false
//                if (response.isSuccessful) {
//                    val articles = response.body()?.listData
//                    _isEducation.value = articles
//                }
//
//            }
//
//            override fun onFailure(call: Call<EducationResponse>, t: Throwable) {
//                _isLoading.value = false
//                Log.d(TAG, t.message.toString())
//            }
//
//        })
//    }
//}