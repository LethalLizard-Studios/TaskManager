package net.group15.taskmanager.datastore

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import net.group15.taskmanager.data.Task

/**
 * Author: Leland Carter
 */

class MainViewModel() : ViewModel() {

    private val fireStore = FirebaseFirestore.getInstance()

    val taskLiveDate = MutableLiveData<List<Task>>(mutableListOf())

    val changeLiveDate= MutableLiveData<Task?>()

    fun deleteTask(task: Task) {
        fireStore.collection("tasks").document(task.id!!)
            .delete().addOnSuccessListener {
                val oldValue = ArrayList(taskLiveDate.value!!)
                oldValue.remove(task)
                taskLiveDate.value = oldValue
            }

    }

    fun addTask(task: Task) {
        fireStore.collection("tasks")
            .document(task.id!!)
            .set(task)
            .addOnSuccessListener {
                getTaskData()
            }.addOnFailureListener {
                Log.d("TAG", "addTask: " + it.localizedMessage)
            }
    }

    fun getTaskData() {
        fireStore.collection("tasks")
            .get().addOnSuccessListener {
                taskLiveDate.value = it.toObjects(Task::class.java)
            }
    }

    fun updateTask(task: Task) {
        fireStore.collection("tasks")
            .document(task.id!!)
            .set(task)
            .addOnSuccessListener {
               getTaskData()
            }.addOnFailureListener {
                Log.d("TAG", "addTask: " + it.localizedMessage)
            }
    }
}