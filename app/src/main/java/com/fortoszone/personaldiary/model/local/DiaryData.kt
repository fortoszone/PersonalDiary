package com.fortoszone.personaldiary.model.local

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DiaryData(
    @SerializedName("page")
    var page: Int = 1,

    @SerializedName("limit")
    var limit: Int = 10,

    @SerializedName("total_data")
    var totalData: Int = 0
) : Parcelable
