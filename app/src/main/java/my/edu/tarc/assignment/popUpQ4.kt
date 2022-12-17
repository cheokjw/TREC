package my.edu.tarc.assignment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import my.edu.tarc.assignment.databinding.FragmentPopUpQ4Binding

class popUpQ4 : DialogFragment() {

    private lateinit var bindingPopUpQ4: FragmentPopUpQ4Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        bindingPopUpQ4 = FragmentPopUpQ4Binding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return bindingPopUpQ4.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindingPopUpQ4.btnDismissQ4.setOnClickListener{
            dismiss()
        }
    }


}