package com.example.healcy.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Hospital(
    val name: String,
    val number: String
):Parcelable
