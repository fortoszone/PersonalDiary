package com.fortoszone.personaldiary.model.remote.retrofit

import com.fortoszone.personaldiary.model.remote.response.DiaryResponse
import com.fortoszone.personaldiary.model.remote.response.UserResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface DiaryService {
    @FormUrlEncoded
    @POST("/auth/register")
    fun registerUser(
        @Field("email") email: String,
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("password_confirmation") passwordConfirm: String,

        ): Call<UserResponse>

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