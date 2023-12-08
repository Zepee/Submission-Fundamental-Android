package com.adhit.submission1.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.adhit.submission1.data.local.entity.Favorite
import com.adhit.submission1.data.repository.RepositoryUser

class FavoriteViewModel(private val repositoryUser: RepositoryUser) : ViewModel() {

    fun getAllUsers() : LiveData<List<Favorite>> = repositoryUser.getAllUsers()

    fun getFavoriteUserByUsername(username: String) : LiveData<Favorite> = repositoryUser.getFavoriteUserByUsername(username)

    fun addFavorite(favorite: Favorite) {
        repositoryUser.addFavorite(favorite)
    }

    fun delete(favorite: Favorite) {
        repositoryUser.delete(favorite)
    }
}