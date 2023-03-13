package com.fortoszone.personaldiary.ui.diary.add

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fortoszone.personaldiary.databinding.ActivityDiaryAddBinding

class AddDiaryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDiaryAddBinding
    private lateinit var addDiaryViewModel: AddDiaryViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDiaryAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addDiaryViewModel = AddDiaryViewModel()

        binding.btnSaveDiary.setOnClickListener {
            val diaryTitle = binding.etTitle.text.toString().trim()
            val diaryContent = binding.etContent.text.toString().trim()

            addDiaryViewModel.createDiary(this, diaryTitle, diaryContent)
        }
    }
}