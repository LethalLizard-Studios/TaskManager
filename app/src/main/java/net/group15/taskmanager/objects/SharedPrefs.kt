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
 * @author Leland Carter
 * @version 1.0
 * @since 2023-04-11
 */
object SharedPrefs : AppCompatActivity() {
    var taskList = mutableListOf<Task>()

    private lateinit var viewModel: MainViewModel
    private lateinit var dataStoreManager: DataStoreManager
    private lateinit var owner: LifecycleOwner

    private var hasInitialized = false

    fun initialize(viewModel: MainViewModel, dataStoreManager: DataStoreManager,
                   owner: LifecycleOwner) {
        this.viewModel = viewModel
        this.dataStoreManager = dataStoreManager
        this.owner = owner

        hasInitialized = true

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
        if (hasInitialized) {
            // Uses Gson to convert our list into Json
            val gson = Gson()
            val json = gson.toJson(taskList)

            viewModel.storeTasks(json)

            println("Saving ${taskList.size}: $json")
        }
    }

    // Loads the users local data, only needs to be called at launch
    fun loadData() {
        if (hasInitialized) {
            viewModel.retrieveTasks.observe(owner){tasks ->
                val gson = Gson()

                val listType = object: TypeToken<MutableList<Task>>() {
                }.type

                if (tasks.isNotEmpty() && taskList.isEmpty()) {
                    taskList = gson.fromJson(tasks, listType)
                    println("Loading ${taskList.size}: $tasks")
                }
            }
        }
    }
}