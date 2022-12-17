package my.edu.tarc.assignment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import my.edu.tarc.assignment.databinding.FragmentEditprofileBinding

class EditProfile : DialogFragment() {

    private lateinit var bindingeditinfo:FragmentEditprofileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingeditinfo = FragmentEditprofileBinding.inflate(layoutInflater)
        return bindingeditinfo.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindingeditinfo.buttonCancel.setOnClickListener{
            dismiss()
        }
    }

}