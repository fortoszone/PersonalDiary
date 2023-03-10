package com.fortoszone.personaldiary.model

import com.google.gson.annotations.SerializedName

data class Token(
    @SerializedName("user")
    val user: User,

    @SerializedName("access_token")
    val accessToken: String = ""
)