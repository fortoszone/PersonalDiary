package com.fortoszone.personaldiary.model.remote.response

import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    // https://private-anon-96d5035311-halfwineaid.apiary-mock.com/auth/register
    @SerializedName("email")
    val email: String?,

    @SerializedName("username")
    val username: String?,

    @SerializedName("password")
    val password: String?,

    @SerializedName("password_confirmation")
    val passwordConfirmation: String?


)
