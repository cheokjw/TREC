package my.edu.tarc.assignment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import my.edu.tarc.assignment.Regis.Signup_regis
import my.edu.tarc.assignment.databinding.FragmentSignupBinding

class Signup : Fragment() {

    private lateinit var bindingSignup: FragmentSignupBinding
    private lateinit var database: FirebaseDatabase
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
                if(pass == repass){

                    var idSign = databaseReference.push().key
                    var regis = Signup_regis(username, fullName, phone, address, email, pass, idSign!!, treeCoin = 0, gameCoin = 0, checkin = 0)

                    //Here Data Inserted
                    databaseReference.child(username).setValue(regis)
                    Toast.makeText(activity, "Signup Successful", Toast.LENGTH_SHORT).show()
                }
            }

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