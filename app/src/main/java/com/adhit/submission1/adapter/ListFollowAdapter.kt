package com.adhit.submission1.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adhit.submission1.data.remote.response.Items
import com.adhit.submission1.databinding.ItemUserBinding
import com.adhit.submission1.ui.DetailActivity
import com.bumptech.glide.Glide

class ListFollowAdapter (private val listUser: List<Items>) :
    RecyclerView.Adapter<ListFollowAdapter.MyViewHolder>(){

    class MyViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(user: Items){
            binding.tvUsername.text = user.login

            binding.apply {
                Glide.with(itemView.context)
                    .load(user.avatarUrl)
                    .circleCrop()
                    .into(imgAvatar)
                itemView.setOnClickListener{
                    val intentDetail = Intent(itemView.context, DetailActivity::class.java)
                    intentDetail.putExtra(DetailActivity.USERNAME, user.login)
                    itemView.context.startActivity(intentDetail)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int =listUser.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(listUser[position])
    }

}