package com.fortoszone.personaldiary.model.remote.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DiaryService {
    private const val BASE_URL = "https://private-anon-5a9c59ad72-halfwineaid.apiary-mock.com/"
    private var retrofit: Retrofit? = null

    fun getInstance(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build()
        }

        return retrofit!!
    }
}