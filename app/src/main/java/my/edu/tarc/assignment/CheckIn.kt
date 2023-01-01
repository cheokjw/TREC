package my.edu.tarc.assignment

import android.content.Context
import android.os.Bundle
import android.service.controls.ControlsProviderService.TAG
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentActivity
import com.google.android.material.tabs.TabLayout.TabGravity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.database.*
import com.google.firebase.database.core.Tag
import my.edu.tarc.assignment.Regis.Signup_regis
import my.edu.tarc.assignment.databinding.FragmentCheckInBinding

class CheckIn : Fragment() {
    private lateinit var bindingCheckIn: FragmentCheckInBinding

    private lateinit var counterTextView: TextView
    private lateinit var builder : AlertDialog.Builder

    lateinit var database: FirebaseDatabase
    lateinit var databaseReference: DatabaseReference

    private var counter = 0
    var gameCoin = 0
    val handler = android.os.Handler()
    private var checkin = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingCheckIn = FragmentCheckInBinding.inflate(inflater)
        return bindingCheckIn.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity: FragmentActivity?= activity

        // Get who is the current user
        val mainActivity: MainActivity = getActivity() as MainActivity
        val sessionUser = mainActivity.getSessionUser()
        database = FirebaseDatabase.getInstance()
        databaseReference = database.getReference("user")

        //Starting Point of the Check In Bar
        bindingCheckIn.progressBarCheckIn.progress = 0

        //Show Balance Constantly
        counterTextView = bindingCheckIn.textViewRegBalance
        handler.post(updateCounter)

        //Check In Button
        bindingCheckIn.buttonCheckIn.setOnClickListener {
            bindingCheckIn.notCheckedInStatus.setImageResource(R.drawable.checkedin)

            databaseReference.child(sessionUser).get().addOnSuccessListener {

                if (it.exists()){

                    var dbCheckIn = it.child(sessionUser).child("checkin").value.toString().toInt()
                    var dbGameCoin = it.child(sessionUser).child("gameCoin").value
                    var checkin : Int

                    checkin = if (dbCheckIn == null){
                        0
                    }
                    else {
                        dbCheckIn as Int
                    }

                    gameCoin = if (dbGameCoin == null){
                        0
                    }else {
                        dbGameCoin as Int
                    }

                    if(checkin<1) {
                        when (counter) {
                            0 -> {
                                //Day 1 CheckIn
                                bindingCheckIn.progressBarCheckIn.progress = 0
                                bindingCheckIn.imageViewDay1.setImageResource((R.drawable.checked_in_progress))
                                gameCoin += 5
                                Toast.makeText(activity, "5 Coins Added!$gameCoin", Toast.LENGTH_SHORT).show()
                                counter++
                                checkin++

                                var coinUpdate = hashMapOf<String, Any>(
                                    "gameCoin" to gameCoin,
                                    "checkin" to checkin
                                )

                                databaseReference.child(sessionUser).updateChildren(coinUpdate)
                            }
                            1 -> {
                                //Day 2 CheckIn
                                //If Statement to Prevent User from checking in twice
                                bindingCheckIn.progressBarCheckIn.progress = (bindingCheckIn.progressBarCheckIn.progress + 15) % 100
                                bindingCheckIn.imageViewDay2.setImageResource((R.drawable.checked_in_progress))
                                gameCoin += 10
                                Toast.makeText(activity, "5 Coins Added!$gameCoin", Toast.LENGTH_SHORT)
                                    .show()
                                counter++
                                checkin+=2

                                var coinUpdate = hashMapOf<String, Any>(
                                    "gameCoin" to gameCoin,
                                    "checkin" to checkin
                                )

                                databaseReference.child(sessionUser).updateChildren(coinUpdate)

                            }
                            2 -> {
                                //Day 3 CheckIn
                                bindingCheckIn.progressBarCheckIn.progress = (bindingCheckIn.progressBarCheckIn.progress + 15) % 100
                                bindingCheckIn.imageViewDay2.setImageResource((R.drawable.checked_in_progress))
                                gameCoin += 15
                                Toast.makeText(activity, "5 Coins Added!$gameCoin", Toast.LENGTH_SHORT)
                                    .show()
                                counter++
                                checkin++
                                databaseReference.child(sessionUser).child("checkin").setValue(checkin)
                                databaseReference.child(sessionUser).child("gameCoin").setValue(gameCoin)
                            }
                            3 -> {
                                //Day 4 CheckIn
                                bindingCheckIn.progressBarCheckIn.progress = (bindingCheckIn.progressBarCheckIn.progress + 15) % 100
                                bindingCheckIn.imageViewDay2.setImageResource((R.drawable.checked_in_progress))
                                gameCoin += 5
                                Toast.makeText(activity, "5 Coins Added!$gameCoin", Toast.LENGTH_SHORT)
                                    .show()
                                counter++
                                checkin++
                                databaseReference.child(sessionUser).child("checkin").setValue(checkin)
                                databaseReference.child(sessionUser).child("gameCoin").setValue(gameCoin)
                            }
                            4 -> {
                                //Day 5 CheckIn
                                bindingCheckIn.progressBarCheckIn.progress = (bindingCheckIn.progressBarCheckIn.progress + 15) % 100
                                bindingCheckIn.imageViewDay2.setImageResource((R.drawable.checked_in_progress))
                                gameCoin += 5
                                Toast.makeText(activity, "5 Coins Added!$gameCoin", Toast.LENGTH_SHORT)
                                    .show()
                                counter++
                                checkin++
                                databaseReference.child(sessionUser).child("checkin").setValue(checkin)
                                databaseReference.child(sessionUser).child("gameCoin").setValue(gameCoin)
                            }
                            5 -> {
                                //Day 6 CheckIn
                                bindingCheckIn.progressBarCheckIn.progress = (bindingCheckIn.progressBarCheckIn.progress + 15) % 100
                                bindingCheckIn.imageViewDay2.setImageResource((R.drawable.checked_in_progress))
                                gameCoin += 5
                                Toast.makeText(activity, "5 Coins Added!$gameCoin", Toast.LENGTH_SHORT)
                                    .show()
                                counter++
                                checkin++
                                databaseReference.child(sessionUser).child("checkin").setValue(checkin)
                                databaseReference.child(sessionUser).child("gameCoin").setValue(gameCoin)
                            }
                            6 -> {
                                //Day 7 CheckIn
                                bindingCheckIn.progressBarCheckIn.progress = (bindingCheckIn.progressBarCheckIn.progress + 15) % 100
                                bindingCheckIn.imageViewDay2.setImageResource((R.drawable.checked_in_progress))
                                gameCoin += 25
                                Toast.makeText(activity, "25 Coins Added!$gameCoin", Toast.LENGTH_SHORT)
                                    .show()
                                counter++
                                checkin++
                                databaseReference.child(sessionUser).child("checkin").setValue(checkin)
                                databaseReference.child(sessionUser).child("gameCoin").setValue(gameCoin)

                                //Show Pop To Notify User
                                builder = AlertDialog.Builder(activity!!)
                                builder.setTitle("Congratulations!")
                                    .setMessage("You have checked in for 7 days")
                                    .setCancelable(true)
                                    .setPositiveButton("Ok") { dialogInterface, it ->
                                        bindingCheckIn.progressBarCheckIn.progress = 0
                                        bindingCheckIn.imageViewDay1.setImageResource((R.drawable.check_in_progress))
                                        bindingCheckIn.imageViewDay2.setImageResource((R.drawable.check_in_progress))
                                        bindingCheckIn.imageViewDay3.setImageResource((R.drawable.check_in_progress))
                                        bindingCheckIn.imageViewDay4.setImageResource((R.drawable.check_in_progress))
                                        bindingCheckIn.imageViewDay5.setImageResource((R.drawable.check_in_progress))
                                        bindingCheckIn.imageViewDay6.setImageResource((R.drawable.check_in_progress))
                                        bindingCheckIn.imageViewDay7.setImageResource((R.drawable.check_in_progress))
                                        dialogInterface.cancel()
                                    }
                                    .show()
                            }
                        }
                    }else{
                        Toast.makeText(activity, "Already Checked In Today!\nPlease Try Again Tomorrow!", Toast.LENGTH_SHORT).show()
                    }


                }else {
                    Toast.makeText(activity, "User Doesn't Exists", Toast.LENGTH_SHORT).show()
                }
            }.addOnFailureListener{
                Toast.makeText(activity, "Failed to Retrieve User", Toast.LENGTH_SHORT).show()
            }
        }


