/** This is the add task fragment
 * @author Ichiro Banskota
 */


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


class AddTask : Fragment() {
    private var _binding: FragmentAddTaskBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding =  FragmentAddTaskBinding.inflate(inflater, container, false)


        binding.setStartingTime.setOnClickListener{
            openTimePickerForStartingTime()
        }

        binding.endTime.setOnClickListener{
            openTimePickerForEndingTime()
        }

        binding.submit.setOnClickListener{
            val startingTimeText = binding.setStartingTime.text.toString()
            val endingTimeText = binding.endTime.text.toString()
            val titleText = binding.titleOfTaskInput.text.toString()
            val titleInfo = binding.taskInfoText.text.toString()
            var addTaskInfo: Boolean = false
            var addTaskTime: Boolean = false


            println("$startingTimeText  $endingTimeText $titleText $titleInfo")

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
                    SharedPrefs.add(task);

                    val snackbar = Snackbar.make(binding.root, "Task has been added", Snackbar.LENGTH_SHORT)
                    snackbar.show()
                    parentFragmentManager.beginTransaction().replace(R.id.frame_layout, Home()).commit()
                }
            }

        }

        return binding.root
    }

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

    override fun onDestroyView(){
        super.onDestroyView()
        _binding = null
    }

}