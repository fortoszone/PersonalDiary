package com.fortoszone.personaldiary.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.fortoszone.personaldiary.R
import com.fortoszone.personaldiary.databinding.DiaryRowBinding
import com.fortoszone.personaldiary.model.local.Diary
import com.fortoszone.personaldiary.ui.detail.DetailActivity

class HomeAdapter(private val diaries: List<Diary>) :
    PagingDataAdapter<Diary, HomeAdapter.ViewHolder>(PHOTO_COMPARATOR) {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = DiaryRowBinding.bind(view)
        fun bind(diary: Diary) {
            binding.tvTitle.text = diary.title
            binding.tvCreatedAt.text = diary.createdAt

            with(itemView) {
                binding.llDiary.setOnClickListener {
                    val moveActivity = Intent(itemView.context, DetailActivity::class.java)
                    moveActivity.putExtra(DetailActivity.EXTRA_DETAILS, diary)
                    itemView.context.startActivity(moveActivity)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.diary_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeAdapter.ViewHolder, position: Int) {
        holder.bind(diaries[position])
    }

    override fun getItemCount() = diaries.size

    companion object {
        private val PHOTO_COMPARATOR = object : DiffUtil.ItemCallback<Diary>() {
            override fun areItemsTheSame(oldItem: Diary, newItem: Diary) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Diary, newItem: Diary) =
                oldItem == newItem
        }
    }
}