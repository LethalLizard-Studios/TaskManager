package net.group15.taskmanager.datastore

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.group15.taskmanager.data.Task
import net.group15.taskmanager.objects.SharedPrefs

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