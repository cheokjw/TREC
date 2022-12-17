
package my.edu.tarc.assignment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import my.edu.tarc.assignment.databinding.FragmentFAQBinding
import my.edu.tarc.assignment.databinding.FragmentPopUpQ3Binding


class popUpQ3 : DialogFragment() {

    private lateinit var bindingPopUpQ3: FragmentPopUpQ3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingPopUpQ3 = FragmentPopUpQ3Binding.inflate(layoutInflater)

        // Inflate the layout for this fragment
        return bindingPopUpQ3.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindingPopUpQ3.btnDismissQ3.setOnClickListener{
            dismiss()
        }
    }
}