package my.edu.tarc.assignment

import android.app.Dialog
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import my.edu.tarc.assignment.databinding.FragmentAvatarBinding
import my.edu.tarc.assignment.databinding.FragmentForgotpasswBinding


class forgotpassw : DialogFragment() {

    lateinit var database: FirebaseDatabase
    lateinit var databaseReference: DatabaseReference
    private lateinit var auth: FirebaseAuth;
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
        database = FirebaseDatabase.getInstance()
        databaseReference = database.getReference().child("user")
        auth = Firebase.auth

        bindingForgotpasswBinding.buttonPwCancel.setOnClickListener{
            dismiss()
        }

        bindingForgotpasswBinding.buttonPwReset.setOnClickListener {
            checkEmail()
        }
    }




    //compare input email with realtime database email
    private fun checkEmail() {
        val email = bindingForgotpasswBinding.EditTextResetPwEmail.text.toString()
        val emailQuery = databaseReference.orderByChild("email").equalTo(email)
        emailQuery.addListenerForSingleValueEvent(object: ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot){
                // dataSnapshot contains the email value
                if (dataSnapshot.exists()){
                    //Email is present
                    sendResetPw()
                    Toast.makeText(activity,"Reset password link sent to email",Toast.LENGTH_SHORT).show()
                    Log.d(TAG, "Email match")
                    dismiss()
                }else{
                    //Email is not present
                    Toast.makeText(requireContext(),"Email not match",Toast.LENGTH_SHORT).show()
                    Log.e(TAG, "Email not present")
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {
                // Handle error
                Log.e(TAG,"Database err")
            }
        })

    }


    private fun sendResetPw(){
        val email = bindingForgotpasswBinding.EditTextResetPwEmail.text.toString()
        auth.sendPasswordResetEmail(email).addOnCompleteListener {task ->
            if(task.isSuccessful){
                Log.d(TAG,"Reset Password sent to $email")
            }else{
                Log.e(TAG, "Error sending password reset email", task.exception)
            }
        }
    }



}