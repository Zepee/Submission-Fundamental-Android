package com.adhit.submission1.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class ThemeSettings private constructor(private val dataStore: DataStore<Preferences>) {

    private val THEMEKEY = booleanPreferencesKey("theme_setting")

    fun getThemeSetting(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[THEMEKEY] ?: false
        }
    }

    suspend fun saveThemeSetting(isDarkModeActive: Boolean) {
        dataStore.edit { preferences ->
            preferences[THEMEKEY] = isDarkModeActive
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: ThemeSettings? = null

        fun getInstance(dataStore: DataStore<Preferences>): ThemeSettings {
            return INSTANCE ?: synchronized(this) {
                val instance = ThemeSettings(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}