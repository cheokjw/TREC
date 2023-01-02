package my.edu.tarc.assignment

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.graphics.alpha
import androidx.fragment.app.Fragment
import androidx.fragment.app.DialogFragment
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import my.edu.tarc.assignment.databinding.FragmentAvatarBinding

class Avatar : DialogFragment() {

    lateinit var database: FirebaseDatabase
    lateinit var databaseReference: DatabaseReference
    private lateinit var bindingAvatar: FragmentAvatarBinding
    var num = 0
    var username = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingAvatar = FragmentAvatarBinding.inflate(layoutInflater)
        return bindingAvatar.root
    }


    @SuppressLint("ClickableViewAccessibility", "ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        database = FirebaseDatabase.getInstance()
        databaseReference = database.getReference().child("user")
        getSess()
        getAvatar()

        //select avatar function
        bindingAvatar.imageViewAvatar1.setOnClickListener {
            imageAlpha(1)
            num = 1
        }

        bindingAvatar.imageViewAvatar2.setOnClickListener {
            imageAlpha(2)
            num = 2
        }

        bindingAvatar.imageViewAvatar3.setOnClickListener {
            imageAlpha(3)
            num = 3
        }

        bindingAvatar.imageViewAvatar4.setOnClickListener {
            imageAlpha(4)
            num = 4
        }

        bindingAvatar.imageViewAvatar5.setOnClickListener {
            imageAlpha(5)
            num = 5
        }

        bindingAvatar.imageViewAvatar6.setOnClickListener {
            imageAlpha(6)
            num = 6
        }

        bindingAvatar.buttonSelectAvatar.setOnClickListener{
            val profile = Profile()
            updateAvatar()
            Toast.makeText(activity, "Avatar Changed", Toast.LENGTH_SHORT).show()
            dismiss()
            refresh()
        }

        bindingAvatar.imageButtonQuit.setOnClickListener {
            dismiss()
        }
    }

    //set image alpha
    private fun imageAlpha(n: Int){
        bindingAvatar.imageViewAvatar1.alpha = 0.5F
        bindingAvatar.imageViewAvatar2.alpha = 0.5F
        bindingAvatar.imageViewAvatar3.alpha = 0.5F
        bindingAvatar.imageViewAvatar4.alpha = 0.5F
        bindingAvatar.imageViewAvatar5.alpha = 0.5F
        bindingAvatar.imageViewAvatar6.alpha = 0.5F
        when (n){
            1 -> bindingAvatar.imageViewAvatar1.alpha = 1F
            2 -> bindingAvatar.imageViewAvatar2.alpha = 1F
            3 -> bindingAvatar.imageViewAvatar3.alpha = 1F
            4 -> bindingAvatar.imageViewAvatar4.alpha = 1F
            5 -> bindingAvatar.imageViewAvatar5.alpha = 1F
            6 -> bindingAvatar.imageViewAvatar6.alpha = 1F
        }
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


    //update image to firebase
    private fun updateAvatar(){
        val update = HashMap<String, Any>()
        update["imgProfile"] = num
        databaseReference.child(username).updateChildren(update)
    }

    private fun getAvatar() {
        //get fullname
        databaseReference.child(username).child("imgProfile").get().addOnSuccessListener {
            val number = it.value.toString().toInt()
            if (number == null){
                Toast.makeText(activity,"Database issue",Toast.LENGTH_SHORT).show()
            }else if(number == 0) {
                Toast.makeText(activity,"No avatar saved",Toast.LENGTH_SHORT).show()
            }else{
                imageAlpha(number)
            }
        }
    }

    //used at Avatar.kt & editprofile.kt
    fun refresh() {
        val profile = activity as MainActivity
        profile.replaceFragment(Profile())
    }

}