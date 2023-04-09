package net.group15.taskmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import net.group15.taskmanager.databinding.ActivityMainBinding
import net.group15.taskmanager.datastore.DataStoreManager
import net.group15.taskmanager.datastore.MainViewModel
import net.group15.taskmanager.objects.SharedPrefs


// Main task manager screen
// All tasks will be viewed here
class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var dataStoreManager: DataStoreManager
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction().replace(R.id.frame_layout, Home()).commit()

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        dataStoreManager = DataStoreManager(this)

        SharedPrefs.initialize(viewModel, dataStoreManager, this)

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home_nav_button -> supportFragmentManager.beginTransaction().replace(R.id.frame_layout, Home()).commit()
                R.id.add_task_nav_button -> supportFragmentManager.beginTransaction().replace(R.id.frame_layout, AddTask()).commit()
                R.id.about_us_nav_button -> supportFragmentManager.beginTransaction().replace(R.id.frame_layout, AboutUs()).commit()
                else -> {
                }
            }
            true
        }
    }

}