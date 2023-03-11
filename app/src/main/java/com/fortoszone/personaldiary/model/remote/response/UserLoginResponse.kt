package com.fortoszone.personaldiary.model.remote.response

import com.google.gson.annotations.SerializedName

data class UserLoginResponse (
    @SerializedName("id")
    val id: String = "",

    @SerializedName("email")
    val email: String = "",

    @SerializedName("username")
    val username: String = "",
)