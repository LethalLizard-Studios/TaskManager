package net.group15.taskmanager.datastore

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Author: Leland Carter
 */

class MainViewModel(app: Application) : AndroidViewModel(app) {
    val dataStore = DataStoreManager(app)

    val retrieveTasks = dataStore.retrieveTasks().asLiveData(Dispatchers.IO)

    fun storeTasks(taskText: String) {
        viewModelScope.launch {
            dataStore.storeTasks(taskText)
        }
    }
}