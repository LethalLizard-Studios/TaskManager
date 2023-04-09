package net.group15.taskmanager

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import net.group15.taskmanager.data.Task
import net.group15.taskmanager.databinding.FragmentHomeBinding
import net.group15.taskmanager.datastore.MainViewModel

class Home : Fragment() {
    private val TAG: String = "TaskListActivity";

    private val dataList = mutableListOf<Task>()
    private val viewModel by activityViewModels<MainViewModel>()

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        val adapter = RecyclerAdapter(dataList, viewModel)
        binding.lv.adapter = adapter
        viewModel.taskLiveDate.observe(viewLifecycleOwner) {
            dataList.clear()
            dataList.addAll(it)
            adapter.notifyDataSetChanged()
        }
        viewModel.getTaskData()
        return binding.root

    }

}