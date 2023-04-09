package net.group15.taskmanager

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import net.group15.taskmanager.data.Task
import net.group15.taskmanager.databinding.FragmentAddTaskBinding
import net.group15.taskmanager.databinding.FragmentHomeBinding
import net.group15.taskmanager.datastore.MainViewModel
import net.group15.taskmanager.objects.SharedPrefs
import kotlin.properties.Delegates

class Home : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val TAG: String = "TaskListActivity";

    private val viewModel by activityViewModels<MainViewModel>()


    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        val adapter = RecyclerAdapter(SharedPrefs.taskList, viewModel)
        binding.lv.adapter = adapter

        return binding.root
    }
}