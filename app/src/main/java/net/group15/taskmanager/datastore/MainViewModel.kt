package net.group15.taskmanager.datastore

import android.app.Application
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
/**
 * Description
 * @author Leland Carter
 * @version 1.0
 * @since 2023-04-11
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