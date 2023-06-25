package com.example.healcy.api

import com.example.healcy.response.EducationResponse
import com.example.healcy.response.LoginResponse
import com.example.healcy.response.SignupResponse
import com.example.healcy.response.UserResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @FormUrlEncoded
    @POST("register")
    fun signupUser(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ) : Call<SignupResponse>

    @FormUrlEncoded
    @POST("login")
    fun loginUser(
        @Field("email") email: String,
        @Field("password") password: String
    ) : Call<LoginResponse>

//    @GET("user")
//    fun getUser(
//        @Header("Authorization") auth: String,
//    ) : Call<UserResponse>

    @GET("articles")
    fun getArticle(
//        @Header("Authorization") token: String,
        @Query("id") id:String,
        @Query("linkImage") linkImage:String,
        @Query("title") title:String,
        @Query("author") author:String,
        @Query("content") content:String
    ) : Call<EducationResponse>
}