package com.adhit.submission1.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.adhit.submission1.data.repository.RepositoryUser
import com.adhit.submission1.injection.Injection
import com.adhit.submission1.viewmodel.FavoriteViewModel
import com.adhit.submission1.viewmodel.ThemeViewModel

class ViewModelFactory(private val repositoryUser: RepositoryUser) :
    ViewModelProvider.NewInstanceFactory(){
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)){
            return  FavoriteViewModel(repositoryUser) as T
        } else if (modelClass.isAssignableFrom(ThemeViewModel::class.java)) {
            return ThemeViewModel(repositoryUser) as T
        }
        throw IllegalAccessException("Unknown Viewmodel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provoideRepository(context))
            }.also { instance = it }
    }
}