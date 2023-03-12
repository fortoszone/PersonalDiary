package com.fortoszone.personaldiary.model.remote.response

import com.google.gson.annotations.SerializedName

data class DiaryResponse(
    @SerializedName("id")
    val id: String = "",

    @SerializedName("title")
    val title: String = "",

    @SerializedName("content")
    val content: String = "",

    @SerializedName("is_archieved")
    val isArchived: Boolean = false,

    @SerializedName("created_at")
    val createdAt: String = "",

    @SerializedName("updated_at")
    val updatedAt: String = ""
)
