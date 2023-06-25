package com.example.healcy.education

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.healcy.R
import com.example.healcy.adapter.EducationAdapter
import com.example.healcy.api.ApiConfig
import com.example.healcy.data.Education
import com.example.healcy.databinding.ActivityDetailBinding
import com.example.healcy.databinding.ActivityEducationBinding
import com.example.healcy.detail.DetailActivity
import com.example.healcy.login.LoginActivity
import com.example.healcy.main.MainActivity
import com.example.healcy.main.MainViewModel
import com.example.healcy.preferences.UserPreference
import com.example.healcy.response.EducationResponse
import com.example.healcy.response.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EducationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEducationBinding
    private lateinit var viewModel: EducationViewModel
    private lateinit var rv: RecyclerView
    private lateinit var adapter: EducationAdapter
    private lateinit var pref: UserPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEducationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = getString(R.string.education)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel = ViewModelProvider(this).get(EducationViewModel::class.java)
        adapter = EducationAdapter(emptyList()) { article ->
            // Handle item click here
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("article_id", article.id)
            startActivity(intent)
        }

        binding.rvEducation.layoutManager = LinearLayoutManager(this)
        binding.rvEducation.adapter = adapter

        viewModel.isEducation.observe(this, { articles ->
            articles?.let {
                adapter.setData(it)
            }
        })

        viewModel.getArticle()

//        pref = UserPreference(this)
//
//        rv = findViewById(R.id.rv_education)
//        viewModel = ViewModelProvider(this).get(EducationViewModel::class.java)
//
//        showRv(listOf())
//        setViewModel()
//        setValid()
//        getToken()
    }

//    private fun getToken() {
//        val token = pref.getArticle().token
//        val client = ApiConfig().getApiService().getArticle(token, linkImage = String(), title = String(), author = String(), content = String())
//        client.enqueue(object : Callback<EducationResponse> {
//            override fun onResponse(call: Call<EducationResponse>, response: Response<EducationResponse>) {
//            }
//            override fun onFailure(call: Call<EducationResponse>, t: Throwable) {
//            }
//        })
//    }

//    private fun getToken() {
//        val token = pref.getArticle().toString()
//        val apiService = ApiConfig().getApiService()
//        val call = apiService.getArticle(token)
//            call.enqueue(object : Callback<EducationResponse> {
//                override fun onResponse(call: Call<EducationResponse>, response: Response<EducationResponse>) {
//                    if (response.isSuccessful) {
//                        val educationResponse = response.body()
//                        // Lakukan sesuatu dengan objek EducationResponse
//                    } else {
//                        val errorBody = response.errorBody()?.string()
//                        // Lakukan sesuatu dengan pesan kesalahan
//                    }
//                }
//
//                override fun onFailure(call: Call<EducationResponse>, t: Throwable) {
//                    // Lakukan sesuatu dengan exception atau pesan kesalahan
//                }
//        })
//    }

//    private fun setValid() {
//        if(!pref.getUser().isLogin){
//            val login = pref.getArticle()
//            Log.d(MainActivity.TAG, login.toString())
//        }
//    }
//
//    private fun showRv() {
//        binding.rvEducation.apply {
//            layoutManager = LinearLayoutManager(this@EducationActivity)
//            adapter = EducationAdapter(listOf())
//            setHasFixedSize(true)
//        }
//    }
//
//    private fun showRv(listStory: List<Education>) {
//        val layoutManager = LinearLayoutManager(this)
//        binding.rvEducation.layoutManager = layoutManager
//
//        val storyAdapter = EducationAdapter(listStory)
//        binding.rvEducation.adapter = storyAdapter
//
//        storyAdapter.setOnItemClickCallback(object : EducationAdapter.OnItemClickCallback{
//            override fun onItemClicked(data: Education) {
//                Intent(this@EducationActivity, DetailActivity::class.java).also { detail ->
//                    detail.putExtra(DetailActivity.EXTRA_DETAIL, data)
//                    startActivity(detail)
//                }
//            }
//        })
//    }
//
//    private fun setViewModel() {
//        viewModel = ViewModelProvider(this)[EducationViewModel::class.java]
//        viewModel.isLoading.observe(this){
//            showLoading(it)
//        }
//        viewModel.isEducation.observe(this){ articles ->
//            articles?.let {
//                adapter.submitList(it)
//            }
//        }
//    }
//
//    private fun showLoading(isLoading: Boolean) {
//        if (isLoading){
//            binding.progressBar.visibility = View.VISIBLE
//        } else {
//            binding.progressBar.visibility = View.GONE
//        }
//    }
}