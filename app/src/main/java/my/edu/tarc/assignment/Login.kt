 package my.edu.tarc.assignment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.google.firebase.database.*
import my.edu.tarc.assignment.databinding.FragmentLoginBinding
import android.content.SharedPreferences


 class Login : Fragment(), View.OnClickListener {

     lateinit var database: FirebaseDatabase
     lateinit var databaseReference: DatabaseReference
     private lateinit var bindinglogin: FragmentLoginBinding



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
         when (v.id) {

             //forgot pw
             R.id.textViewForgotPw -> {
                 val showPopUp = forgotpassw()
                 showPopUp.show((activity as AppCompatActivity).supportFragmentManager, "showPopUp")
             }

             //login btn function
             R.id.buttonSignIn -> {
                login()
             }


             //create acc
             R.id.textViewSignup -> {
                 val SignUpFragment = Signup()
                 val transaction: FragmentTransaction = parentFragmentManager!!.beginTransaction()
                 transaction.replace(R.id.frameLayout_login, SignUpFragment)
                 transaction.commit()
             }

         }
     }


     private fun login() {
        val username = bindinglogin.editTextUsername.text.toString()
        val password = bindinglogin.editTextPassword.text.toString()

         if (username.isEmpty() || password.isEmpty()) {
             Toast.makeText(activity, "Empty Field", Toast.LENGTH_SHORT).show()
         } else {
             CheckUser(username)
         }
     }

     private fun CheckUser(username: String){
         val activityfunction = activity as LoginActivity

         databaseReference.child(username).get().addOnSuccessListener {
             if(it.exists()){
                 val veripw = it.child("pass").value
                 if(bindinglogin.editTextPassword.text.toString() == veripw){
                     //toast msg
                     Toast.makeText(activity, "Login Successful", Toast.LENGTH_SHORT).show()

                     //create session
                     startSess(bindinglogin.editTextUsername.text.toString())

                     //start mainactivity
                     activityfunction.access()

                 }else{
                     Toast.makeText(activity, "Password Error", Toast.LENGTH_SHORT).show()
                 }
             }else{
                 Toast.makeText(activity, "Username does not exists", Toast.LENGTH_SHORT).show()
             }

         }.addOnFailureListener{
             Toast.makeText(activity, "Failed to get username", Toast.LENGTH_SHORT).show()
         }
     }

     private fun startSess(username: String){
         val preferences = requireContext().getSharedPreferences("sess_store", Context.MODE_PRIVATE)
         val editor = preferences.edit()
         editor.putString("username", username)
         editor.apply()

     }




 }


