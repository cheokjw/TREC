package my.edu.tarc.assignment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import my.edu.tarc.assignment.databinding.FragmentPopUpQ2Binding

class popUpQ2 : DialogFragment() {

    private lateinit var bindingPopUpQ2: FragmentPopUpQ2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingPopUpQ2 = FragmentPopUpQ2Binding.inflate(layoutInflater)
        // Inflate the layout for this fragment

        return bindingPopUpQ2.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindingPopUpQ2.btnDismissQ2.setOnClickListener{
            dismiss()
        }

    }
}