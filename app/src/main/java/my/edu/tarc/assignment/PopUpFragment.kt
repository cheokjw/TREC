package my.edu.tarc.assignment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import my.edu.tarc.assignment.databinding.FragmentPopUpBinding


// This class is for popup view in FAQ page
class PopUpFragment : DialogFragment() {

    private lateinit var bindingPopUp:FragmentPopUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingPopUp = FragmentPopUpBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return bindingPopUp.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindingPopUp.btnDismiss.setOnClickListener{
            dismiss()
        }
    }
}