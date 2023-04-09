package net.group15.taskmanager

import android.icu.text.SimpleDateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import net.group15.taskmanager.data.Task
import net.group15.taskmanager.databinding.AdapterTaskListBinding
import net.group15.taskmanager.datastore.MainViewModel
import net.group15.taskmanager.objects.SharedPrefs
import java.util.*

class RecyclerAdapter(
    private val taskList: MutableList<Task>,
    private val viewModel: MainViewModel
) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        return ViewHolder(
            AdapterTaskListBinding.inflate(
                parent.layoutLayoutInflater(),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        val task = taskList[position]
        holder.binding.apply {
            tvTitle.text = task.name
            tvStart.text =
                SimpleDateFormat("hh:mm a", Locale.getDefault()).format(task.startingTime)
            tvEnd.text = SimpleDateFormat("hh:mm a", Locale.getDefault()).format(task.endingTime)
            tvDescription.text = task.description
            tvDel.setOnClickListener {
                // TODO: Does not remove until page refresh
                SharedPrefs.remove(task)
            }
            // TODO: Add back change live date
            /*root.setOnClickListener {
                viewModel.changeLiveDate.value = task;
            }*/
        }

    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    inner class ViewHolder(val binding: AdapterTaskListBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    private fun View.layoutLayoutInflater(): LayoutInflater {
        return LayoutInflater.from(this.context);
    }
}