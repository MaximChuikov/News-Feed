package com.example.news_feed.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.news_feed.R
import com.example.news_feed.databinding.ViewTypeBinding
import com.example.news_feed.databinding.ViewTypeImageBinding
import com.example.news_feed.databinding.ViewTypeImageCircleBinding
import com.example.news_feed.models.UsersModel
import com.squareup.picasso.Picasso


class UsersListAdapter() : ListAdapter<UsersModel, RecyclerView.ViewHolder>(MyDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            R.layout.view_type_image -> {
                val binding = ViewTypeImageBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
                UserHolderImage(binding)
            }

            R.layout.view_type_image_description -> {
                val binding = ViewTypeImageBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
                UserDescription(binding)
            }

            R.layout.view_type_image_circle -> {
                val binding = ViewTypeImageCircleBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
                UserCircleImage(binding)
            }

            R.layout.view_type -> {
                val binding = ViewTypeBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
                UserHolder(binding)
            }

            else -> throw IllegalStateException("Неизвестный тип $viewType")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is UsersModel.UserImage -> R.layout.view_type_image
            is UsersModel.UserDesc -> R.layout.view_type_image_description
            is UsersModel.UserCircle -> R.layout.view_type_image_circle
            is UsersModel.UserDefault -> R.layout.view_type
            else -> Int.MAX_VALUE
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            R.layout.view_type_image -> (holder as UserHolderImage).bind(
                getItem(position) as UsersModel.UserImage
            )

            R.layout.view_type_image_description -> (holder as UserDescription).bind(
                getItem(position) as UsersModel.UserDesc
            )

            R.layout.view_type_image_circle -> (holder as UserCircleImage).bind(
                getItem(position) as UsersModel.UserCircle
            )

            R.layout.view_type -> (holder as UserHolder).bind(
                getItem(position) as UsersModel.UserDefault
            )
            else -> throw IllegalStateException("Неизвестный тип ${holder.itemViewType}")
        }
    }

    class UserHolderImage(private val binding: ViewTypeImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(news: UsersModel.UserImage) = with(binding) {
            header.text = news.title
            subHeader.text = news.subtitle
            Picasso.get().load(news.imageUrl)
                .placeholder(R.drawable.animated_loading)
                .error(R.drawable.ic_broken_image)
                .into(image)
        }
    }

    class UserDescription(private val binding: ViewTypeImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(news: UsersModel.UserDesc) = with(binding) {
            header.text = news.title
            subHeader.text = news.subtitle
            containerText.setBackgroundColor(Color.BLACK)
            Picasso.get().load(news.imageUrl)
                .placeholder(R.drawable.animated_loading)
                .error(R.drawable.ic_broken_image)
                .into(image)
        }
    }

    class UserCircleImage(private val binding: ViewTypeImageCircleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(news: UsersModel.UserCircle) = with(binding) {
            header.text = news.title
            subHeader.text = news.subtitle
            Picasso.get().load(news.imageUrl)
                .placeholder(R.drawable.animated_loading)
                .error(R.drawable.ic_broken_image)
                .into(image)
        }
    }

    class UserHolder(private val binding: ViewTypeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(news: UsersModel) = with(binding) {
            header.text = news.title
            subHeader.text = news.subtitle
        }
    }

    class MyDiffCallback : DiffUtil.ItemCallback<UsersModel>() {
        override fun areItemsTheSame(oldItem: UsersModel, newItem: UsersModel): Boolean {
            return oldItem == newItem
        }
        override fun areContentsTheSame(oldItem: UsersModel, newItem: UsersModel): Boolean {
            return oldItem == newItem
        }
    }
}