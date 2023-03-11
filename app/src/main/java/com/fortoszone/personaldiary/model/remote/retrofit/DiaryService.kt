package com.fortoszone.personaldiary.model.remote.retrofit

import com.fortoszone.personaldiary.model.remote.response.DiaryResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface DiaryService {
    @POST("/auth/register")
    fun register(): Call<DiaryResponse>

    @POST("/auth/login")
    fun login(): Call<DiaryResponse>

    @GET("/diary")
    fun getDiaryList(): Call<DiaryResponse>

    @GET("/diary/{diary_id}")
    fun getDiaryDetail(
        @Path("diary_id") diaryId: String
    ): Call<DiaryResponse>

    @PUT("/diary/{diary_id}")
    fun updateDiaryDetail(
        @Path("diary_id") diaryId: String
    ): Call<DiaryResponse>

    @POST("/diary")
    fun createDiary(): Call<DiaryResponse>

    @PUT("/diary/{diary_id}/archieve")
    fun archiveDiary(
        @Path("diary_id") diaryId: String
    ): Call<DiaryResponse>

    @PUT("/diary/{diary_id}/unarchieve")
    fun unarchiveDiary(
        @Path("diary_id") diaryId: String
    ): Call<DiaryResponse>
}