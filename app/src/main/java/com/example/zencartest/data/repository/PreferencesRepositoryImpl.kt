package com.example.zencartest.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.zencartest.domain.repository.PreferencesRepository
import com.example.zencartest.utils.SETTINGS_PREFERENCES
import com.example.zencartest.utils.USER_TOKEN
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = SETTINGS_PREFERENCES,
)

class PreferencesRepositoryImpl @Inject constructor(
    private val context: Context,
) : PreferencesRepository {

    override suspend fun saveUserTokenToDataStorage(userToken: String) {
        val preferenceKey = stringPreferencesKey(USER_TOKEN)
        context.dataStore.edit { pref ->
            pref[preferenceKey] = userToken
        }
    }

    override suspend fun getUserTokenFromDataStorage(): Flow<String?> {
        val preferenceKey = stringPreferencesKey(USER_TOKEN)

        return context.dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                preferences[preferenceKey]
            }
    }

    // Очистка токена пользователя (выход)
    override suspend fun clearUserTokenFromDataStorage() {
        context.dataStore.edit { pref ->
            pref.clear()
        }
    }
}
