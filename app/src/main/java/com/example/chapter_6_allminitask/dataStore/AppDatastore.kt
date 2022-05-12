package com.example.chapter_6_allminitask.dataStore


import android.annotation.SuppressLint
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AppDatastore(private val context: Context) {

    private val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = "app_datastore")
    private val USER_NAME = stringPreferencesKey(name = "user_name")
    private val USER_PASSWORD = stringPreferencesKey(name = "user_password")

    companion object {

        @SuppressLint("StaticFieldLeak")
        var INSTANCE: AppDatastore? = null
        fun getInstance(base: Context): AppDatastore? {
            if (INSTANCE == null) {
                synchronized(AppDatastore::class.java) {
                    INSTANCE = AppDatastore(base.applicationContext)
                }
            }

            return INSTANCE
        }
    }

    suspend fun setUserName(name: String) {
        context.datastore.edit { key ->
            key[USER_NAME] = name
        }
    }

    suspend fun setUserPassword(pass: String) {
        context.datastore.edit { key ->
            key[USER_PASSWORD] = pass
        }
    }

    val getUserName: Flow<String> = context.datastore.data.map { preferences ->
        preferences[USER_NAME] ?: ""
    }

    val getUserPassword: Flow<String> = context.datastore.data.map { preferences ->
        preferences[USER_PASSWORD] ?: ""
    }

}