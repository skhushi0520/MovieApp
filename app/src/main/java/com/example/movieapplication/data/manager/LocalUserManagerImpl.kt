package com.example.movieapplication.data.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.movieapplication.domain.manager.LocalUserManager
import com.example.movieapplication.util.Constants
import com.example.movieapplication.util.Constants.USER_SETTINGS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalUserManagerImpl(private val context: Context): LocalUserManager
{
    override suspend fun saveAppEntry() {
        context.dataStore.edit{ settings->
            settings[PreferencesKeys.APP_ENTRY] = true

        }
    }

    override fun readAppEntry(): Flow<Boolean> {
        return  context.dataStore.data.map { preferences->
            preferences[PreferencesKeys.APP_ENTRY]?:false
        }
    }
}
private val Context.dataStore: DataStore<androidx.datastore.preferences.core.Preferences> by preferencesDataStore(name = USER_SETTINGS)

private object PreferencesKeys{
    val APP_ENTRY = booleanPreferencesKey(name= Constants.APP_ENTRY)
}
