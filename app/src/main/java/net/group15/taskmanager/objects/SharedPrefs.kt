package net.group15.taskmanager.objects

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import net.group15.taskmanager.Home
import net.group15.taskmanager.data.Task
import net.group15.taskmanager.datastore.DataStoreManager
import net.group15.taskmanager.datastore.MainViewModel

/**
 * Singleton object used to save and load task data locally.
 * Author: Leland Carter
 */

object SharedPrefs : AppCompatActivity() {
    var taskList = mutableListOf<Task>()

    private lateinit var viewModel: MainViewModel
    private lateinit var dataStoreManager: DataStoreManager
    private lateinit var owner: LifecycleOwner

    fun initialize(viewModel: MainViewModel, dataStoreManager: DataStoreManager,
                   owner: LifecycleOwner) {
        this.viewModel = viewModel
        this.dataStoreManager = dataStoreManager
        this.owner = owner

        loadData()
    }

    fun add(task: Task) {
        taskList.add(task)
        saveData()
    }

    fun remove(index: Int) {
        taskList.removeAt(index)
        saveData()
    }

    fun remove(task: Task) {
        taskList.remove(task)
        saveData()
    }

    // Fully clears all tasks and saved data, factory reset
    fun reset() {
        viewModel.storeTasks("")
        taskList.clear()
    }

    // Saves the users data locally
    private fun saveData() {
        // Uses Gson to convert our list into Json
        val gson = Gson()
        val json = gson.toJson(taskList)

        viewModel.storeTasks(json)

        println("Saving: $json")
    }

    // Loads the users local data, only needs to be called at launch
    private fun loadData() {
        viewModel.retrieveTasks.observe(owner){tasks ->
            val gson = Gson()

            val listType = object: TypeToken<MutableList<Task>>() {
            }.type

            if (tasks.isNotEmpty() && taskList.isEmpty()) {
                println("Loading: $tasks")
                taskList = gson.fromJson(tasks, listType)
            }
        }
    }
}