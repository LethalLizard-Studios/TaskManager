package net.group15.taskmanager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import net.group15.taskmanager.databinding.FragmentAboutUsBinding
import net.group15.taskmanager.objects.SharedPrefs
/**
 * About us fragment calls which calls a simple view
 * Not much Functionality Here
 * @author Ichiro Banskota
 * @version 1.0
 * @since 2023-04-11
 */
class AboutUs : Fragment() {
    private var _binding: FragmentAboutUsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding =  FragmentAboutUsBinding.inflate(inflater, container, false)

        binding.clear.setOnClickListener{
            SharedPrefs.reset()
        }
        return binding.root
    }
}