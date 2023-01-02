package my.edu.tarc.assignment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import my.edu.tarc.assignment.databinding.FragmentEditprofileBinding
import java.util.Objects

class EditProfile : DialogFragment() {

    lateinit var database: FirebaseDatabase
    lateinit var databaseReference: DatabaseReference
    private lateinit var bindingeditinfo:FragmentEditprofileBinding
    var username = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        database = FirebaseDatabase.getInstance()
        databaseReference = database.getReference().child("user")
        bindingeditinfo = FragmentEditprofileBinding.inflate(layoutInflater)
        return bindingeditinfo.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val profile = Profile()
        super.onViewCreated(view, savedInstanceState)
        getSess()
        displayinfo()
        bindingeditinfo.buttonEdit.setOnClickListener{
            updateInfo()
            Toast.makeText(activity,"User info updated!",Toast.LENGTH_SHORT).show()
            dismiss()
            refresh()
        }
        bindingeditinfo.buttonCancel.setOnClickListener{
            dismiss()
        }
    }


    private fun displayinfo(){
        getData(username)
    }


    private fun getSess(){
        val preferences = requireContext().getSharedPreferences("sess_store", Context.MODE_PRIVATE)
        val sess_username = preferences.getString("username", "")
        if (sess_username != ""){
            username = sess_username.toString()
        } else {
            Toast.makeText(activity,"failed to retrieve username", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getData(username: String) {
        //get fullname
        databaseReference.child(username).child("fullname").get().addOnSuccessListener {
            val full_name = it.getValue(String::class.java)
            bindingeditinfo.editTextFullName.setText(full_name)}
        //get email
        databaseReference.child(username).child("email").get().addOnSuccessListener {
            val email = it.getValue(String::class.java)
            bindingeditinfo.editTextEmail.setText(email)}
        //get phone
        databaseReference.child(username).child("phone").get().addOnSuccessListener {
            val phone = it.getValue(String::class.java)
            bindingeditinfo.editTextPhone.setText(phone)}
        //get address
        databaseReference.child(username).child("address").get().addOnSuccessListener {
            val address = it.getValue(String::class.java)
            bindingeditinfo.editTextAddress.setText(address)}
    }

    private fun updateInfo(){
        val full_name = bindingeditinfo.editTextFullName.text.toString()
        val email = bindingeditinfo.editTextEmail.text.toString()
        val phone = bindingeditinfo.editTextPhone.text.toString()
        val address = bindingeditinfo.editTextAddress.text.toString()
        val update = HashMap<String, Any>()
        update["fullname"] = full_name
        update["email"] = email
        update["phone"] = phone
        update["address"] = address
        databaseReference.child(username).updateChildren(update)
    }

    //used at Avatar.kt & editprofile.kt
    fun refresh() {
        val profile = activity as MainActivity
        profile.replaceFragment(Profile())
    }

}