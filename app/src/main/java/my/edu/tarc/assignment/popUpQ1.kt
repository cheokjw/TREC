package my.edu.tarc.assignment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import my.edu.tarc.assignment.databinding.FragmentPopUpQ1Binding


class popUpQ1 : DialogFragment() {
    private lateinit var bindingPopQ1: FragmentPopUpQ1Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingPopQ1 = FragmentPopUpQ1Binding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return bindingPopQ1.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindingPopQ1.btnDismissQ1.setOnClickListener{
            dismiss()
        }
    }

}