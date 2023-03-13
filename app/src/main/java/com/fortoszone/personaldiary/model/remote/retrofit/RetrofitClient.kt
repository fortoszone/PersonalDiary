package com.fortoszone.personaldiary.model.remote.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val MOCK_BASE_URL = "https://private-anon-196aef79a7-halfwineaid.apiary-mock.com/"
    private const val PROD_BASE_URL = "https://diary-test.ifdenewhallaid.com/"

    private var retrofit: Retrofit? = null

    fun getInstance(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder().baseUrl(MOCK_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build()
        }

        return retrofit!!
    }
}