 package my.edu.tarc.assignment

import android.os.Bundle
import android.util.Patterns.EMAIL_ADDRESS
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import my.edu.tarc.assignment.databinding.FragmentLoginBinding
import java.util.regex.Pattern


 class Login : Fragment(), View.OnClickListener {

     lateinit var database: FirebaseDatabase
     lateinit var databaseReference: DatabaseReference

     private lateinit var bindinglogin:FragmentLoginBinding




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        database = FirebaseDatabase.getInstance()
        databaseReference = database.getReference().child("user")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val v = inflater.inflate(R.layout.fragment_login, container, false)



        bindinglogin = FragmentLoginBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return bindinglogin.root


    }

     override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
         super.onViewCreated(view, savedInstanceState)

         bindinglogin.textViewForgotPw.setOnClickListener(this)
         bindinglogin.buttonSignIn.setOnClickListener(this)
         bindinglogin.textViewSignup.setOnClickListener(this)


     }

     override fun onClick(v: View) {
         when(v.id){
             //forgot pw
             R.id.textViewForgotPw -> {
                 val showPopUp = forgotpassw()
                 showPopUp.show((activity as AppCompatActivity).supportFragmentManager, "showPopUp")
             }

             R.id.buttonSignIn -> {

             }


             //create acc
             R.id.textViewSignup -> {
                 val SignUpFragment = Signup()
                 val transaction: FragmentTransaction = parentFragmentManager!!.beginTransaction()
                 transaction.replace(R.id.frameLayout_login,SignUpFragment)
                 transaction.commit()
             }

         }
     }

     /*private fun isValidEmail(email:String): Boolean {
         val pattern = Pattern.EMAIL_ADDRESS
     }

     private fun login() {
         val username = bindinglogin.editTextUsername.text.toString()
         val password = bindinglogin.editTextPassword.text.toString()

         if(username.isEmpty() || password.isEmpty()){
             Toast.makeText(activity, "Empty Field", Toast.LENGTH_SHORT).show()
         }else{
             if ()
         }
     }


     private fun isEmailExist(email:String) {
         databaseReference.addValueEventListener(object : ValueEventListener){
             override fun onDataChange(dataSnapshot: DataSnapshot) {

             }
         }
     }*/




}