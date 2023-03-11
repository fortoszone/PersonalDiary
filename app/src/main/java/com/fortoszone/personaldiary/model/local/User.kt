package com.fortoszone.personaldiary.model.local

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
    val id: String = "",

    @SerializedName("email")
    val email: String = "",

    @SerializedName("username")
    val username: String = "",

    @SerializedName("password")
    val password: String = "",

    @SerializedName("password_confirmation")
    val passwordConfirmation: String = ""

)