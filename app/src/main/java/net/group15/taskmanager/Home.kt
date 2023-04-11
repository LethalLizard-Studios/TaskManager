package net.group15.taskmanager
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import net.group15.taskmanager.data.Task
import net.group15.taskmanager.databinding.FragmentHomeBinding
import net.group15.taskmanager.datastore.MainViewModel
import net.group15.taskmanager.objects.SharedPrefs
import net.group15.taskmanager.objects.SharedPrefs.taskList
import net.group15.taskmanager.objects.SwipeToDeleteCallBack
/**
 * This is the home fragement this deals with the home page
 * and how to display the tasks in a recycler view manner
 * This fragment deals with adding a task
 * @authors Ichiro Banskota & Hanwen Shi & Leland Carter
 * @version 1.0
 * @since 2023-04-11
 */
class Home : Fragment() {
    // vars
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val dataList = mutableListOf<Task>()
    private val viewModel by activityViewModels<MainViewModel>()
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // main view binding
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        val adapter = RecyclerAdapter(dataList, viewModel)
        binding.lv.adapter = adapter
        // this is where the tasks are loaded from shared preferences
        viewModel.retrieveTasks.observe(viewLifecycleOwner) {
            dataList.clear()
            SharedPrefs.loadData()
            dataList.addAll(SharedPrefs.taskList)
            adapter.notifyDataSetChanged()
            // If the data list is empty the empty state is shown
            if(dataList.isEmpty()){
                binding.lv.visibility = View.GONE
                binding.emptyState.visibility = View.VISIBLE

            } else {
                binding.lv.visibility = View.VISIBLE
                binding.emptyState.visibility = View.GONE
            }
            // this is where swipeToDelete is called and handles the deleting based on swipe
            val swipeToDeleteCallBack = object :  SwipeToDeleteCallBack() {
                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    SharedPrefs.remove(viewHolder.adapterPosition)
                }
            }
            val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallBack)
            itemTouchHelper.attachToRecyclerView(binding.lv);

        }
        return binding.root
    }
}