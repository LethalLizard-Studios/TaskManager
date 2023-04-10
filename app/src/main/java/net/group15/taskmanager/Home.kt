package net.group15.taskmanager

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import net.group15.taskmanager.data.Task
import net.group15.taskmanager.databinding.EmptyStateBinding
import net.group15.taskmanager.databinding.FragmentHomeBinding
import net.group15.taskmanager.datastore.MainViewModel
import net.group15.taskmanager.objects.SharedPrefs

class Home : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val dataList = mutableListOf<Task>()
    private val viewModel by activityViewModels<MainViewModel>()

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        val adapter = RecyclerAdapter(dataList, viewModel)
        binding.lv.adapter = adapter

        viewModel.retrieveTasks.observe(viewLifecycleOwner) {
            dataList.clear()
            SharedPrefs.loadData()
            dataList.addAll(SharedPrefs.taskList)
            adapter.notifyDataSetChanged()

            if(dataList.isEmpty()){
                binding.lv.visibility = View.GONE
                binding.emptyState.visibility = View.VISIBLE

            } else {
                binding.lv.visibility = View.VISIBLE
                binding.emptyState.visibility = View.GONE
            }

        }

        return binding.root
    }
}