package com.fortoszone.personaldiary.model.local

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Diary(
    @SerializedName("id")
    var id: String = "",

    @SerializedName("title")
    var title: String = "",

    @SerializedName("content")
    var content: String = "",

    @SerializedName("is_archieved")
    var isArchived: Boolean = false,

    @SerializedName("created_at")
    var createdAt: String = "",

    @SerializedName("updated_at")
    var updatedAt: String = ""
) : Parcelable
