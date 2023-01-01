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
    private lateinit var bindingTree: FragmentTreeBinding
    lateinit var database: FirebaseDatabase
    lateinit var databaseReference: DatabaseReference
    var gamecoin = 0
    var sprayqtt = 0
    var greenqtt = 0
    var goldqtt = 0
    var username =""
    var treecoin = 0

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
        bindingPotion = FragmentPotionshopBinding.inflate(inflater)
        //retrieve game coin
        databaseReference.child("gameCoin").get().addOnSuccessListener {
            gamecoin = it.value.toString().toInt()
            bindingPotion.textViewGameCoin.text = it.value.toString()
        }.addOnFailureListener {
            Log.e("firebase", "Error getting data", it)
        }
        //retrieve tree coin
        databaseReference.child("treeCoin").get().addOnSuccessListener {
            treecoin = it.value.toString().toInt()
            bindingPotion.textViewTreeCoin.text = it.value.toString()
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
                sprayqtt += 1
                Toast.makeText(activity, "Successfully bought 1 Spray!\n Spray Balance: "+ sprayqtt , Toast.LENGTH_SHORT).show()
                }else
                Toast.makeText(activity, "Insufficient GAME COIN!\n Spray Balance: " + sprayqtt, Toast.LENGTH_SHORT).show()
            var sprayUpdate = hashMapOf<String, Any>(
                "gameCoin" to gamecoin,
                "spray_quantity" to sprayqtt
            )
            databaseReference.updateChildren(sprayUpdate)
            databaseReference.child("gameCoin").get().addOnSuccessListener {
                gamecoin = it.value.toString().toInt()
                bindingPotion.textViewGameCoin.text = it.value.toString()
            }.addOnFailureListener {
                Log.e("firebase", "Error getting data", it)
            }
        }

        bindingPotion.greentagbutton.setOnClickListener{
            if(gamecoin>=800) {
                gamecoin -= 800
                greenqtt += 1
                Toast.makeText(activity, "Successfully bought 1 Green Pot!\n Green Pot Balance: "+ greenqtt , Toast.LENGTH_SHORT).show()
            }else
                Toast.makeText(activity, "Insufficient GAME COIN!\n Green Pot Balance: " + greenqtt, Toast.LENGTH_SHORT).show()
            var greenUpdate = hashMapOf<String, Any>(
                "gameCoin" to gamecoin,
                "green_quantity" to greenqtt
            )
            databaseReference.updateChildren(greenUpdate)
            databaseReference.child("gameCoin").get().addOnSuccessListener {
                gamecoin = it.value.toString().toInt()
                bindingPotion.textViewGameCoin.text = it.value.toString()
            }.addOnFailureListener {
                Log.e("firebase", "Error getting data", it)
            }
        }
        bindingPotion.goldtagbutton.setOnClickListener{
            if(gamecoin>=1500) {
                gamecoin -= 1500
                goldqtt += 1
                Toast.makeText(activity, "Successfully bought 1 Gold Pot!\n Gold Pot Balance: "+ goldqtt , Toast.LENGTH_SHORT).show()
            }else
                Toast.makeText(activity, "Insufficient GAME COIN!\n Gold Pot Balance: " + goldqtt, Toast.LENGTH_SHORT).show()
            var goldenUpdate = hashMapOf<String, Any>(
                "gameCoin" to gamecoin,
                "golden_quantity" to goldqtt
            )
            databaseReference.updateChildren(goldenUpdate)
            databaseReference.child("gameCoin").get().addOnSuccessListener {
                gamecoin = it.value.toString().toInt()
                bindingPotion.textViewGameCoin.text = it.value.toString()
            }.addOnFailureListener {
                Log.e("firebase", "Error getting data", it)
            }
        }



        bindingPotion.treeGrowthButton.setOnClickListener {
            replaceFragment(Tree())
        }

        return bindingPotion.root
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