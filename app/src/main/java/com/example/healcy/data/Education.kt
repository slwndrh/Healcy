package com.example.healcy.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Education(
    val id: String,
    val author: String,
    val linkImage: String,
    val title: String,
    val content: String
):Parcelable
