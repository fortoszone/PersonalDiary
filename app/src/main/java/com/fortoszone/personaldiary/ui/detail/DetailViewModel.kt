package com.fortoszone.personaldiary.ui.detail

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.fortoszone.personaldiary.model.remote.response.DiaryResponse
import com.fortoszone.personaldiary.model.remote.retrofit.DiaryService
import com.fortoszone.personaldiary.model.remote.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel : ViewModel() {
    fun loadDiary(context: Context, id: String) {
        val apiService = RetrofitClient.getInstance().create(DiaryService::class.java)
        apiService.getDiaryDetail(id)
            .enqueue(object : Callback<DiaryResponse> {
                override fun onResponse(
                    call: Call<DiaryResponse>,
                    response: Response<DiaryResponse>
                ) {
                    Toast.makeText(context, response.body().toString(), Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<DiaryResponse>, t: Throwable) {
                    Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
                }
            })
    }

    fun addToArchive(context: Context, id: String) {
        val apiService = RetrofitClient.getInstance().create(DiaryService::class.java)
        apiService.archiveDiary(id).enqueue(object : Callback<DiaryResponse> {
            override fun onResponse(call: Call<DiaryResponse>, response: Response<DiaryResponse>) {
                Toast.makeText(
                    context,
                    "${response.body()?.id} added to archive",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onFailure(call: Call<DiaryResponse>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }

    fun removeFromArchive(context: Context, id: String) {
        val apiService = RetrofitClient.getInstance().create(DiaryService::class.java)
        apiService.unarchiveDiary(id).enqueue(object : Callback<DiaryResponse> {
            override fun onResponse(call: Call<DiaryResponse>, response: Response<DiaryResponse>) {
                Toast.makeText(
                    context,
                    "${response.body()?.title} removed from archive",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onFailure(call: Call<DiaryResponse>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
            }

        })

    }
}