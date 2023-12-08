package com.adhit.submission1.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.adhit.submission1.data.local.entity.Favorite
import com.adhit.submission1.databinding.ItemUserBinding
import com.adhit.submission1.ui.DetailActivity
import com.adhit.submission1.utils.loadImage

class FavoriteAdapter: ListAdapter<Favorite, FavoriteAdapter.MyViewHolder>(DIFF_CALLBACK){
    class MyViewHolder (private val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Favorite){
            binding.apply {
                imgAvatar.loadImage(item.avatarUrl)
                tvUsername.text = item.username
                itemView.setOnClickListener{
                    val intentDetail = Intent(itemView.context, DetailActivity::class.java)
                    intentDetail.putExtra(DetailActivity.USERNAME, item.username)
                    itemView.context.startActivity(intentDetail)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return FavoriteAdapter.MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)
    }

    companion object{
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Favorite>(){
            override fun areItemsTheSame(
                oldItem: Favorite,
                newItem: Favorite
            ): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(
                oldItem: Favorite,
                newItem: Favorite
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}