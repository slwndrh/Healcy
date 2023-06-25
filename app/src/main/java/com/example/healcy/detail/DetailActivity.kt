package com.example.healcy.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.healcy.R
import com.example.healcy.data.Education
import com.example.healcy.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    companion object{
        const val EXTRA_DETAIL = "extra_detail"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = getString(R.string.education)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val article = intent.getParcelableExtra<Education>(EXTRA_DETAIL)
        if (article != null) {
            showDetail(article)
        }

//        showDetail()
    }

    private fun showDetail(education: Education) {
        binding.tvDetailTitle.text = education.title
        binding.tvDetailAuthor.text = education.author
        binding.tvDetailContent.text = education.content

        Glide.with(this)
            .load(education.linkImage)
            .into(binding.ivDetailPhoto)
    }

//    private fun showDetail() {
//        val data = intent.getParcelableExtra<Education>(EXTRA_DETAIL)
//        binding.apply {
//            tvDetailTitle.text = data?.title
//            tvDetailAuthor.text = data?.author
//            tvDetailContent.text = data?.content
//            Glide.with(this@DetailActivity)
//                .load(data?.linkImage)
//                .into(ivDetailPhoto)
//        }
//    }
}