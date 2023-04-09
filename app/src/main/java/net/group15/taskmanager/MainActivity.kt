package net.group15.taskmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import net.group15.taskmanager.databinding.ActivityMainBinding
import net.group15.taskmanager.datastore.DataStoreManager
import net.group15.taskmanager.datastore.MainViewModel
import net.group15.taskmanager.objects.SharedPrefs


// Main task manager screen
// All tasks will be viewed here
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var dataStoreManager: DataStoreManager
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().replace(R.id.frame_layout, Home()).commit()
        viewModel.changeLiveDate.observe(this) {
            it?.let {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_layout, AddTask().apply {
                        val bundle = Bundle()
                        bundle.putSerializable("task", it)
                        arguments = bundle
                    }).commit()
            }
        }
        dataStoreManager = DataStoreManager(this)
//        viewModel.initDataStore(application)
        SharedPrefs.initialize(viewModel, dataStoreManager, this)

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home_nav_button -> supportFragmentManager.beginTransaction().replace(
                    R.id.frame_layout,
                    Home()
                ).commit()
                R.id.add_task_nav_button -> {
                    supportFragmentManager.beginTransaction().replace(R.id.frame_layout, AddTask())
                        .commit()
                }
                R.id.about_us_nav_button -> supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_layout, AboutUs()).commit()
                else -> {
                }
            }
            true
        }
    }

}

// add a task (save for the day)
// task title (string)
// task start time (hour)
// task estimated time to complete (hour)


// add a goal screen (save for the time defined)
// goal title
// goal start time (hour)
// goal estimated time to complete (hour)
// goal duration (weeks)