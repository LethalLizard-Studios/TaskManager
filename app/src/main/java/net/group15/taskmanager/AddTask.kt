package net.group15.taskmanager

import android.icu.text.SimpleDateFormat
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import net.group15.taskmanager.databinding.FragmentAddTaskBinding
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.util.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


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