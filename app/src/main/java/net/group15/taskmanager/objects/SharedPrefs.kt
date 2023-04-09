package net.group15.taskmanager.objects

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import net.group15.taskmanager.data.Task

/**
 * Singleton object used to save and load task data locally.
 * Author: Leland Carter
 */

object SharedPrefs : AppCompatActivity() {

    // Saves the users data locally
    fun saveData(data: MutableList<Task>) {
        //val sharedPreferences = getSharedPreferences("local preferences", Context.MODE_PRIVATE)
        //val sharedEdit = preferences.edit()

        // Uses Gson to convert our list into Json
        val gson = Gson()
        val json = gson.toJson(data)

        println(json.toString())

        //sharedEdit.putString("tasks", json)

        //sharedEdit.apply()

        System.out.println(json.toString())
    }

    // Loads the users local data and returns it
    fun loadData() : MutableList<Task> {
        val sharedPreferences = getSharedPreferences("local preferences", Context.MODE_PRIVATE)

        val gson = Gson()
        val json = sharedPreferences.getString("tasks", "")

        // Converts the JSON back into the Task data
        val data = Gson().fromJson(json, Task::class.java)

        println(data.toString())

        val list = object: TypeToken<MutableList<Task>>() {
        }.type

        return gson.fromJson(json, list)

        // DOES NOT RETURN SAVED DATA YET!
        //return mutableListOf<Task>()
    }
}