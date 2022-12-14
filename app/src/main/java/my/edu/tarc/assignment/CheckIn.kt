package my.edu.tarc.assignment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import my.edu.tarc.assignment.databinding.FragmentCheckInBinding

/**
 * A simple [Fragment] subclass.
 * Use the [CheckIn.newInstance] factory method to
 * create an instance of this fragment.
 */
class CheckIn : Fragment() {
    private lateinit var bindingCheckIn: FragmentCheckInBinding
    private lateinit var builder: AlertDialog.Builder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingCheckIn = FragmentCheckInBinding.inflate(inflater)

        bindingCheckIn.buttonCheckIn.setOnClickListener {
            bindingCheckIn.notCheckedInStatus.setImageResource(R.drawable.checkedin)
        }
        return bindingCheckIn.root
    }

    //TODO: implement pop up scrollview for rewards history


}