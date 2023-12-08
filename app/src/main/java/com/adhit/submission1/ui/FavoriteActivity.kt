package com.adhit.submission1.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.adhit.submission1.adapter.FavoriteAdapter
import com.adhit.submission1.databinding.ActivityFavoriteBinding
import com.adhit.submission1.utils.ViewModelFactory
import com.adhit.submission1.viewmodel.FavoriteViewModel

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private val favoriteViewModel: FavoriteViewModel by viewModels{ ViewModelFactory.getInstance(this)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        favoriteViewModel.getAllUsers().observe(this@FavoriteActivity){
            val favoriteAdapter = FavoriteAdapter()
            favoriteAdapter.submitList(it)
            binding.apply {
                rvUserFavorite.layoutManager = LinearLayoutManager(this@FavoriteActivity)
                rvUserFavorite.adapter = favoriteAdapter
            }
        }
    }
}