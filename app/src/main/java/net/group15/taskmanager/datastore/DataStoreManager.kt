package net.group15.taskmanager.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import net.group15.taskmanager.data.Task
import net.group15.taskmanager.objects.SharedPrefs
import java.io.IOException

/**
 * Author: Leland Carter
 */

class DataStoreManager(context: Context) {
    private val Context.dataStore : DataStore<Preferences> by preferencesDataStore(name="TASK_DATA")
    private val dataStore = context.dataStore

    companion object{
        val taskKey = stringPreferencesKey("TASKS_KEY")
    }

    suspend fun storeTasks(taskText: String) {
        dataStore.edit { pref ->
            pref[taskKey] = taskText
        }
    }

    fun retrieveTasks() : Flow<String> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                }
                else {
                    throw exception
                }
            }
            .map { pref ->
                val tasks = pref[taskKey] ?: false
                tasks.toString()
            }
    }
}