package com.fortoszone.personaldiary.ui.diary.add

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.fortoszone.personaldiary.model.remote.response.DiaryResponse
import com.fortoszone.personaldiary.model.remote.retrofit.DiaryService
import com.fortoszone.personaldiary.model.remote.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddDiaryViewModel : ViewModel() {
    fun createDiary(context: Context, diaryTitle: String, diaryContent: String) {
        val apiService = RetrofitClient.getInstance().create(DiaryService::class.java)
        apiService.createDiary(diaryTitle, diaryContent).enqueue(object :
            Callback<DiaryResponse> {
            override fun onResponse(
                call: Call<DiaryResponse>,
                response: Response<DiaryResponse>
            ) {
                if (response.code() == 201) {
                    Toast.makeText(
                        context,
                        "New diary created",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<DiaryResponse>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
            }
        })
    }
}
