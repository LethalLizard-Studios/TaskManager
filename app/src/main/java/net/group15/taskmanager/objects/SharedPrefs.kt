package net.group15.taskmanager.objects

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import net.group15.taskmanager.data.Task
import net.group15.taskmanager.datastore.DataStoreManager
import net.group15.taskmanager.datastore.MainViewModel

/**
 * Singleton object used to save and load task data locally.
 * Author: Leland Carter
 */

object SharedPrefs : AppCompatActivity() {
    private var taskList = mutableListOf<Task>()

    private lateinit var viewModel: MainViewModel
    private lateinit var dataStoreManager: DataStoreManager
    private lateinit var owner: LifecycleOwner

    fun initialize(viewModel: MainViewModel, dataStoreManager: DataStoreManager,
                   owner: LifecycleOwner) {
        this.viewModel = viewModel
        this.dataStoreManager = dataStoreManager
        this.owner = owner

        // Load on startup
        updateTaskList()
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
        updateTaskList()
    }

    fun updateTaskList() {
        taskList = loadData()
    }

    // Saves the users data locally
    private fun saveData() {
        // Uses Gson to convert our list into Json
        val gson = Gson()
        val json = gson.toJson(taskList)

        viewModel.storeTasks(json)

        println(json.toString())
    }

    // Loads the users local data and returns it
    private fun loadData() : MutableList<Task> {
        var finalList = mutableListOf<Task>()

        viewModel.retrieveTasks.observe(owner){tasks ->
            val gson = Gson()

            println(tasks.toString())

            val listType = object: TypeToken<MutableList<Task>>() {
            }.type

            if (tasks.isNotEmpty()) {
                finalList = gson.fromJson(tasks, listType)
                println("retrieved!")
            }
        }

        println("init: "+taskList.size.toString())
        println("loaded:"+finalList.size.toString())

        return finalList
    }
}