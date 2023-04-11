package net.group15.taskmanager

import android.icu.text.SimpleDateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import net.group15.taskmanager.data.Task
import net.group15.taskmanager.databinding.AdapterTaskListBinding
import net.group15.taskmanager.datastore.MainViewModel
import net.group15.taskmanager.objects.SharedPrefs
import net.group15.taskmanager.objects.SwipeToDeleteCallBack
import java.util.*

class RecyclerAdapter(
    private val taskList: MutableList<Task>,
    private val viewModel: MainViewModel
) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {


    // Inflate the layout for each item in the RecyclerView.
    // create a ViewHolder that will hold the views for the item.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        return ViewHolder(
            AdapterTaskListBinding.inflate(
                parent.layoutLayoutInflater(),
                parent,
                false
            )
        )
    }

    // Binding data to the views in the ViewHolder.
    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {

        val task = taskList[position]

        holder.binding.apply {
            tvTitle.text = task.name
            tvStart.text =
                SimpleDateFormat("hh:mm a", Locale.getDefault()).format(task.startingTime)
            tvEnd.text = SimpleDateFormat("hh:mm a", Locale.getDefault()).format(task.endingTime)
            tvDescription.text = task.description
            val task = taskList[position]


            // TODO: Add back change live date
            /*root.setOnClickListener {
                viewModel.changeLiveDate.value = task;
            }*/
        }

    }

    // Return the total number of items in the data set.
    override fun getItemCount(): Int {
        return taskList.size
    }

    // ViewHolder class holds references to the views in the item.
    inner class ViewHolder(val binding: AdapterTaskListBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    // Simplify the process of obtaining a LayoutInflater instance when inflating views.
    private fun View.layoutLayoutInflater(): LayoutInflater {
        return LayoutInflater.from(this.context);
    }
}