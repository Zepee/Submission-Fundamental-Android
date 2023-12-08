package com.adhit.submission1.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.adhit.submission1.R
import com.adhit.submission1.adapter.ListUserAdapter
import com.adhit.submission1.data.remote.response.Items
import com.adhit.submission1.databinding.ActivityMainBinding
import com.adhit.submission1.viewmodel.ListViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val listViewModel by viewModels<ListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val layoutManager = LinearLayoutManager(this)
        binding.rvUser.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvUser.addItemDecoration(itemDecoration)

        listViewModel.getSearchList.observe(this) { searchList ->
            setUserData(searchList)
        }

        listViewModel.isLoading.observe(this) { isLoading ->
            showLoading(isLoading)
        }

        binding.apply {
            searchView.setupWithSearchBar(searchBar)
            searchView.editText.setOnEditorActionListener{ textView, actionId, event ->
                searchBar.text = searchView.text
                searchView.hide()
                listViewModel.findUsers(searchView.text.toString())
                false
            }
        }
    }
    private fun setUserData(searchList: List<Items>) {
        val adapter = ListUserAdapter()
        adapter.submitList(searchList)
        binding.rvUser.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.list_favorite -> {
                val moveIntent = Intent(this, FavoriteActivity::class.java)
                startActivity(moveIntent)
                return true
            }
            R.id.list_mode -> {
                val moveIntent = Intent(this, ThemeActivity::class.java)
                startActivity(moveIntent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}