package com.fortoszone.personaldiary.ui.detail

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.fortoszone.personaldiary.R
import com.fortoszone.personaldiary.databinding.ActivityDetailBinding
import com.fortoszone.personaldiary.model.local.Diary
import com.fortoszone.personaldiary.ui.diary.update.UpdateDiaryActivity

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

            if (!diary.isArchived) {
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)

        val diary = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(EXTRA_DETAILS, Diary::class.java)
        } else {
            intent.getParcelableExtra<Diary>(EXTRA_DETAILS) as Diary
        }

        val edit: MenuItem? = menu?.findItem(R.id.btn_edit)
        edit?.setOnMenuItemClickListener {
            val intent = Intent(this, UpdateDiaryActivity::class.java)
            intent.putExtra(UpdateDiaryActivity.EXTRA_DETAILS, diary)
            this.startActivity(intent)
            true
        }

        return super.onCreateOptionsMenu(menu)
    }


}