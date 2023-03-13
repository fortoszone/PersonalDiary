package com.fortoszone.personaldiary.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fortoszone.personaldiary.R
import com.fortoszone.personaldiary.adapter.HomeAdapter
import com.fortoszone.personaldiary.databinding.ActivityHomeBinding
import com.fortoszone.personaldiary.model.local.Diary
import com.fortoszone.personaldiary.model.local.DiaryData
import com.fortoszone.personaldiary.ui.addDiary.DiaryEditorActivity
import com.fortoszone.personaldiary.ui.archive.ArchiveActivity
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var rvDiaries: RecyclerView
    private lateinit var adapter: HomeAdapter
    private lateinit var homeViewModel: HomeViewModel
    private var diaries: ArrayList<Diary> = arrayListOf()
    private val searchList = MutableLiveData<ArrayList<Diary>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        homeViewModel = HomeViewModel()

        adapter = HomeAdapter(diaries)

        rvDiaries = binding.rvDiaries
        rvDiaries.setHasFixedSize(true)
        loadRecyclerViewList()

        binding.btnAddNewDiary.setOnClickListener {
            val intent = Intent(this@HomeActivity, DiaryEditorActivity::class.java)
            startActivity(intent)
        }

        supportActionBar!!.setDisplayShowTitleEnabled(false);
    }

    private fun loadRecyclerViewList() {
        rvDiaries.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        homeViewModel.loadDiaries(this) { diaries: List<Diary> ->
            rvDiaries.adapter = HomeAdapter(diaries)
        }
    }

    private fun loadRecyclerViewSearchList() {
        rvDiaries.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        homeViewModel.loadDiaries(this) { diaries: List<Diary> ->
            rvDiaries.adapter = HomeAdapter(diaries)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)

        val search: MenuItem? = menu?.findItem(R.id.btn_search)
        val sv: SearchView = search!!.actionView as SearchView
        sv.queryHint = getString(R.string.search_title)

        sv.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return if (query!!.isNotEmpty()) {
                    diaries.clear()
                    binding.progressBar.visibility = View.VISIBLE
                    adapter.notifyDataSetChanged()
                    getDiaryData(query)
                    binding.rvDiaries.visibility = View.INVISIBLE
                    binding.llHeader.visibility = View.INVISIBLE
                    binding.cvAddNewDiary.visibility = View.INVISIBLE
                    true

                } else {
                    diaries.clear()
                    false
                }
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                diaries.clear()
                return false
            }


        })

        val archive: MenuItem? = menu.findItem(R.id.btn_archive)
        archive?.setOnMenuItemClickListener {
            val intent = Intent(this, ArchiveActivity::class.java)
            this.startActivity(intent)
            true
        }

        binding.rvDiaries.visibility = View.VISIBLE
        binding.llHeader.visibility = View.VISIBLE
        binding.cvAddNewDiary.visibility = View.VISIBLE
        return super.onCreateOptionsMenu(menu)

    }

    private fun getDiaryData(query: String) {
        val client = AsyncHttpClient()
        val url = "https://private-anon-96d5035311-halfwineaid.apiary-mock.com/diary?search=$query"
        client.addHeader("Authorization", "Bearer YOURACCESSTOKENGOESHERE")
        client.addHeader("Accept", "application/json")
        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<Header>,
                responseBody: ByteArray
            ) {
                val response = String(responseBody)
                parseJsonData(response)
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<Header>,
                responseBody: ByteArray,
                error: Throwable
            ) {
                val errorMessage = when (statusCode) {
                    401 -> "$statusCode : Bad Request"
                    403 -> "$statusCode : Forbidden"
                    404 -> "$statusCode : Not Found"
                    else -> "$statusCode : ${error.message}"
                }
                Toast.makeText(this@HomeActivity, errorMessage, Toast.LENGTH_SHORT).show()
            }
        })

    }

    private fun parseJsonData(response: String) {
        try {
            val jsonObject = JSONObject(response)
            val items = jsonObject.getJSONArray("data")
            val diaryData = DiaryData()
            diaryData.page = jsonObject.getInt("page")
            diaryData.limit = jsonObject.getInt("limit")
            diaryData.totalData = jsonObject.getInt("total_data")

            for (i in 0 until items.length()) {
                val item = items.getJSONObject(i)
                val diary = Diary()
                diary.id = item.getString("id")
                diary.title = item.getString("title")
                diary.content = item.getString("content")
                diary.isArchived = item.getBoolean("is_archieved")
                diary.createdAt = item.getString("created_at")
                diary.updatedAt = item.getString("updated_at")
                diaries.add(diary)
            }

            searchList.postValue(diaries)
            loadRecyclerViewSearchList()
            binding.progressBar.visibility = View.INVISIBLE
            binding.rvSearchDiary.visibility = View.VISIBLE

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onRestart() {
        super.onRestart()
        diaries.clear()
        binding.rvDiaries.visibility = View.VISIBLE
        binding.llHeader.visibility = View.VISIBLE
        binding.cvAddNewDiary.visibility = View.VISIBLE
    }

    override fun onResume() {
        super.onResume()
        binding.rvDiaries.visibility = View.VISIBLE
        binding.llHeader.visibility = View.VISIBLE
        binding.cvAddNewDiary.visibility = View.VISIBLE
    }
}