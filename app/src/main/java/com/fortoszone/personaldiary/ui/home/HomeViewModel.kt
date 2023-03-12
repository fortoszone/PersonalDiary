package com.fortoszone.personaldiary.ui.home

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.fortoszone.personaldiary.model.local.Diary
import com.fortoszone.personaldiary.model.remote.response.DiaryListResponse
import com.fortoszone.personaldiary.model.remote.retrofit.DiaryService
import com.fortoszone.personaldiary.model.remote.retrofit.RetrofitClient
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel() : ViewModel() {
    fun loadDiaries(context: Context, callback: (List<Diary>) -> Unit) {
        val apiService = RetrofitClient.getInstance().create(DiaryService::class.java)
        apiService.getDiaryList()
            .enqueue(object : Callback<DiaryListResponse> {
                override fun onResponse(
                    call: Call<DiaryListResponse>,
                    response: Response<DiaryListResponse>
                ) {
                    return callback(response.body()!!.diaries)
                }

                override fun onFailure(call: Call<DiaryListResponse>, t: Throwable) {
                    Toast.makeText(context, t.message, Toast.LENGTH_LONG)
                        .show()

                }
            })
    }

//    fun searchDiary(context: Context, query: String) {
//        val apiService = RetrofitClient.getInstance().create(DiaryService::class.java)
//        apiService.searchDiary(query)
//
//        apiService.enqueue(object : Callback<DiaryListResponse> {
//            override fun onResponse(
//                call: Call<DiaryListResponse>,
//                response: Response<DiaryListResponse>
//            ) {
//                return callback(response.body()!!.diaries)
//            }
//
//            override fun onFailure(call: Call<DiaryListResponse>, t: Throwable) {
//                Toast.makeText(context, t.message, Toast.LENGTH_LONG)
//                    .show()
//
//            }
//        })


    //  }
}