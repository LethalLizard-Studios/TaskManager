package net.group15.taskmanager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import net.group15.taskmanager.databinding.ActivityMainBinding
import net.group15.taskmanager.datastore.DataStoreManager
import net.group15.taskmanager.datastore.MainViewModel
import net.group15.taskmanager.objects.SharedPrefs
/**
 * This is the main activity class that handles all the views and switching between screens
 * @author Ichiro Banskota
 * @version 1.0
 * @since 2023-04-11
 */
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

        // This is the method that handles switching between screens
        // Based on the button clicked in the bottom nav bar the view is adjusted accordly
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