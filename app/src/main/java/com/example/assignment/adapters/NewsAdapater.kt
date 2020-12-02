package com.example.assignment.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.assignment.R
import com.example.assignment.model.* //ktlint-disable
import kotlinx.android.synthetic.main.item_article_preview.view.*
import java.lang.Exception

class NewsAdapater : RecyclerView.Adapter<NewsAdapater.NewsViewHolder>() {

    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_article_preview, parent, false))
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val result = differ.currentList[position]
        holder.itemView.apply {
            try {
                Glide.with(this).load(result.media.elementAt(0).media_metadata.elementAt(0).url).into(ivArticleImage)
            } catch (e: Exception) {
            }
            tvSource.text = result.source
            tvTitle.text = result.title
            tvDescription.text = result.desciption
            tvPublishedAt.text = result.published_date
            setOnClickListener {
                onItemClickListener?.let { it(result) }
            }
        }
    }

    private var onItemClickListener: ((Result) -> Unit)? = null

    fun setOnItemClickListener(listener: (Result) -> Unit) {
        onItemClickListener = listener
    }
    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}
