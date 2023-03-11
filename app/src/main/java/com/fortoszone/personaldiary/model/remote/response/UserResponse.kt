package com.fortoszone.personaldiary.model.remote.response

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("email")
    val email: String?,

    @SerializedName("username")
    val username: String?,

    @SerializedName("password")
    val password: String?,

    @SerializedName("password_confirmation")
    val passwordConfirmation: String?


)
