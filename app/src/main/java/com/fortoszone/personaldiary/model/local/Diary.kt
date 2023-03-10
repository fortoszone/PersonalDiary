package com.fortoszone.personaldiary.model.local

import com.google.gson.annotations.SerializedName

data class Diary(
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
