package my.edu.tarc.assignment

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import my.edu.tarc.assignment.Regis.Signup_regis
import my.edu.tarc.assignment.databinding.FragmentSignupBinding
import java.util.regex.Pattern

class Signup : Fragment() {

    private lateinit var bindingSignup: FragmentSignupBinding
    private lateinit var database: FirebaseDatabase
    private lateinit var auth: FirebaseAuth;
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bindingSignup = FragmentSignupBinding.inflate(inflater)

        database = FirebaseDatabase.getInstance()
        databaseReference = database.getReference().child("user")
        auth = FirebaseAuth.getInstance()
        val username = bindingSignup.editTextUsernameReg.text.toString()


        bindingSignup.buttonRegister.setOnClickListener(){
            //TODO: Add registered alert
            val username = bindingSignup.editTextUsernameReg.text.toString()
            val fullName = bindingSignup.editTextFullNameReg.text.toString()
            val phone = bindingSignup.editTextPhoneReg.text.toString()
            val address = bindingSignup.editTextAddressReg.text.toString()
            val email = bindingSignup.editTextEmailReg.text.toString()
            val pass = bindingSignup.editTextEnterPassReg.text.toString()
            val repass = bindingSignup.editTextReEnterPassReg.text.toString()

            if(username.isNotEmpty() && fullName.isNotEmpty() && phone.isNotEmpty() && address.isNotEmpty() && email.isNotEmpty() && pass.isNotEmpty() && repass.isNotEmpty()){
                if(pass == repass && pass.length > 6){
                    if(pass.length > 6){
                    val isValid = isValidEmail(email)
                        if(isValid) {

                            var idSign = databaseReference.push().key
                            var regis = Signup_regis(
                                username,
                                fullName,
                                phone,
                                address,
                                email,
                                pass,
                                idSign!!,
                                treeCoin = 0,
                                gameCoin = 0,
                                checkin = 0,
                                treeProgress = 0,
                                spray_quantity = 0,
                                green_quantity = 0,
                                golden_quantity = 0,
                                quizCorrect = 0.0,
                                imgProfile = 0,
                                checkInCounter = 0,
                                currentPosition = 1,
                                checkInDate = 0,
                            )

                            //Here Data Inserted
                            addToAuth()
                            databaseReference.child(username).setValue(regis)
                            Toast.makeText(activity, "Signup Successful", Toast.LENGTH_SHORT).show()
                            returnlogin()
                        }else{
                            Toast.makeText(activity, "Invalid Email", Toast.LENGTH_SHORT).show()
                        }
                    }else{
                        Toast.makeText(activity, "Password is Less than 6 Characters", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(activity, "Password Does not Match", Toast.LENGTH_SHORT).show()
                }
            }
        }

        bindingSignup.textViewLogin.setOnClickListener() {
//            val LogInFragment = Login()
//            val transaction: FragmentTransaction = parentFragmentManager!!.beginTransaction()
//            transaction.replace(R.id.frameLayout_login, LogInFragment)
//            transaction.commit()
            returnlogin()
        }
        return bindingSignup.root
    }


//    fun onClick(v: View) {
//        when (v.id) {
//
//
//            //create acc
//            R.id.textViewSignup -> {
//                val LogInFragment = Login()
//                val transaction: FragmentTransaction = parentFragmentManager!!.beginTransaction()
//                transaction.replace(R.id.frameLayout_login, LogInFragment)
//                transaction.commit()
//            }
//
//        }
//    }

    private fun returnlogin(){

        val LoginFragment = Login()
        val transaction: FragmentTransaction = parentFragmentManager!!.beginTransaction()
        transaction.replace(R.id.frameLayout_login, LoginFragment)
        transaction.commit()
    }


    private fun addToAuth(){
        val email = bindingSignup.editTextEmailReg.text.toString()
        val password = bindingSignup.editTextEnterPassReg.text.toString()
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if(task.isSuccessful){
                Log.d(TAG, "Added to FirebaseAuth - forgt pw purpose")
            } else{
                //debugging issue found
                Log.e(TAG,"Add to FirebaseAuth (failed)", task.exception)
            }
        }
    }


    fun isValidEmail(email: String): Boolean {
        val pattern = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
        )
        val matcher = pattern.matcher(email)
        return matcher.matches()
    }



}