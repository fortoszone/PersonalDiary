package com.fortoszone.personaldiary.model.remote.response

import com.fortoszone.personaldiary.model.local.Diary
import com.google.gson.annotations.SerializedName

data class DiaryListResponse(
    // https://private-anon-5a9c59ad72-halfwineaid.apiary-mock.com/diary?search=
    @SerializedName("data")
    val diaries: List<Diary>,

    @SerializedName("page")
    val page: String = "",

    @SerializedName("limit")
    val limit: String = "",

    @SerializedName("total_data")
    val totalDiaries: String = ""

)