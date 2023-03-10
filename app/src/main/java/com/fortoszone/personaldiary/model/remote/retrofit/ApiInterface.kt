package com.fortoszone.personaldiary.model.remote.retrofit

import com.fortoszone.personaldiary.model.remote.response.DiaryResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("/diary")
    fun getDiaryList(): Call<DiaryResponse>

}