package my.edu.tarc.assignment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import my.edu.tarc.assignment.databinding.FragmentCheckInBinding
import my.edu.tarc.assignment.databinding.FragmentSignupBinding

class Signup : Fragment() {

    private lateinit var bindingSignup: FragmentSignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bindingSignup = FragmentSignupBinding.inflate(inflater)

        bindingSignup.buttonRegister.setOnClickListener(){
            //TODO: Add registered alert
            replaceFragment(Login())
        }

        return bindingSignup.root
    }

    //TODO: Receive input

    private fun replaceFragment(fragment : Fragment){

        val fragmentManager = getActivity()?.supportFragmentManager
        val fragmentTransaction = fragmentManager?.beginTransaction()

        // Selecting which part of the UI should be replaced by the fragment
        // in this case its the frameLayout in activity_main.xml
        fragmentTransaction?.replace(R.id.frameLayout, fragment)
        fragmentTransaction?.commit()
    }

}