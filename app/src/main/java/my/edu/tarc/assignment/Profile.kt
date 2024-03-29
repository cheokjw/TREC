package my.edu.tarc.assignment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import my.edu.tarc.assignment.databinding.FragmentProfileBinding
import kotlin.math.E

class Profile : Fragment(), View.OnClickListener {

    lateinit var database: FirebaseDatabase
    lateinit var databaseReference: DatabaseReference
    private lateinit var bindingProfile:FragmentProfileBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database = FirebaseDatabase.getInstance()
        databaseReference = database.getReference().child("user")
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        bindingProfile = FragmentProfileBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return bindingProfile.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getSess()
        displayinfo()
        bindingProfile.cvAvatar.setOnClickListener(this)
        bindingProfile.buttonEditInfo.setOnClickListener(this)
        bindingProfile.buttonLogout.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.cvAvatar -> {
                val showPopUpAvatar = Avatar()
                showPopUpAvatar.show((activity as AppCompatActivity).supportFragmentManager, "showPopUp")
            }

            R.id.buttonEditInfo -> {
                val showPopUpEdit = EditProfile()
                showPopUpEdit.show((activity as AppCompatActivity).supportFragmentManager, "showPopUp")
            }

            R.id.buttonLogout -> {
                alertLogout()
            }
        }
    }

    private fun displayinfo(){
        getData(bindingProfile.textViewUsername.text.toString())
    }


    private fun getData(username: String) {
        //get fullname
        databaseReference.child(username).child("fullname").get().addOnSuccessListener {
            val full_name = it.getValue(String::class.java)
            bindingProfile.textViewFullName.text = full_name
        }
        //get email
        databaseReference.child(username).child("email").get().addOnSuccessListener {
            val email = it.getValue(String::class.java)
            bindingProfile.textViewEmail.text = email
        }
        //get phone
        databaseReference.child(username).child("phone").get().addOnSuccessListener {
            val phone = it.getValue(String::class.java)
            bindingProfile.textViewContact.text = phone
        }
        //get address
        databaseReference.child(username).child("address").get().addOnSuccessListener {
            val address = it.getValue(String::class.java)
            bindingProfile.textViewAddress.text = address
        }
        //get treecoin
        databaseReference.child(username).child("treeCoin").get().addOnSuccessListener {
            val treecoin = it.value.toString().toInt()
            bindingProfile.textViewTreeCoin.text = treecoin.toString()
        }
        //get gamecoin
        databaseReference.child(username).child("gameCoin").get().addOnSuccessListener {
            val gamecoin = it.value.toString().toInt()
            bindingProfile.textViewGameCoin.text = gamecoin.toString()
        }
        //get username
        databaseReference.child(username).child("username").get().addOnSuccessListener {
            val nname = it.value.toString()
            bindingProfile.textViewUserName.text = nname
        }
        //get image
        databaseReference.child(username).child("imgProfile").get().addOnSuccessListener {
            val number = it.value.toString().toInt()
            setAvatar(number)
        }
    }

    private fun getSess() {
            val preferences =
                requireContext().getSharedPreferences("sess_store", Context.MODE_PRIVATE)
            val sess_username = preferences.getString("username", "")
            if (sess_username != "") {
                bindingProfile.textViewUsername.text = sess_username
            } else {
                Toast.makeText(activity, "failed to retrieve username", Toast.LENGTH_SHORT).show()
            }
        }

    private fun setAvatar(n : Int){
        when(n){
            1 -> bindingProfile.imageViewAvatar.setImageResource(R.drawable.avatar1)
            2 -> bindingProfile.imageViewAvatar.setImageResource(R.drawable.avatar2)
            3 -> bindingProfile.imageViewAvatar.setImageResource(R.drawable.avatar3)
            4 -> bindingProfile.imageViewAvatar.setImageResource(R.drawable.avatar4)
            5 -> bindingProfile.imageViewAvatar.setImageResource(R.drawable.avatar5)
            6 -> bindingProfile.imageViewAvatar.setImageResource(R.drawable.avatar6)
        }
    }

    private fun alertLogout(){
        var logout = android.app.AlertDialog.Builder(activity)
            .setTitle("Logout")
            .setMessage("Are you sure you want to logout?")
            .setPositiveButton("Yes"){_,_ ->
                var activityfunction = activity as MainActivity
                Toast.makeText(activity, "Logging Out ...", Toast.LENGTH_SHORT).show()
                activityfunction.logout()
            }
            .setNegativeButton("No"){_,_ ->
            }
        val alertDialog = logout.create()
        alertDialog.show()
    }


}


