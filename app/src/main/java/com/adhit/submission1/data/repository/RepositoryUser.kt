package com.adhit.submission1.data.repository

import androidx.lifecycle.LiveData
import com.adhit.submission1.data.local.entity.Favorite
import com.adhit.submission1.data.local.room.FavoriteDao
import com.adhit.submission1.utils.AppExecutors
import com.adhit.submission1.utils.ThemeSettings
import kotlinx.coroutines.flow.Flow

class RepositoryUser (
    private val favoriteDao: FavoriteDao,
    private val appExecutors: AppExecutors,
    private val settingPreferences: ThemeSettings
) {

    fun addFavorite(favoriteUser: Favorite) {
        appExecutors.diskIO.execute{
            favoriteDao.addUser(favoriteUser)
        }
    }

    fun getAllUsers() : LiveData<List<Favorite>> = favoriteDao.getAllUsers()

    fun getFavoriteUserByUsername(username: String): LiveData<Favorite> = favoriteDao.getUserByUsername(username)

    fun delete(favorite: Favorite) {
        appExecutors.diskIO.execute{
            favoriteDao.delete(favorite)
        }
    }

    fun getThemeSetting(): Flow<Boolean> = settingPreferences.getThemeSetting()

    suspend fun saveThemeSetting(isDarkModeActive: Boolean) = settingPreferences.saveThemeSetting(isDarkModeActive)
    companion object {
        @Volatile
        private var INSTANCE: RepositoryUser? = null

        fun getInstance(
            favoriteDao: FavoriteDao,
            appExecutors: AppExecutors,
            settingPreferences: ThemeSettings
        ):RepositoryUser = INSTANCE ?: synchronized(this) {
            INSTANCE ?: RepositoryUser(favoriteDao,appExecutors, settingPreferences)
        }.also { INSTANCE = it }
    }
}