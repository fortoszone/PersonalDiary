package com.fortoszone.personaldiary.model.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    // https://private-anon-96d5035311-halfwineaid.apiary-mock.com/auth/login
    @SerializedName("user")
    val user: UserLoginResponse? = null,

    @SerializedName("access_token")
    val accessToken: String? = ""

)