package com.fortoszone.personaldiary.ui.archive

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fortoszone.personaldiary.R
import com.fortoszone.personaldiary.adapter.HomeAdapter
import com.fortoszone.personaldiary.databinding.ActivityArchiveBinding
import com.fortoszone.personaldiary.databinding.ActivityHomeBinding
import com.fortoszone.personaldiary.model.local.Diary
import com.fortoszone.personaldiary.ui.addDiary.AddDiaryActivity
import com.fortoszone.personaldiary.ui.home.HomeViewModel

class ArchiveActivity : AppCompatActivity() {
    private lateinit var binding: ActivityArchiveBinding
    private lateinit var archiveViewModel: ArchiveViewModel
    private lateinit var rvArchivedDiary: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArchiveBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = getString(R.string.archive_title)

        archiveViewModel = ArchiveViewModel()

        rvArchivedDiary = binding.rvArchivedDiary
        rvArchivedDiary.setHasFixedSize(true)
        binding.progressBar.visibility = View.VISIBLE
        loadRecyclerView()
    }

    private fun loadRecyclerView() {
        rvArchivedDiary.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        archiveViewModel.loadDiaries(this) { diaries: List<Diary> ->
            rvArchivedDiary.adapter = HomeAdapter(diaries)
        }
        binding.progressBar.visibility = View.INVISIBLE
    }
}