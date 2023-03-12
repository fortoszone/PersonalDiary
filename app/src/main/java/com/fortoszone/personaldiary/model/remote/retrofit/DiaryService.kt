package com.fortoszone.personaldiary.model.remote.retrofit

import com.fortoszone.personaldiary.model.remote.response.DiaryListResponse
import com.fortoszone.personaldiary.model.remote.request.LoginRequest
import com.fortoszone.personaldiary.model.remote.response.DiaryResponse
import com.fortoszone.personaldiary.model.remote.response.LoginResponse
import com.fortoszone.personaldiary.model.remote.response.RegisterResponse
import retrofit2.Call
import retrofit2.http.*

interface DiaryService {
    @FormUrlEncoded
    @Headers("content-type: application/json")
    @POST("/auth/register")
    fun registerUser(
        @Field("email") email: String,
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("password_confirmation") passwordConfirm: String,

        ): Call<RegisterResponse>

    @POST("/auth/login")
    @Headers("content-type: application/json")
    fun loginUser(
        @Body loginRequest: LoginRequest,
    ): Call<LoginResponse>

    @GET("/diary")
    fun getDiaryList(): Call<DiaryListResponse>

    @GET("/diary/{diary_id}")
    fun getDiaryDetail(
        @Path("diary_id") diaryId: String
    ): Call<DiaryResponse>

    @PUT("/diary/{diary_id}")
    fun updateDiaryDetail(
        @Path("diary_id") diaryId: String
    ): Call<DiaryResponse>

    @POST("/diary")
    fun createDiary(): Call<DiaryListResponse>

    @PUT("/diary/{diary_id}/archieve")
    fun archiveDiary(
        @Path("diary_id") diaryId: String
    ): Call<DiaryListResponse>

    @PUT("/diary/{diary_id}/unarchieve")
    fun unarchiveDiary(
        @Path("diary_id") diaryId: String
    ): Call<DiaryListResponse>
}