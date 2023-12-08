package com.adhit.submission1.injection

import android.content.Context
import com.adhit.submission1.data.local.room.FavoriteRoomDatabase
import com.adhit.submission1.data.repository.RepositoryUser
import com.adhit.submission1.utils.AppExecutors
import com.adhit.submission1.utils.ThemeSettings
import com.adhit.submission1.utils.dataStore

object Injection {
    fun provoideRepository(context: Context) : RepositoryUser {
        val database = FavoriteRoomDatabase.getInstance(context)
        val dao = database.favoriteDao()
        val appExecutors = AppExecutors()
        val settingPreferences = ThemeSettings.getInstance(context.dataStore)
        return RepositoryUser.getInstance(dao, appExecutors, settingPreferences)
    }
}