package com.fortoszone.personaldiary.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fortoszone.personaldiary.R
import com.fortoszone.personaldiary.adapter.HomeAdapter
import com.fortoszone.personaldiary.databinding.ActivityHomeBinding
import com.fortoszone.personaldiary.databinding.ActivitySplashBinding
import com.fortoszone.personaldiary.model.local.Diary
import com.fortoszone.personaldiary.ui.addDiary.AddDiaryActivity
import com.fortoszone.personaldiary.ui.archive.ArchiveActivity

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var rvDiaries: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        homeViewModel = HomeViewModel()

        rvDiaries = binding.rvDiaries
        rvDiaries.setHasFixedSize(true)
        loadRecyclerView()

        binding.btnArchive.setOnClickListener {
            val intent = Intent(this@HomeActivity, ArchiveActivity::class.java)
            startActivity(intent)
        }

        binding.btnAddNewDiary.setOnClickListener {
            val intent = Intent(this@HomeActivity, AddDiaryActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loadRecyclerView() {
        rvDiaries.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        homeViewModel.loadDiaries(this) {diaries: List<Diary> ->
            rvDiaries.adapter = HomeAdapter(diaries)}
    }
}