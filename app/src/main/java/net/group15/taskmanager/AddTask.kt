package net.group15.taskmanager
import android.content.res.ColorStateList
import android.graphics.Color
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import net.group15.taskmanager.databinding.FragmentAddTaskBinding
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import net.group15.taskmanager.data.Task
import net.group15.taskmanager.objects.SharedPrefs
import java.util.*
/**
 * This is the add task fragment
 * This fragment deals with adding a task
 * @authors Ichiro Banskota & Carter Leland
 * @version 1.0
 * @since 2023-04-11
 */
class AddTask : Fragment() {
    private var _binding: FragmentAddTaskBinding? = null
    private val binding get() = _binding!!
    // This handles the view of the add task fragment
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // main binding view
        _binding =  FragmentAddTaskBinding.inflate(inflater, container, false)
        // onclick listener for setting the starting time
        // this opens a clock view that is a clean way for users to enter a time
        binding.setStartingTime.setOnClickListener{
            openTimePickerForStartingTime()
        }
        // onclick listener for setting the end time
        // this opens a clock view that is a clean way for users to enter a time
        binding.endTime.setOnClickListener{
            openTimePickerForEndingTime()
        }
        // on submit Onclicklistener that handles what happens when users
        // enter all there task information
        binding.submit.setOnClickListener{
            // vars
            val startingTimeText = binding.setStartingTime.text.toString()
            val endingTimeText = binding.endTime.text.toString()
            val titleText = binding.titleOfTaskInput.text.toString()
            val titleInfo = binding.taskInfoText.text.toString()
            var addTaskInfo = false
            var addTaskTime = false
            // This is error handling for user input
            // Error 1. If no data is entered
            // Error 2. If time does not make sense
            // Case 1. time is the same
            // Case 2. start time is after end time
            // Displays error messages
            if(titleInfo=="" || titleText==""){
                binding.taskInfoTextInputLayout.helperText = "*task title and description required"
                binding.taskInfoTextInputLayout.setHelperTextColor(ColorStateList.valueOf(Color.RED))
            } else {
                binding.taskInfoTextInputLayout.helperText = ""
                addTaskInfo = true
            }

            if(startingTimeText == "Start Time" || endingTimeText == "End Time"){
                binding.titleTextInputLayout.helperText = "*Start time and End time required"
                binding.titleTextInputLayout.setHelperTextColor(ColorStateList.valueOf(Color.RED))
            } else {
                val sdf = SimpleDateFormat("hh:mm a", Locale.getDefault())
                val timeStart = sdf.parse(startingTimeText)
                val timeEnd = sdf.parse(endingTimeText)

                if(timeStart.after(timeEnd)){
                    binding.titleTextInputLayout.helperText = "*Start time cannot be after end time"
                    binding.titleTextInputLayout.setHelperTextColor(ColorStateList.valueOf(Color.RED))
                } else if (timeStart.equals(timeEnd)){
                    binding.titleTextInputLayout.helperText = "*Start time and end time cannot be the same"
                    binding.titleTextInputLayout.setHelperTextColor(ColorStateList.valueOf(Color.RED))
                }
                else {
                    binding.titleTextInputLayout.helperText = ""
                    addTaskTime = true
                }

                if (addTaskInfo && addTaskTime){
                    // Create a new task with the entered data
                    val task = Task.Builder()
                        .name(titleText)
                        .description(titleInfo)
                        .startTime(timeStart)
                        .endTime(timeEnd)
                        .build()
                    // Add the built task to our singleton's list
                    SharedPrefs.add(task)

                    val snackbar = Snackbar.make(binding.root, "Task has been added", Snackbar.LENGTH_SHORT)
                    snackbar.show()
                    parentFragmentManager.beginTransaction().replace(R.id.frame_layout, Home()).commit()
                }
            }
        }
        return binding.root
    }
    // Methods for handling clock view for starting time
    // Check docs for more information https://www.geeksforgeeks.org/timepicker-in-kotlin/
    private fun openTimePickerForStartingTime(){

        val picker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_12H)
            .setHour(12)
            .setMinute(0)
            .setTitleText("Start Time")
            .build()
        picker.show(childFragmentManager, "Tag")

        picker.addOnPositiveButtonClickListener{
            val hour = picker.hour
            val min = picker.minute
            val convertTo12Hour = SimpleDateFormat("hh:mm a", Locale.getDefault())
                .format(SimpleDateFormat("HH:mm", Locale.getDefault()).parse("$hour:$min"))
            binding.setStartingTime.text = convertTo12Hour
        }
    }
    // Methods for handling the clock view for ending time
    // Check docs for more information https://www.geeksforgeeks.org/timepicker-in-kotlin/
    private fun openTimePickerForEndingTime() {
        val picker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_12H)
            .setHour(12)
            .setMinute(0)
            .setTitleText("Start Time")
            .build()
        picker.show(childFragmentManager, "Tag")

        picker.addOnPositiveButtonClickListener{
            val hour = picker.hour
            val min = picker.minute
            val convertTo12Hour = SimpleDateFormat("hh:mm a", Locale.getDefault())
                .format(SimpleDateFormat("HH:mm", Locale.getDefault()).parse("$hour:$min"))
            binding.endTime.text = convertTo12Hour
        }
    }
    // What happens when the view is destroyed
    override fun onDestroyView(){
        super.onDestroyView()
        _binding = null
    }
}