package com.adhit.submission1.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.adhit.submission1.R
import com.adhit.submission1.adapter.SectionPagerAdapter
import com.adhit.submission1.data.local.entity.Favorite
import com.adhit.submission1.databinding.ActivityDetailBinding
import com.adhit.submission1.utils.ViewModelFactory
import com.adhit.submission1.viewmodel.DetailViewModel
import com.adhit.submission1.viewmodel.FavoriteViewModel
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel by viewModels<DetailViewModel>()
    private val favoriteViewModel: FavoriteViewModel by viewModels{ViewModelFactory.getInstance(this)}
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val user = intent.getStringExtra(USERNAME)

        detailViewModel.getDetail(user.toString())

        detailViewModel.username.observe(this) { dataUser ->
            Glide.with(this@DetailActivity)
                .load(dataUser.avatarUrl)
                .circleCrop()
                .into(binding.photo)
            binding.fullname.text = dataUser.name
            binding.username.text = dataUser.login
            val sectionPagerAdapter = SectionPagerAdapter(this)
            sectionPagerAdapter.username = user.toString()
            binding.viewPager.adapter = sectionPagerAdapter
            val tabs : TabLayout = findViewById(R.id.tabs)
            val follow = mutableListOf<String>(
                String.format(getString(R.string.Followers,dataUser.followers)),
                String.format(getString(R.string.Following,dataUser.following))
            )
            TabLayoutMediator(tabs, binding.viewPager) { tab, position ->
                tab.text = follow[position]
            }.attach()
            supportActionBar?.elevation = 0f
            favoriteViewModel.getFavoriteUserByUsername(dataUser.login).observe(this@DetailActivity) { user ->
                isFavorite = user != null
                setFavorite(isFavorite)

                binding.apply {
                    btnFav.setOnClickListener{
                        if (isFavorite) {
                            favoriteViewModel.delete(user)
                            isFavorite=false
                        } else {
                            val favoriteUser = Favorite(
                                username = dataUser.login,
                                avatarUrl = dataUser.avatarUrl
                            )
                            favoriteViewModel.addFavorite(favoriteUser)
                            setFavorite(isFavorite)
                            isFavorite = true
                        }
                        setFavorite(isFavorite)
                    }
                }
            }
        }

        detailViewModel.isLoading.observe(this) {
            showLoading(it)
        }

    }

    private fun setFavorite(isFavorite: Boolean) {
        if (isFavorite){
            binding.btnFav.setImageDrawable(ContextCompat.getDrawable(this@DetailActivity, R.drawable.ic_baseline_favorite_24))
        }else{
            binding.btnFav.setImageDrawable(ContextCompat.getDrawable(this@DetailActivity, R.drawable.ic_baseline_favorite_border_24))
        }
    }

    private fun showLoading(state: Boolean) { binding.progressBar.visibility = if (state) View.VISIBLE else View.GONE }

    companion object{
        const val USERNAME = "Zepee"
        const val TAG = "DetailActivity"
    }

}
