package com.example.healcy.response

import com.example.healcy.data.User
import com.google.gson.annotations.SerializedName

data class UserResponse(
    @field:SerializedName("user")
    val user: ArrayList<User>,

    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String
)
