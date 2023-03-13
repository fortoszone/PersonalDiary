package com.fortoszone.personaldiary.ui.detail

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.fortoszone.personaldiary.R
import com.fortoszone.personaldiary.databinding.ActivityDetailBinding
import com.fortoszone.personaldiary.model.local.Diary

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailViewModel: DetailViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        detailViewModel = DetailViewModel()

        val diary = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(EXTRA_DETAILS, Diary::class.java)
        } else {
            intent.getParcelableExtra<Diary>(EXTRA_DETAILS) as Diary
        }

        if (diary != null) {
            binding.tvTitle.text = diary.title
            binding.tvDiaryContent.text = diary.content
            binding.tvCreatedAt.text = diary.createdAt

            if (diary.isArchived == false) {
                binding.fabArchive.setImageDrawable(
                    ContextCompat.getDrawable(
                        this@DetailActivity, R.drawable.baseline_unarchive_24
                    )
                )
            } else {
                binding.fabArchive.setImageDrawable(
                    ContextCompat.getDrawable(
                        this@DetailActivity, R.drawable.baseline_archive_24
                    )
                )
            }

        } else {
            Toast.makeText(this, "Data is not retrieved yet", Toast.LENGTH_SHORT).show()
        }

        binding.fabArchive.setOnClickListener {
            if (!diary!!.isArchived) {
                detailViewModel.addToArchive(this, diary.id)
                diary.isArchived
            } else {
                detailViewModel.removeFromArchive(this, diary.id)
                !diary.isArchived
            }
        }
    }

    companion object {
        const val EXTRA_DETAILS = "extra_details"
    }
}