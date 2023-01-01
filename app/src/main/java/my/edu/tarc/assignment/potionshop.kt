package my.edu.tarc.assignment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import my.edu.tarc.assignment.databinding.FragmentPotionshopBinding
import my.edu.tarc.assignment.databinding.FragmentTreeBinding


class potionshop : Fragment() {
    private lateinit var bindingPotion: FragmentPotionshopBinding
    lateinit var database: FirebaseDatabase
    lateinit var databaseReference: DatabaseReference
    var sprayqtt = 0
    var greenqtt = 0
    var goldqtt = 0
    var username =""
    var treecoin = 0
    var gamecoin = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        getSess()
        bindingPotion = FragmentPotionshopBinding.inflate(inflater)
        database = FirebaseDatabase.getInstance()
        databaseReference = database.getReference().child("user").child(username)

        //retrieve game coin
        databaseReference.child("gameCoin").get().addOnSuccessListener {
            gamecoin = it.value.toString().toInt()
//            bindingPotion.textViewGameCoin.text = gamecoin.toString()
        }.addOnFailureListener {
            Log.e("firebase", "Error getting data", it)
        }
        //retrieve tree coin
        databaseReference.child("treeCoin").get().addOnSuccessListener {
            treecoin = it.value.toString().toInt()
//            bindingPotion.textViewTreeCoin.text = treecoin.toString()
        }.addOnFailureListener {
            Log.e("firebase", "Error getting data", it)
        }
        //spray portion quantity
        databaseReference.child("spray_quantity").get().addOnSuccessListener {
            sprayqtt = it.value.toString().toInt()
        }.addOnFailureListener {
            Log.e("firebase", "Error getting data", it)
        }
        //green portion quantity
        databaseReference.child("green_quantity").get().addOnSuccessListener {
            greenqtt = it.value.toString().toInt()
        }.addOnFailureListener {
            Log.e("firebase", "Error getting data", it)
        }
        //gold portion quantity
        databaseReference.child("golden_quantity").get().addOnSuccessListener {
            goldqtt = it.value.toString().toInt()
        }.addOnFailureListener {
            Log.e("firebase", "Error getting data", it)
        }

        //price
        bindingPotion.spraytagbutton.setOnClickListener{
            if (gamecoin>=500){
                gamecoin -= 500
//                bindingPotion.textViewGameCoin.text = gamecoin.toString()
                sprayqtt += 1
                updateData("gameCoin","spray_quantity",gamecoin, sprayqtt)
                Toast.makeText(activity, "Successfully bought 1 Spray!\n Spray Balance: "+ sprayqtt , Toast.LENGTH_SHORT).show()
            }else
                Toast.makeText(activity, "Insufficient GAME COIN!\n Spray Balance: " + sprayqtt, Toast.LENGTH_SHORT).show()
        }

        bindingPotion.greentagbutton.setOnClickListener{
            if(gamecoin>=800) {
                gamecoin -= 800
//                bindingPotion.textViewGameCoin.text = gamecoin.toString()
                greenqtt += 1
                updateData("gameCoin","green_quantity",gamecoin, greenqtt)
                Toast.makeText(activity, "Successfully bought 1 Green Pot!\n Green Pot Balance: "+ greenqtt , Toast.LENGTH_SHORT).show()
            }else
                Toast.makeText(activity, "Insufficient GAME COIN!\n Green Pot Balance: " + greenqtt, Toast.LENGTH_SHORT).show()
        }
        bindingPotion.goldtagbutton.setOnClickListener{
            if(gamecoin>=1500) {
                gamecoin -= 1500
//                bindingPotion.textViewGameCoin.text = gamecoin.toString()
                goldqtt += 1
                updateData("gameCoin","golden_quantity",gamecoin, goldqtt)
                Toast.makeText(activity, "Successfully bought 1 Gold Pot!\n Gold Pot Balance: "+ goldqtt , Toast.LENGTH_SHORT).show()
            }else
                Toast.makeText(activity, "Insufficient GAME COIN!\n Gold Pot Balance: " + goldqtt, Toast.LENGTH_SHORT).show()
        }



        bindingPotion.treeGrowthButton.setOnClickListener {
            replaceFragment(Tree())
        }

        return bindingPotion.root
    }

    private fun updateData(string1: String, string2: String, gamecoin: Number, quantity: Number){
        var dataUpdate = hashMapOf<String, Any>(
            string1 to gamecoin,
            string2 to quantity
        )
        databaseReference.updateChildren(dataUpdate)
    }

    private fun replaceFragment(fragment : Fragment){

        val fragmentManager = getActivity()?.supportFragmentManager
        val fragmentTransaction = fragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.frameLayout, fragment)
        fragmentTransaction?.commit()
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