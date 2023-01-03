 package my.edu.tarc.assignment

import android.content.ContentValues.TAG
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
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


 class Login : Fragment(), View.OnClickListener {

     lateinit var database: FirebaseDatabase
     lateinit var databaseReference: DatabaseReference
     private lateinit var auth: FirebaseAuth
     private lateinit var bindinglogin: FragmentLoginBinding



     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         database = FirebaseDatabase.getInstance()
         databaseReference = database.getReference().child("user")
         auth = Firebase.auth

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
                 var veriEmail = Signup().isValidEmail(bindinglogin.editTextUsername.text.toString())
                 val isValid = veriEmail
                 if (isValid){
                     login()
                 }else{
                    bindinglogin.editTextUsername.error = "Please enter a correct email format"
                 }

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
             authLogin()
             //CheckUser(username)
         }
     }



     //firebase authentication login method
     private fun authLogin(){
         val email = bindinglogin.editTextUsername.text.toString()
         val password = bindinglogin.editTextPassword.text.toString()
         auth.signInWithEmailAndPassword(email,password).addOnCompleteListener { task ->
             if(task.isSuccessful){
                 //Sign in success
                 Toast.makeText(activity, "Logging In ...", Toast.LENGTH_SHORT).show()
                 emailUser()
             }else{
                 Toast.makeText(activity, "Login Failed", Toast.LENGTH_SHORT).show()
             }
         }
     }





     private fun emailUser(){
         val activityfunction = activity as LoginActivity
         val useremail = bindinglogin.editTextUsername.text.toString()
         val emailQuery = databaseReference.orderByChild("email").equalTo(useremail)

         emailQuery.addListenerForSingleValueEvent(object: ValueEventListener {
             override fun onDataChange(dataSnapshot: DataSnapshot) {
                 if (dataSnapshot.exists()) {
                     // Email is present in the database
                     for (userSnapshot in dataSnapshot.children) {
                         val userID = userSnapshot.key.toString()
                         startSess(userID)
                         activityfunction.access()
                     }
                 } else {
                     // Email is not present in the database
                     Log.e(TAG,"Error in getting userID")
                 }
             }

             override fun onCancelled(databaseError: DatabaseError) {
                 // Handle error
             }
         })
     }

     //realtime database login method
     private fun CheckUser(username: String){
         val activityfunction = activity as LoginActivity
         databaseReference.child(username).get().addOnSuccessListener {
             if(it.exists()){
                 val veripw = it.child("pass").value
                 if(bindinglogin.editTextPassword.text.toString() == veripw){
                     //toast msg
                     Toast.makeText(activity, "Login Successful", Toast.LENGTH_SHORT).show()

                     //create session
                     //startSess(bindinglogin.editTextUsername.text.toString())


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
         //Toast.makeText(activity,"Success",Toast.LENGTH_SHORT).show()
         Log.d(TAG,"Username session: $username")
     }




 }


