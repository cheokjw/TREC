package my.edu.tarc.assignment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import my.edu.tarc.assignment.databinding.FragmentFAQBinding

// Need to use View interface to implement onClick function
class FAQ : Fragment(), View.OnClickListener {
    lateinit var database: FirebaseDatabase
    lateinit var databaseReference: DatabaseReference
    private lateinit var bindingFAQ: FragmentFAQBinding
    var name = ""
    var username = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        getSess()
        database = FirebaseDatabase.getInstance()
        databaseReference = database.getReference().child("user").child(username)

        //retrieve name
        databaseReference.child("fullname").get().addOnSuccessListener {
            name = it.value.toString()
        }.addOnFailureListener {
            Log.e("firebase", "Error getting data", it)
        }
        bindingFAQ = FragmentFAQBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return bindingFAQ.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindingFAQ.cvQ1.setOnClickListener(this)
        bindingFAQ.cvQ2.setOnClickListener(this)
        bindingFAQ.cvQ3.setOnClickListener(this)
        bindingFAQ.cvQ4.setOnClickListener(this)

        bindingFAQ.btnEmail.setOnClickListener {
            val emailIntent = Intent(Intent.ACTION_SEND)
            emailIntent.type = "text/plain"
            emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf("treecrds2s1g1@gmail.com"))
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Know More About TREC.")
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Hello I am "+name +",\nI want to know")
            startActivity(Intent.createChooser(emailIntent, "Send email using:"))
        }

    }

    override fun onClick(v:View){

        when(v.id){
            R.id.cvQ1 -> {
                val showPopUp = popUpQ1()
                showPopUp.show((activity as AppCompatActivity).supportFragmentManager, "showPopUp")
            }

            R.id.cvQ2 -> {
                val showPopUp = popUpQ2()
                showPopUp.show((activity as AppCompatActivity).supportFragmentManager, "showPopUp")
            }

            R.id.cvQ3 -> {
                val showPopUp = popUpQ3()
                showPopUp.show((activity as AppCompatActivity).supportFragmentManager, "showPopUp")
            }

            R.id.cvQ4 -> {
                val showPopUp = popUpQ4()
                showPopUp.show((activity as AppCompatActivity).supportFragmentManager, "showPopUp")
            }
        }

    }
    private fun getSess(){
        val preferences = requireContext().getSharedPreferences("sess_store", Context.MODE_PRIVATE)
        val sess_username = preferences.getString("username", "")
        if (sess_username != ""){
            username = sess_username.toString()
        }else{
            Toast.makeText(activity, "failed to retrieve username", Toast.LENGTH_SHORT).show()
        }
    }

}


