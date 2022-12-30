package my.edu.tarc.assignment

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import my.edu.tarc.assignment.databinding.FragmentAvatarBinding
import my.edu.tarc.assignment.databinding.FragmentForgotpasswBinding


class forgotpassw : DialogFragment() {

    private lateinit var bindingForgotpasswBinding: FragmentForgotpasswBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingForgotpasswBinding = FragmentForgotpasswBinding.inflate(layoutInflater)
        return bindingForgotpasswBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindingForgotpasswBinding.buttonPwCancel.setOnClickListener{
            dismiss()
        }

        bindingForgotpasswBinding.buttonPwReset.setOnClickListener {
            Toast.makeText(activity, "New Password sent to Email", Toast.LENGTH_SHORT).show()
            dismiss()
        }



    }


}