package net.group15.taskmanager.objects

import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import net.group15.taskmanager.data.Task

/**
 * Singleton object used to save and load task data locally.
 * Author: Leland Carter
 */

object SharedPreferences : AppCompatActivity() {

    // Saves the users data locally
    fun saveData(data: MutableList<Task>) {
        val sharedPreferences = getSharedPreferences("local preferences", MODE_PRIVATE)
        val sharedEdit = sharedPreferences.edit()

        // Uses Gson to convert our list into Json
        val gson = Gson()
        val json = gson.toJson(data)

        sharedEdit.putString("tasks", json)

        sharedEdit.apply()
    }

    // Loads the users local data and returns it
    fun loadData() : MutableList<Task> {
        val sharedPreferences = getSharedPreferences("local preferences", MODE_PRIVATE)

        val gson = Gson()
        val json = sharedPreferences.getString("tasks", "")

        // TODO: Turn the json data back into MutableList<Task>

        /*val list = object: TypeToken<MutableList<Task>>() {
        }.type

        return gson.fromJson(json, list)*/

        // DOES NOT RETURN SAVED DATA YET!
        return mutableListOf<Task>()
    }
}