        //TODO: setAlarm function and create variables to be stored as data set

        //Nav to History Page
        bindingCheckIn.buttonRewards.setOnClickListener {

            replaceFragment(Signup())
        }
    }


    //Function to Constantly Update Counter without Delay
    private val updateCounter = object : Runnable {
        override fun run() {
            counterTextView.text = gameCoin.toString()
            handler.postDelayed(this, 0) // run instantly
        }
    }

//    private fun getSharedPreferences(s: String, modePrivate: Int): Any {
//        // First, get a reference to the SharedPreferences object
//        val sharedPreferences = getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
//
//        var currentTime = System.currentTimeMillis()
//
//        var lastClickTime = sharedPreferences.getLong("last_click_time", 0)
//
//        val timeSinceLastClick = currentTime - lastClickTime
//
//
//        if (timeSinceLastClick == 86400000) {
//            // It has been at least one day, so allow the button to be clicked
//
//            // Perform the button's action here
//
//            // Save the current time as the "last_click_time" in the SharedPreferences object
//            sharedPreferences.edit().putLong("last_click_time", currentTime).apply()
//        } else {
//            // It has not been at least one day, so prevent the button from being clicked
//        }
//    }

    private fun replaceFragment(fragment : Fragment){

        val fragmentManager = activity?.supportFragmentManager
        val fragmentTransaction = fragmentManager?.beginTransaction()

        // Selecting which part of the UI should be replaced by the fragment
        // in this case its the frameLayout in activity_main.xml
        fragmentTransaction?.replace(R.id.frameLayout, fragment)
        fragmentTransaction?.commit()
    }


}