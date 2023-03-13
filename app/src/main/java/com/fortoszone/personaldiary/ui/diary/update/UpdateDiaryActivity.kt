package com.fortoszone.personaldiary.ui.diary.update

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fortoszone.personaldiary.databinding.ActivityDiaryUpdateBinding
import com.fortoszone.personaldiary.model.local.Diary

class UpdateDiaryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDiaryUpdateBinding
    private lateinit var viewModel: UpdateDiaryViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDiaryUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val diary = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(EXTRA_DETAILS, Diary::class.java)
        } else {
            intent.getParcelableExtra<Diary>(EXTRA_DETAILS) as Diary
        }

        val diaryId = diary!!.id
        binding.etTitle.setText(diary.title)
        binding.etContent.setText(diary.content)

        viewModel = UpdateDiaryViewModel()

        binding.btnSaveDiary.setOnClickListener {
            val diaryTitle = binding.etTitle.text.toString().trim()
            val diaryContent = binding.etContent.text.toString().trim()

            viewModel.updateDiary(this, diaryId, diaryTitle, diaryContent)
        }
    }

    companion object {
        const val EXTRA_DETAILS = "extra_details"
    }
}