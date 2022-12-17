 package my.edu.tarc.assignment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import my.edu.tarc.assignment.databinding.FragmentLoginBinding


 class Login : Fragment(), View.OnClickListener {

     private lateinit var bindinglogin:FragmentLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        bindinglogin = FragmentLoginBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return bindinglogin.root
    }

     override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
         super.onViewCreated(view, savedInstanceState)

         bindinglogin.textViewForgotPw.setOnClickListener(this)
     }

     override fun onClick(v: View) {
         when(v.id){
             R.id.textViewForgotPw -> {
                 val showPopUp = forgotpassw()
                 showPopUp.show((activity as AppCompatActivity).supportFragmentManager, "showPopUp")
             }
         }
     }

}