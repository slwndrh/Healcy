package com.example.healcy.response

import com.example.healcy.data.Education
import com.example.healcy.data.User
import com.google.gson.annotations.SerializedName

data class EducationResponse(
//    @field:SerializedName("listData")
//    val listData: List<DataArticle>,

    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("id")
    val id: String
)

data class DataArticle(
    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("author")
    val author: String,

    @field:SerializedName("linkImage")
    val linkImage: String,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("content")
    val content: String,
)
