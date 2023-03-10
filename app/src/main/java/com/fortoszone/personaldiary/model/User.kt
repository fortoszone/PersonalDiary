package com.fortoszone.personaldiary.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
    val id: String = "",

    @SerializedName("email")
    val email: String = "",

    @SerializedName("username")
    val username: String = ""
)