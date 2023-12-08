package com.adhit.submission1.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.adhit.submission1.data.repository.RepositoryUser
import kotlinx.coroutines.launch

class ThemeViewModel(private val repositoryUser: RepositoryUser) : ViewModel() {

    fun getThemeSettings(): LiveData<Boolean> {
        return repositoryUser.getThemeSetting().asLiveData()
    }

    fun saveThemeSetting(isDarkModeActive: Boolean) {
        viewModelScope.launch {
            repositoryUser.saveThemeSetting(isDarkModeActive)
        }
    }
}